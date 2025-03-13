package com.mpena.accounts_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mpena.accounts_ms.account.controller.ContactInfoDTO;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {ContactInfoDTO.class})
public class AccountsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsMsApplication.class, args);
	}
	//TODO: finish the basics of this MS - dollar amounts, etc.
	//TODO: create a customer MS - customer info only
	//TODO: global error handling service/controllers, validation on entity and DTOs
	//TODO: enable auditing
}
