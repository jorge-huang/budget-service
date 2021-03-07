package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AccountService.class})
public class AccountServiceTest {
    @Autowired
    AccountService service;

    @MockBean
    AccountRepository accountRepository;

    @Test
    public void shouldReturnAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, "My Bank Savings", "Savings", true));
        accounts.add(new Account(2, "My Bank Checking", "Checking", false));
        when(accountRepository.getAll()).thenReturn(accounts);
        assertEquals(2, service.getAll().size());
        assertEquals(1, service.getAll().get(0).getId());
        assertEquals(2, service.getAll().get(1).getId());
    }

    @Test
    public void shouldReturnNullWhenAllAccountsThrowsException() throws Exception {
        when(accountRepository.getAll()).thenThrow(mock(DataAccessException.class));
        assertNull(service.getAll());
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        when(accountRepository.create(any())).thenReturn(1);
        assertEquals(1, service.create(any(Account.class)));
    }

    @Test
    public void shouldReturnZeroWhenCreateAccountThrowsException() throws Exception {
        when(accountRepository.create(any())).thenThrow(mock(DataAccessException.class));
        assertEquals(0, service.create(any(Account.class)));
    }

    @Test
    public void shouldFindAccountById() throws Exception {
        Account account = new Account();
        account.setId(1);
        when(accountRepository.getById(anyInt())).thenReturn(account);
        assertEquals(1, service.getById(1).getId());
    }

    @Test
    public void shouldReturnNullWhenFindAccountByIdRepositoryThrowsException() throws Exception {
        when(accountRepository.getById(anyInt())).thenThrow(mock(DataAccessException.class));
        assertEquals(null, service.getById(1));
    }

    @Test
    public void shouldDeleteAccountById() throws Exception {
        when(accountRepository.delete(anyInt())).thenReturn(1);
        assertEquals(1, service.delete(1));
    }

    @Test
    public void shouldReturnZeroWhenDeleteAccountByIdThrowsException() throws Exception {
        when(accountRepository.delete(anyInt())).thenThrow(mock(DataAccessException.class));
        assertEquals(0, service.delete(1));
    }

    @Test
    public void shouldUpdateAccountById() throws Exception {
        Account account = new Account();
        account.setId(1);
        when(accountRepository.update(any())).thenReturn(1);
        assertEquals(1, service.update(account));
    }

    @Test
    public void shouldReturnZeroWhenZeroUpdateAccountByIdThrowsException() throws Exception {
        when(accountRepository.update(any())).thenThrow(mock(DataAccessException.class));
        assertEquals(0, service.update(new Account()));
    }
}
