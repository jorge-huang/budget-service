package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AccountService.class})
public class AccountServiceTest {
    @Autowired
    AccountService service;

    @MockBean
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, "My Bank Savings", "Savings", true));
        accounts.add(new Account(2, "My Bank Checking", "Checking", false));
        when(accountRepository.getAllAccounts()).thenReturn(accounts);
    }

    @Test
    public void shouldReturnAllAccounts() {
        assertEquals(2, service.getAll().size());
        assertEquals(1, service.getAll().get(0).getId());
        assertEquals(2, service.getAll().get(1).getId());
    }
}
