package com.mpena.customer_ms.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mpena.customer_ms.customer.dto.AccountDTO;
import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerDetailsDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;
import com.mpena.customer_ms.customer.entity.Customer;
import com.mpena.customer_ms.customer.mapper.CustomerMapper;
import com.mpena.customer_ms.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerOperations{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    private final AccountsFeignClient accountsFeignClient;

    @Override
    public CustomerResponseDTO createNewCustomer(CustomerCreateDTO createDTO) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(createDTO)));
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
