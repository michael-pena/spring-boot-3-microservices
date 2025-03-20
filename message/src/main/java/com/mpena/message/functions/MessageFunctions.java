package com.mpena.message.functions;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mpena.message.dto.CustomerMsgDto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MessageFunctions {

    @Bean
    public Function<CustomerMsgDto, Long> sms() {
        return customerMsgDto -> {
            log.info("sending sms with details: " +  customerMsgDto.toString());
            return customerMsgDto.accountNumber();
        };
    }

    // Function<input, output>
    @Bean
    public Function<CustomerMsgDto, CustomerMsgDto> email() {
        return customerMsgDto -> {
            log.info("sending email with details: " +  customerMsgDto.toString());
            return customerMsgDto;
        };
    }
}
