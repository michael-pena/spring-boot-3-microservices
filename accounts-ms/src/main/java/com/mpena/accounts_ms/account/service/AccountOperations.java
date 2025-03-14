package com.mpena.accounts_ms.account.service;

import java.util.List;

import com.mpena.accounts_ms.account.dto.AccountCreateDTO;
import com.mpena.accounts_ms.account.dto.AccountResponseDTO;

public interface AccountOperations {

    public AccountResponseDTO createNewAccount(AccountCreateDTO createDTO);

    public List<AccountResponseDTO> getAllAccounts();

    public AccountResponseDTO getAccount(Long accountId);

    public AccountResponseDTO getAccountByCustomerId(Long customerId);
}
