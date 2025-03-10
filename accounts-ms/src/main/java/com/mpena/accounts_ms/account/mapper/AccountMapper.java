package com.mpena.accounts_ms.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mpena.accounts_ms.account.dto.AccountCreateDTO;
import com.mpena.accounts_ms.account.dto.AccountResponseDTO;
import com.mpena.accounts_ms.account.entity.Accounts;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Accounts toEntity(AccountCreateDTO accountCreateDTO);
    AccountResponseDTO toDTO(Accounts accountsEntity);

}
