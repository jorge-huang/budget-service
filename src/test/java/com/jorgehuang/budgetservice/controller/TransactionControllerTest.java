package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    @WithMockUser()
    public void shouldReturnTransactionsWhenGetTransactionsIsSuccessful() throws Exception {
        List<Transaction> transactions = new ArrayList<>(1);
        transactions.add(new Transaction(1, null, null, null, null, null, null));
        when(transactionService.getByDateRange(anyLong(), anyLong())).thenReturn(transactions);
        mockMvc.perform(get("/transactions")
                .queryParam("startDate", "1609477200000")
                .queryParam("endDate", "1612069200000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1,\"")));
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus500WhenGetTransactionsFails() throws Exception {
        when(transactionService.getByDateRange(anyLong(), anyLong())).thenReturn(null);
        mockMvc.perform(get("/transactions")
                .queryParam("startDate", "1609477200000")
                .queryParam("endDate", "1612069200000"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void shouldReturnTransactionWhenGetTransactionByIdIsSuccessful() throws Exception {
        when(transactionService.getById(anyInt()))
                .thenReturn(new Transaction(1, null, null, null, null, null, null));
        mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1,\"")));
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus500WhenGetTransactionByIdFails() throws Exception {
        when(transactionService.getById(anyInt())).thenReturn(null);
        mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus201WhenCreateIsSuccessful() throws Exception {
        when(transactionService.create(anyString(), anyString(), anyDouble(), anyLong(), anyInt()))
                .thenReturn(true);
        mockMvc.perform(post("/transactions")
                .param("description", "snack")
                .param("category", "food")
                .param("amount", "10.50")
                .param("date", "1609477200000")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus500WhenCreateFails() throws Exception {
        when(transactionService.create(anyString(), anyString(), anyDouble(), anyLong(), anyInt()))
                .thenReturn(false);
        mockMvc.perform(post("/transactions")
                .param("description", "snack")
                .param("category", "food")
                .param("amount", "10.50")
                .param("date", "1609477200000")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void shouldReturn200WhenUpdateIsSuccessful() throws Exception {
        when(transactionService.update(anyInt(), anyString(), anyString(), anyDouble(), anyLong(), anyInt()))
                .thenReturn(true);
        mockMvc.perform(put("/transactions/1")
                .param("description", "snack")
                .param("category", "food")
                .param("amount", "10.50")
                .param("date", "1609477200000")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void shouldReturn500WhenUpdateFails() throws Exception {
        when(transactionService.update(anyInt(), anyString(), anyString(), anyDouble(), anyLong(), anyInt()))
                .thenReturn(false);
        mockMvc.perform(put("/transactions/1")
                .param("description", "snack")
                .param("category", "food")
                .param("amount", "10.50")
                .param("date", "1609477200000")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus200WhenDeleteIsSuccessful() throws Exception {
        when(transactionService.delete(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/transactions/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void shouldReturnStatus500WhenDeleteFails() throws Exception {
        when(transactionService.delete(anyInt())).thenReturn(false);
        mockMvc.perform(delete("/transactions/1")
                .with(csrf()))
                .andExpect(status().isInternalServerError());
    }
}
