package com.jorgehuang.budgetservice.service;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAll() {
        try {
            return accountRepository.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Account account) {
        try {
            return accountRepository.create(account) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public Account getById(int id) {
        try {
            return accountRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(int id) {
        try {
            return accountRepository.delete(id) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Account account) {
        try {
            return accountRepository.update(account) == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
