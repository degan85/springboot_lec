package com.degan.jpatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.degan.jpatest.domain.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
    
    // List<Account> findByAddressZipCodeContains(String zipcode);

    List<Account> findByAddressZipCode(String string);
}
