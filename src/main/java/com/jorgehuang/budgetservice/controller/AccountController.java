package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/accounts/{id}")
    @ResponseBody
    public Object getAccountById(@PathVariable Integer id) {
        Account account = accountService.getById(id);
        if (account == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return account;
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

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Integer id) {
        if (accountService.delete(id) > 0) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity updateAccountById(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam Boolean enabled) {
        Account account = new Account(id, name, type, enabled);
        if (accountService.update(account) > 0) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
