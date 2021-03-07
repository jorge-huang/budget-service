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

    public Transaction getById(int i) {
        try {
            return transactionRepository.getById(1).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Transaction transaction) {
        try {
            return transactionRepository.create(transaction) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Transaction transaction) {
        try {
            return transactionRepository.update(transaction) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            return transactionRepository.delete(id) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
