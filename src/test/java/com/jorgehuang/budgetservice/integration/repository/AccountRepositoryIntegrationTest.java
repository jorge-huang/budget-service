package com.jorgehuang.budgetservice.integration.repository;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AccountRepositoryIntegrationTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @WithMockUser("user3")
    public void getAllAccountsShouldReturnAllAccountsGivenUserGroup() {
        assertEquals(3, accountRepository.getAllAccounts().size());
        assertEquals("My Bank Checking C", accountRepository.getAllAccounts().get(0).getName());
        assertEquals("My Bank Savings C", accountRepository.getAllAccounts().get(1).getName());
        assertEquals("My Brokerage C", accountRepository.getAllAccounts().get(2).getName());
    }

    @Test
    @WithMockUser()
    public void createShouldCreateAccount() {
        Account account = new Account();
        account.setName("test");
        account.setType("investment");
        assertEquals(1, accountRepository.create(account));
    }

    @Test
    @WithMockUser()
    public void findByIdShouldReturnAccountById() {
        Account account = accountRepository.findById(1);
        assertEquals(1, account.getId());
        assertEquals("My Bank Checking A", account.getName());
        assertEquals("checking", account.getType());
        assertEquals(true, account.getEnabled());
    }

    @Test
    @WithMockUser()
    public void deleteShouldDeleteAccountByIdWhenIdExists() {
        assertEquals(1, accountRepository.delete(10));
    }

    @Test
    @WithMockUser()
    public void deleteShouldReturnZeroWhenIdDoesNotExist() {
        assertEquals(0, accountRepository.delete(1000));
    }

    @Test
    @WithMockUser()
    public void updateShouldUpdateAccount() {
        Account account = new Account(1, "My Account", "savings", true);
        assertEquals(1, accountRepository.update(account));
    }
}
