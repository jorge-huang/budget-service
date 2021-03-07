package com.jorgehuang.budgetservice.integration.repository;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AccountRepositoryIntegrationTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @WithMockUser("user3")
    public void getAllAccounts() {
        List<Account> accounts = accountRepository.getAll();
        assertEquals(3, accounts.size());
        assertEquals("My Bank Checking C", accounts.get(0).getName());
        assertEquals("My Bank Savings C", accounts.get(1).getName());
        assertEquals("My Brokerage C", accounts.get(2).getName());
    }

    @Test
    @WithMockUser()
    public void getById() {
        Account account = accountRepository.getById(1);
        assertEquals(1, account.getId());
        assertEquals("My Bank Checking A", account.getName());
        assertEquals("checking", account.getType());
        assertEquals(true, account.getEnabled());
    }

    @Test
    @WithMockUser()
    public void createUpdateDeleteAccount() {
        Account account = new Account();
        account.setName("test");
        account.setType("investment");
        assertEquals(1, accountRepository.create(account));

        account = new Account(1, "My Account", "savings", true);
        assertEquals(1, accountRepository.update(account));
        assertEquals(1, accountRepository.delete(10));
    }
}
