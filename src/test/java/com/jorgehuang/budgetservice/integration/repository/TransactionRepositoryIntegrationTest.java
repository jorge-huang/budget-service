package com.jorgehuang.budgetservice.integration.repository;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TransactionRepositoryIntegrationTest {
    @Autowired
    TransactionRepository repository;

    @Test
    @WithMockUser()
    public void getTransactionsByDateRange() {
        List<Transaction> transactions = repository.getTransactionsByDateRange("2021-01-01", "2021-01-31");
        assertEquals(2, transactions.size());
        assertEquals(1, transactions.get(0).getId());
        assertEquals("food from best grocery", transactions.get(0).getDescription());
        assertEquals("food", transactions.get(0).getCategory());
        assertEquals(10.50, transactions.get(0).getAmount());
        assertEquals("2021-01-01", transactions.get(0).getDate().toString());
        assertEquals(1, transactions.get(0).getAccountId());
        assertEquals("My Bank Checking A", transactions.get(0).getAccountName());
    }

    @Test
    @WithMockUser()
    public void getTransactionsById() {
        List<Transaction> transaction = repository.getTransactionById(1);
        assertEquals(1, transaction.size());
        assertEquals(1, transaction.get(0).getId());
        assertEquals("food from best grocery", transaction.get(0).getDescription());
        assertEquals("food", transaction.get(0).getCategory());
        assertEquals(10.50, transaction.get(0).getAmount());
        assertEquals("2021-01-01", transaction.get(0).getDate().toString());
        assertEquals(1, transaction.get(0).getAccountId());
        assertEquals("My Bank Checking A", transaction.get(0).getAccountName());
    }

    @Test
    @WithMockUser()
    public void createUpdateDeleteTransaction() {
        Transaction transaction = new Transaction();
        transaction.setDescription("night out");
        transaction.setCategory("fun");
        transaction.setAmount(1.50);
        Date date = new Date(1615081036661L);
        transaction.setDate(date);
        transaction.setAccountId(1);
        assertEquals(1, repository.create(transaction));

        transaction.setId(28);
        transaction.setDescription("night in");
        transaction.setCategory("not fun");
        transaction.setAmount(1.50);
        date = new Date(1617673036661L);
        transaction.setDate(date);
        transaction.setAccountId(2);
        assertEquals(1, repository.update(transaction));
        assertEquals(28, repository.getTransactionById(28).get(0).getId());
        assertEquals("night in", repository.getTransactionById(28).get(0).getDescription());
        assertEquals("not fun", repository.getTransactionById(28).get(0).getCategory());
        assertEquals(1.50, repository.getTransactionById(28).get(0).getAmount());
        assertEquals("2021-04-05", repository.getTransactionById(28).get(0).getDate().toString());
        assertEquals(2, repository.getTransactionById(28).get(0).getAccountId());

        assertEquals(1, repository.delete(28));
    }
}
