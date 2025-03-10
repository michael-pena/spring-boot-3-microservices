package com.mpena.accounts_ms.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mpena.accounts_ms.account.dto.AccountCreateDTO;
import com.mpena.accounts_ms.account.dto.AccountResponseDTO;
import com.mpena.accounts_ms.account.entity.Accounts;
import com.mpena.accounts_ms.account.mapper.AccountMapper;
import com.mpena.accounts_ms.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountOperations{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDTO createNewAccount(AccountCreateDTO createDTO) {
        return accountMapper.toDTO(accountRepository.save(accountMapper.toEntity(createDTO)));
    }

    @Override
    public AccountResponseDTO getAccount(Long accountId) throws RuntimeException {
        
        return accountMapper.toDTO(accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account with id: " + accountId + "not found.")));
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<Accounts> accountsResults = accountRepository.findAll();

        if (accountsResults.isEmpty()) {
            return new ArrayList<AccountResponseDTO>();
        }
        
        return accountsResults.stream()
            .map(accountMapper::toDTO)
            .toList();
    }

    
}
