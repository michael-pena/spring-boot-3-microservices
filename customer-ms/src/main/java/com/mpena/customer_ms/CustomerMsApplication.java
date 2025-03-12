package com.mpena.customer_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMsApplication.class, args);
		//TODO: add csv data
		//TODO: add feign client
		//TODO: add config server
		//TODO: add eureka server
		//TODO: add google jib dependency to both MS: run command to create container
		//TODO: create docker compose file
	}

}
