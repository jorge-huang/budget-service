package com.jorgehuang.budgetservice.controller;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    @ResponseBody
    public Object getTransactions(@RequestParam Long startDate, @RequestParam Long endDate) {
        List<Transaction> transactions = transactionService.getByDateRange(startDate, endDate);
        return transactions != null ?
                transactions :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/transactions/{id}")
    @ResponseBody
    public Object getTransactionsById(@PathVariable Integer id) {
        Transaction transactions = transactionService.getById(id);
        return transactions != null ?
                transactions :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/transactions")
    public ResponseEntity<HttpStatus> create(
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam Long date,
            @RequestParam Integer accountId) {

        return transactionService.create(description, category, amount, date, accountId) ?
                new ResponseEntity(HttpStatus.CREATED) :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<HttpStatus> update(
            @PathVariable Integer id,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam Double amount,
            @RequestParam Long date,
            @RequestParam Integer accountId) {
        return transactionService.update(id, description, category, amount, date, accountId) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        return transactionService.delete(id) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
