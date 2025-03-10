package com.mpena.accounts_ms.account.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountCreateDTO {

    private Long customerId;

    private Long accountNumber;

    private String accountType;

    private Long balance;
}
