package com.mpena.customer_ms.customer.service;

import java.util.List;

import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerDetailsDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;

public interface CustomerOperations {
    public CustomerResponseDTO createNewCustomer(CustomerCreateDTO createDTO);

    public List<CustomerResponseDTO> getAllCustomers();

    public CustomerResponseDTO getCustomer(Long customerId);

    public CustomerDetailsDTO getCustomerDetails(Long customerId, String correlationId);
}
