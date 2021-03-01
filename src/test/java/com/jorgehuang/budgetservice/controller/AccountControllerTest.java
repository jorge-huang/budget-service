package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    @WithMockUser()
    public void getAccountsShouldReturnListOfAccounts() throws Exception {
        mockMvc.perform(get("/accounts")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void getAccountByIdShouldReturnAccount() throws Exception {
        when(accountService.findById(anyInt())).thenReturn(new Account());
        mockMvc.perform(get("/accounts/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void getAccountByIdShouldReturnAccountWhenNull() throws Exception {
        when(accountService.findById(anyInt())).thenReturn(null);
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode201() throws Exception {
        when(accountService.create(any())).thenReturn(1);
        mockMvc.perform(post("/accounts")
                .param("name", "My bank account")
                .param("type", "investment")
                .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode500() throws Exception {
        when(accountService.create(any())).thenReturn(0);
        mockMvc.perform(post("/accounts")
                .param("name", "My bank account")
                .param("type", "investment")
                .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode400WhenNameIsNull() throws Exception {
        mockMvc.perform(post("/accounts")
                .param("type", "investment")
                .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode400WhenTypeIsNull() throws Exception {
        mockMvc.perform(post("/accounts")
                .param("name", "My bank account")
                .with(csrf()))
                .andExpect(status().isBadRequest());
    }
}
