package com.degan.jpatest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.degan.jpatest.domain.Account;
import com.degan.jpatest.domain.Address;
import com.degan.jpatest.repository.AccountRepository;

@DataJpaTest
public class AccountRepositoryTest {
    
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void addressTest() {
        Account account = new Account();
        account.setUsername("testuser");
        account.setPassword("passworda");
        Address address = new Address();
        address.setZipCode("000");

        account.setAddress(address);

        accountRepository.save(account);


        List<Account> rtn = accountRepository.findByAddressZipCode("000");

        assertThat(rtn.size()).isEqualTo(1);
    }
}
