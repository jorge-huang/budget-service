package com.jorgehuang.budgetservice.integration.repository;

import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TransactionRepositoryIntegrationTest {
    @Autowired
    TransactionRepository repository;

    @Test
    @WithMockUser("user")
    public void getAllTransactions() {
        assertEquals(18, repository.getAllTransactions().size());
        assertEquals(1, repository.getAllTransactions().get(0).getId());
        assertEquals("food from best grocery", repository.getAllTransactions().get(0).getDescription());
        assertEquals("food", repository.getAllTransactions().get(0).getCategory());
        assertEquals(10.50, repository.getAllTransactions().get(0).getAmount());
        assertEquals("2021-01-01", repository.getAllTransactions().get(0).getDate().toString());
        assertEquals(1, repository.getAllTransactions().get(0).getAccountId());
        assertEquals("My Bank Checking A", repository.getAllTransactions().get(0).getAccountName());
    }
}
