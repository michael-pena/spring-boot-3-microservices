package com.mpena.customer_ms.customer.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {
    private Long customerId;

    private Long accountNumber;

    private String accountType;

    private Long balance;
}
