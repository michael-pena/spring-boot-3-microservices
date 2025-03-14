package com.mpena.customer_ms.customer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerDetailsDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;
import com.mpena.customer_ms.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    public final static String CUSTOMER_PATH = "/api/v1/customer/";
    public final static String CUSTOMER_PATH_ID = CUSTOMER_PATH + "{customerId}/";
    public final static String CUSTOMER_DETAILS = CUSTOMER_PATH + "details/{customerId}/";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable("customerId") Long customerId) {

        CustomerResponseDTO responseDTO = customerService.getCustomer(customerId);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(CUSTOMER_PATH)
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customerList =  customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerList);
    }
    

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerCreateDTO createDTO) {
        CustomerResponseDTO responseDTO = customerService.createNewCustomer(createDTO);
        return ResponseEntity
                .created(UriComponentsBuilder
                    .fromPath(CUSTOMER_PATH_ID)
                    .build(responseDTO.getId()))
                .body(responseDTO);
    }

    @GetMapping(CUSTOMER_DETAILS)
    public ResponseEntity<CustomerDetailsDTO> getCustomerDetails(@PathVariable("customerId") Long customerId) {
        CustomerDetailsDTO customerDetailsDTO = customerService.getCustomerDetails(customerId);
        return ResponseEntity.ok().body(customerDetailsDTO);
    }
    
}
