package com.jorgehuang.budgetservice.repository;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> getAllAccounts() throws DataAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer groupId = jdbcTemplate.queryForObject("SELECT group_id FROM group_members WHERE username = ?", Integer.class, username);
        return jdbcTemplate.query("SELECT * FROM accounts WHERE group_id = ?", new AccountMapper(), groupId);
    }
}
