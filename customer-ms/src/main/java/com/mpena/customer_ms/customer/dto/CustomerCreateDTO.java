package com.mpena.customer_ms.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateDTO {
    
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private Long accountNumber;
}
