package com.mpena.customer_ms.customer.controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerDetailsDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;
import com.mpena.customer_ms.customer.service.CustomerService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    public final static String CUSTOMER_PATH = "/api/v1/customer/";
    public final static String CUSTOMER_PATH_ID = CUSTOMER_PATH + "{customerId}/";
    public final static String CUSTOMER_DETAILS = CUSTOMER_PATH + "details/{customerId}/";

    private final CustomerService customerService;
    private final Environment environment;

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

    @Retry(name="getFallbackTest", fallbackMethod = "getBuildInfoFallback")
    @GetMapping(CUSTOMER_PATH + "fallbacktest/")
    public ResponseEntity<String> getFallbackTest() throws TimeoutException {
        log.debug("getFallbackTest() method invoked");
        throw new TimeoutException(); // make the retry method kick in
        //return ResponseEntity.ok().body("Build: 1.0");
    }
    
    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        log.debug("getBuildInfoFallback() method invoked");
        return ResponseEntity.ok().body("Fallback method version: 1.0");
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
    public ResponseEntity<CustomerDetailsDTO> getCustomerDetails(@RequestHeader("bank-correlation-id") String correlationId, 
        @PathVariable("customerId") Long customerId) {
        log.debug("bank-correlation-id found: {}", correlationId);
        CustomerDetailsDTO customerDetailsDTO = customerService.getCustomerDetails(customerId, correlationId);
        return ResponseEntity.ok().body(customerDetailsDTO);
    }

    @RateLimiter(name="getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }
    

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity.ok().body("Java 17");
    }
}
