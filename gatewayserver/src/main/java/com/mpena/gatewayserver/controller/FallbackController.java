package com.mpena.gatewayserver.controller;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class FallbackController {

    @GetMapping("/contactSupport")
    public Mono<String> getMethodName(@RequestParam String param) {
        
        return Mono.just("An error occured. Please try again later!");
    }    
}
