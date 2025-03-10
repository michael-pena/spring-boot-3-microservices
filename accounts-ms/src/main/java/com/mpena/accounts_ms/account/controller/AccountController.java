package com.mpena.accounts_ms.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mpena.accounts_ms.account.dto.AccountCreateDTO;
import com.mpena.accounts_ms.account.dto.AccountResponseDTO;
import com.mpena.accounts_ms.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

    public final static String ACCOUNT_PATH = "/api/v1/account/";
    public final static String ACCOUNT_PATH_ID = ACCOUNT_PATH + "{accountId}/";

    public final AccountService accountService;

    @GetMapping(ACCOUNT_PATH_ID)
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable("accountId") Long accountId) {

        AccountResponseDTO responseDTO = accountService.getAccount(accountId);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(ACCOUNT_PATH)
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accountList =  accountService.getAllAccounts();
        return ResponseEntity.ok().body(accountList);
    }
    

    @PostMapping(ACCOUNT_PATH)
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountCreateDTO createDTO) {
        AccountResponseDTO responseDTO = accountService.createNewAccount(createDTO);
        return ResponseEntity
                .created(UriComponentsBuilder
                    .fromPath(ACCOUNT_PATH)
                    .build(responseDTO.getAccountNumber()))
                .body(responseDTO);
    }
    
    
}
