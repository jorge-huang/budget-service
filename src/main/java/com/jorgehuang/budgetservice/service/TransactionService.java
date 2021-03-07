package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getByDateRange(Long startDate, Long endDate) {
        try {
            return transactionRepository.getByDateRange(new Date(startDate), new Date(endDate));
        } catch (Exception e) {
            return null;
        }
    }

    public Transaction getById(Integer id) {
        try {
            return transactionRepository.getById(id).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(
            String description,
            String category,
            Double amount,
            Long date,
            Integer accountId) {
        try {
            Transaction transaction = Transaction.builder()
                    .description(description)
                    .category(category)
                    .amount(amount)
                    .date(new Date(date))
                    .accountId(accountId)
                    .build();

            return transactionRepository.create(transaction) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(
            Integer id,
            String description,
            String category,
            Double amount,
            Long date,
            Integer accountId) {
        try {
            Transaction transaction = Transaction.builder()
                    .id(id)
                    .description(description)
                    .category(category)
                    .amount(amount)
                    .date(new Date(date))
                    .accountId(accountId)
                    .build();

            return transactionRepository.update(transaction) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        try {
            return transactionRepository.delete(id) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
