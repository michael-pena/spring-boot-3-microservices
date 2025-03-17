package com.mpena.gatewayserver.controller;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
public class FallbackController {

    @GetMapping("/contactSupport")
    public Mono<String> getMethodName(@RequestParam String param, @RequestHeader("bank-correlation-id") String correlationId) {
        
        return Mono.just("An error occured. Please try again later! Correletion Id: " + correlationId);
    }    
}
