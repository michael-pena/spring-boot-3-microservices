package com.mpena.customer_ms.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mpena.customer_ms.customer.dto.AccountDTO;
import com.mpena.customer_ms.customer.dto.CustomerMsgDto;
import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerDetailsDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;
import com.mpena.customer_ms.customer.entity.Customer;
import com.mpena.customer_ms.customer.mapper.CustomerMapper;
import com.mpena.customer_ms.customer.repository.CustomerRepository;
import com.mpena.customer_ms.customer.service.client.AccountsFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerOperations{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    private final AccountsFeignClient accountsFeignClient;
    private final StreamBridge streamBridge;

    @Override
    public CustomerResponseDTO createNewCustomer(CustomerCreateDTO createDTO) {
        Customer customer = customerRepository.save(customerMapper.toEntity(createDTO));
        sendCommunication(customer);
        return customerMapper.toDTO(customer);
    }

    private void sendCommunication(Customer customer) {        
        var customerMsgDto = new CustomerMsgDto(customer.getFirstName(), customer.getLastName(),
                customer.getAddress(), customer.getAccountNumber());

        log.info("Sending Communication request for the details: {}", customerMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", customerMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customerResults = customerRepository.findAll();

        if (customerResults.isEmpty()) {
            return new ArrayList<CustomerResponseDTO>();
        }

        return customerResults.stream()
            .map(customerMapper::toDTO)
            .toList();
    }

    @Override
    public CustomerResponseDTO getCustomer(Long customerId) throws RuntimeException{
        
        return customerMapper.toDTO( customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer with id: " + customerId + "not found.")));
    }

    @Override
    public CustomerDetailsDTO getCustomerDetails(Long customerId, String correlationId) {
        
        CustomerResponseDTO customerResponseDTO = customerMapper.toDTO(customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer with id: " + customerId + "not found.")));
        
        ResponseEntity<AccountDTO> accountDTO = accountsFeignClient.fetchAccountDetails(customerId, correlationId);
        
        return CustomerDetailsDTO.builder()
            .customerResponseDTO(customerResponseDTO)
            .accountDTO(accountDTO.getBody())
            .build();
    }
}
