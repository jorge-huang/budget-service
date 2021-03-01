package com.jorgehuang.budgetservice.repository;

import com.jorgehuang.budgetservice.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AccountRepositoryTest {
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
    public void findByIdShouldReturnNullWhenAccountIdDoesNotExist() {
        assertEquals(null, accountRepository.findById(100));
    }

    @Test
    @WithMockUser()
    public void deleteShouldDeleteAccountByIdWhenIdExists() {
        assertEquals(1, accountRepository.delete(1));
    }

    @Test
    @WithMockUser()
    public void deleteShouldReturnZeroWhenIdDoesNotExist() {
        assertEquals(0, accountRepository.delete(1000));
    }
}
