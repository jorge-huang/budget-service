package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    @ResponseBody
    public List<Account> getAccounts() {
        return accountService.getAll();
    }

    @PostMapping("/accounts")
    public ResponseEntity createAccount(@RequestParam String name, @RequestParam String type) {
        Account account = new Account();
        account.setName(name);
        account.setType(type);
        if (accountService.create(account) < 1) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
