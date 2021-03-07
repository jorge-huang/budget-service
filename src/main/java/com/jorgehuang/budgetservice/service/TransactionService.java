package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getByDateRange(String startDate, String endDate) {
        try {
            return transactionRepository.getByDateRange(startDate, endDate);
        } catch (Exception e) {
            return null;
        }
    }
}
