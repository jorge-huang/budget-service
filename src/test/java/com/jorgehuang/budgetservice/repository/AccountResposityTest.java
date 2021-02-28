package com.jorgehuang.budgetservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AccountResposityTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @WithMockUser("user3")
    public void shouldReturnAllAccountsGivenUserGroup() {
        assertEquals(3, accountRepository.getAllAccounts().size());
        assertEquals("My Bank Checking C", accountRepository.getAllAccounts().get(0).getName());
        assertEquals("My Bank Savings C", accountRepository.getAllAccounts().get(1).getName());
        assertEquals("My Brokerage C", accountRepository.getAllAccounts().get(2).getName());
    }
}
