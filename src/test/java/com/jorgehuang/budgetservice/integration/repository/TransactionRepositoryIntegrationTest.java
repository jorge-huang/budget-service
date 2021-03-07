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
    public void getTransactionsByDateRange() throws Exception {
        List<Transaction> transactions = repository.getByDateRange(new Date(1609477200000L), new Date(1612069200000L));
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
    public void getTransactionsById() throws Exception {
        List<Transaction> transaction = repository.getById(1);
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
    public void createUpdateDeleteTransaction() throws Exception {
        Transaction transaction = Transaction.builder()
                .description("night out")
                .category("fun")
                .amount(1.50)
                .date(new Date(1615081036661L))
                .accountId(1)
                .build();
        assertEquals(1, repository.create(transaction));

        transaction.setId(28);
        transaction.setDescription("night in");
        transaction.setCategory("not fun");
        transaction.setAmount(1.50);
        transaction.setDate(new Date(1617673036661L));
        transaction.setAccountId(2);
        assertEquals(1, repository.update(transaction));
        assertEquals(1, repository.delete(28));
    }
}
