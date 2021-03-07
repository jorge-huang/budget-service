package com.jorgehuang.budgetservice.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("user")
    public void getTransactionsWithUser() throws Exception {
        mockMvc.perform(get("/transactions")
                .queryParam("startDate", "1609477200000")
                .queryParam("endDate", "1612069200000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":1,\"accountName\":\"My Bank Checking A\"}")))
                .andExpect(content().string(containsString("{\"id\":10,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":4,\"accountName\":\"My Bank Checking B\"}")));
    }

    @Test
    @WithMockUser("user2")
    public void getTransactionsWithUser2() throws Exception {
        mockMvc.perform(get("/transactions")
                .queryParam("startDate", "1609477200000")
                .queryParam("endDate", "1612069200000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":1,\"accountName\":\"My Bank Checking A\"}")))
                .andExpect(content().string(containsString("{\"id\":10,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":4,\"accountName\":\"My Bank Checking B\"}")));
    }

    @Test
    @WithMockUser("user3")
    public void getTransactionsWithUser3() throws Exception {
        mockMvc.perform(get("/transactions")
                .queryParam("startDate", "1622520000000")
                .queryParam("endDate", "1625025600000"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":19,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-06-01\",\"accountId\":7,\"accountName\":\"My Bank Checking C\"}")))
                .andExpect(content().string(containsString("{\"id\":22,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-06-01\",\"accountId\":8,\"accountName\":\"My Bank Savings C\"}")))
                .andExpect(content().string(containsString("{\"id\":25,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-06-01\",\"accountId\":9,\"accountName\":\"My Brokerage C\"}")));
    }

    @Test
    @WithMockUser("user")
    public void getTransactionByIdWithUser() throws Exception {
        mockMvc.perform(get("/transactions/10"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":10,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":4,\"accountName\":\"My Bank Checking B\"}")));
    }

    @Test
    @WithMockUser("user2")
    public void getTransactionByIdWithUser2() throws Exception {
        mockMvc.perform(get("/transactions/10"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":10,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-01-01\",\"accountId\":4,\"accountName\":\"My Bank Checking B\"}")));
    }

    @Test
    @WithMockUser("user3")
    public void getTransactionByIdWithUser3() throws Exception {
        mockMvc.perform(get("/transactions/22"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":22,\"description\":\"food from best grocery\",\"category\":\"food\",\"amount\":10.5,\"date\":\"2021-06-01\",\"accountId\":8,\"accountName\":\"My Bank Savings C\"}")));
    }

    @Test
    @WithMockUser()
    public void createUpdateDeleteTransactionWithUser() throws Exception {
        mockMvc.perform(post("/transactions")
                .param("description", "snack")
                .param("category", "food")
                .param("amount", "10.50")
                .param("date", "1609477200000")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/transactions/28")
                .param("description", "more snack")
                .param("category", "food")
                .param("amount", "11.50")
                .param("date", "1609477200001")
                .param("accountId", "1")
                .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/transactions/28")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
