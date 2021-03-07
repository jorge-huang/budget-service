package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TransactionService.class})
public class TransactionServiceTest {
    @Autowired
    private TransactionService service;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void shouldReturnListOfTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>(2);
        transactions.add(new Transaction(1, null, null, null, null, null, null));
        transactions.add(new Transaction(2, null, null, null, null, null, null));
        when(transactionRepository.getByDateRange(anyString(), anyString())).thenReturn(transactions);
        List<Transaction> res = service.getByDateRange("2021-01-01", "2021-01-31");
        assertEquals(1, res.get(0).getId());
        assertEquals(2, res.get(1).getId());
    }

    @Test
    public void shouldReturnZeroWhenGetByDateRangeThrowsException() throws Exception {
        when(transactionRepository.getByDateRange(anyString(), anyString())).thenThrow(mock(DataAccessException.class));
        assertNull(service.getByDateRange("2021-01-01", "2021-01-31"));
    }

    @Test
    public void shouldReturnTransactionById() throws Exception {
        List<Transaction> transactions = new ArrayList<>(1);
        transactions.add(new Transaction(1, null, null, null, null, null, null));
        when(transactionRepository.getById(1)).thenReturn(transactions);
        assertEquals(1, service.getById(1).getId());
    }

    @Test
    public void shouldReturnNullWhenTransactionByIdIsEmpty() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.getById(anyInt())).thenReturn(transactions);
        assertNull(service.getById(1));
    }

    @Test
    public void shouldReturnNullWhenTransactionThrowsException() throws Exception {
        when(transactionRepository.getById(anyInt())).thenThrow(mock(DataAccessException.class));
        assertNull(service.getById(1));
    }

    // TODO: create
    // TODO: update
    // TODO: delete
}
