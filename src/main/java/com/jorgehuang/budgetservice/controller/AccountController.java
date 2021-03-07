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
    public Object getAccounts() {
        List<Account> accounts = accountService.getAll();
        return accounts != null ?
                accounts :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/accounts/{id}")
    @ResponseBody
    public Object getAccountById(@PathVariable Integer id) {
        Account account = accountService.getById(id);
        return account != null ?
                account :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/accounts")
    public ResponseEntity createAccount(@RequestParam String name, @RequestParam String type) {
        Account account = new Account();
        account.setName(name);
        account.setType(type);

        return accountService.create(account) ?
                new ResponseEntity(HttpStatus.CREATED):
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Integer id) {
        return accountService.delete(id) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity updateAccountById(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam Boolean enabled) {
        Account account = new Account(id, name, type, enabled);
        return accountService.update(account) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
