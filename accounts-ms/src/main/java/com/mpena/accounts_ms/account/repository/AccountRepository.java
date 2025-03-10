package com.mpena.accounts_ms.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpena.accounts_ms.account.entity.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

}
