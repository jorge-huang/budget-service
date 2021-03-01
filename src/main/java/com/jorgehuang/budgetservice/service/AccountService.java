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
        return accountRepository.getAllAccounts();
    }

    public int create(Account account) {
        return accountRepository.create(account);
    }

    public Account findById(int id) {
        return accountRepository.findById(id);
    }

    public int delete(int id) {
        return accountRepository.delete(id);
    }

    public int update(Account account) {
        if (account.getId() == null) {
            return 0;
        }

        return accountRepository.update(account);
    }
}
