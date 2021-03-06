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
public class AccountsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("user")
    public void getAccountsWithUser() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"My Bank Checking A\",\"type\":\"checking\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":2,\"name\":\"My Bank Savings A\",\"type\":\"savings\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":3,\"name\":\"My Brokerage A\",\"type\":\"investment\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":4,\"name\":\"My Bank Checking B\",\"type\":\"checking\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":5,\"name\":\"My Bank Savings B\",\"type\":\"savings\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":6,\"name\":\"My Brokerage B\",\"type\":\"investment\",\"enabled\":true}")));
    }

    @Test
    @WithMockUser("user2")
    public void getAccountsWithUser2() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"My Bank Checking A\",\"type\":\"checking\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":2,\"name\":\"My Bank Savings A\",\"type\":\"savings\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":3,\"name\":\"My Brokerage A\",\"type\":\"investment\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":4,\"name\":\"My Bank Checking B\",\"type\":\"checking\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":5,\"name\":\"My Bank Savings B\",\"type\":\"savings\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":6,\"name\":\"My Brokerage B\",\"type\":\"investment\",\"enabled\":true}")));
    }

    @Test
    @WithMockUser("user3")
    public void getAccountsWithUser3() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":7,\"name\":\"My Bank Checking C\",\"type\":\"checking\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":8,\"name\":\"My Bank Savings C\",\"type\":\"savings\",\"enabled\":true}")))
                .andExpect(content().string(containsString("{\"id\":9,\"name\":\"My Brokerage C\",\"type\":\"investment\",\"enabled\":true}")));
    }

    @Test
    @WithMockUser("user")
    public void getAccountByIdWithUser() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"name\":\"My Bank Checking A\",\"type\":\"checking\",\"enabled\":true}")));
    }

    @Test
    @WithMockUser("user2")
    public void getAccountByIdWithUser2() throws Exception {
        mockMvc.perform(get("/accounts/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":2,\"name\":\"My Bank Savings A\",\"type\":\"savings\",\"enabled\":true}")));

    }

    @Test
    @WithMockUser("user3")
    public void getAccountByIdWithUser3() throws Exception {
        mockMvc.perform(get("/accounts/7"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":7,\"name\":\"My Bank Checking C\",\"type\":\"checking\",\"enabled\":true}")));
    }

    @Test
    @WithMockUser("user")
    public void createUpdateAndDeleteAccountById() throws Exception {
        mockMvc.perform(post("/accounts")
                .param("name", "My foo account")
                .param("type", "checking")
                .with(csrf()))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/accounts/10")
                .param("name", "bank")
                .param("type", "checking")
                .param("enabled", "false")
                .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/accounts/10")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
