package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        when(transactionRepository.getByDateRange(any(Date.class), any(Date.class))).thenReturn(transactions);
        List<Transaction> res = service.getByDateRange(1609477200000L, 1612069200000L);
        assertEquals(1, res.get(0).getId());
        assertEquals(2, res.get(1).getId());
    }

    @Test
    public void shouldReturnZeroWhenGetByDateRangeThrowsException() throws Exception {
        when(transactionRepository.getByDateRange(any(Date.class), any(Date.class))).thenThrow(mock(DataAccessException.class));
        assertNull(service.getByDateRange(1609477200000L, 1612069200000L));
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

    @Test
    public void shouldReturnTrueWhenTransactionIsCreated() throws Exception {
        when(transactionRepository.create(any(Transaction.class))).thenReturn(1);
        assertTrue(service.create("", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionIsNotCreated() throws Exception {
        when(transactionRepository.create(any(Transaction.class))).thenReturn(0);
        assertFalse(service.create("", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionCreationThrowsException() throws Exception {
        when(transactionRepository.create(any(Transaction.class))).thenThrow(mock(DataAccessException.class));
        assertFalse(service.create("", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnTrueWhenTransactionIsUpdated() throws Exception {
        when(transactionRepository.update(any(Transaction.class))).thenReturn(1);
        assertTrue(service.update(1, "", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionIsNotUpdated() throws Exception {
        when(transactionRepository.update(any(Transaction.class))).thenReturn(0);
        assertFalse(service.update(1, "", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionUpdateThrowsException() throws Exception {
        when(transactionRepository.update(any(Transaction.class))).thenThrow(mock(DataAccessException.class));
        assertFalse(service.update(1, "", "", 2.15, 1L, 1));
    }

    @Test
    public void shouldReturnTrueWhenTransactionIsDeleted() throws Exception {
        when(transactionRepository.delete(anyInt())).thenReturn(1);
        assertTrue(service.delete(1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionIsNotDeleted() throws Exception {
        when(transactionRepository.delete(anyInt())).thenReturn(0);
        assertFalse(service.delete(1));
    }

    @Test
    public void shouldReturnFalseWhenTransactionDeletionThrowsException() throws Exception {
        when(transactionRepository.delete(anyInt())).thenThrow(mock(DataAccessException.class));
        assertFalse(service.delete(1));
    }

}
