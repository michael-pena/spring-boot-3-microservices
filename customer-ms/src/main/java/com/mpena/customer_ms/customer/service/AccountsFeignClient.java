package com.mpena.customer_ms.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mpena.customer_ms.customer.dto.AccountDTO;

@FeignClient("accounts")
public interface AccountsFeignClient {

    @GetMapping("/api/v1/account/customer/{customerId}/")
    public ResponseEntity<AccountDTO> fetchAccountDetails(@PathVariable Long customerId, @RequestHeader("bank-correlation-id")
    String correlationId);
}
