package com.mpena.customer_ms.customer.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDetailsDTO {

    private AccountDTO accountDTO;
    private CustomerResponseDTO customerResponseDTO;
}
