package com.mpena.customer_ms.customer.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mpena.customer_ms.customer.dto.AccountDTO;
import com.mpena.customer_ms.customer.service.client.AccountsFeignClient;

@Component
public class AccountsFallback implements AccountsFeignClient{

    @Override
    public ResponseEntity<AccountDTO> fetchAccountDetails(Long customerId, String correlationId) {
        return null;
        // here we can do anything during fallback 
        // read from redis cache, read from another db, etc.
    }
}
