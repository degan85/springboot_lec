package com.bbs.deganbbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.deganbbs.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
