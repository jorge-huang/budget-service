package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public @ResponseBody
    List<Account> getAccounts() {
        return accountService.getAll();
    }
}
