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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void getAccountsShouldReturnStatus500() throws Exception {
        when(accountService.getAll()).thenReturn(null);
        mockMvc.perform(get("/accounts")).andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void getAccountByIdShouldReturnAccount() throws Exception {
        when(accountService.getById(anyInt())).thenReturn(new Account());
        mockMvc.perform(get("/accounts/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void deleteAccountByIdShouldReturn200() throws Exception {
        when(accountService.delete(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/accounts/1")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void deleteAccountByIdShouldReturn500() throws Exception {
        when(accountService.delete(anyInt())).thenReturn(false);
        mockMvc.perform(delete("/accounts/100")
                .with(csrf()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser()
    public void deleteAccountByIdShouldReturn403WhenCSRFIsNull() throws Exception {
        when(accountService.delete(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/accounts/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser()
    public void updateAccountShouldReturn200() throws Exception {
        when(accountService.update(any())).thenReturn(true);
        mockMvc.perform(put("/accounts/1")
                .param("name", "bank")
                .param("type", "checking")
                .param("enabled", "false")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void getAccountByIdShouldReturnAccountWhenNull() throws Exception {
        when(accountService.getById(anyInt())).thenReturn(null);
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode201() throws Exception {
        when(accountService.create(any())).thenReturn(true);
        mockMvc.perform(post("/accounts")
                .param("name", "My bank account")
                .param("type", "investment")
                .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser()
    public void postAccountsShouldReturnStatusCode500() throws Exception {
        when(accountService.create(any())).thenReturn(false);
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
