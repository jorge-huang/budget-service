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

    private int getCurrentUserGroupId() throws DataAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jdbcTemplate.queryForObject("SELECT group_id FROM group_members WHERE username = ?", Integer.class, username);
    }

    public List<Account> getAllAccounts() throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM accounts WHERE group_id = ?", new AccountMapper(), getCurrentUserGroupId());
    }

    public int create(Account account) throws DataAccessException {
        return jdbcTemplate.update("INSERT INTO accounts (name, type, group_id) VALUES (?, ?, ?)",
                account.getName(),
                account.getType(),
                getCurrentUserGroupId());
    }

    public Account findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id = ? AND group_id = ?", new AccountMapper(), id, getCurrentUserGroupId());
        } catch (Exception e) {
            return null;
        }
    }

    public int delete(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM accounts WHERE id = ? AND group_id = ?", id, getCurrentUserGroupId());
    }

    public int update(Account account) throws DataAccessException {
        return jdbcTemplate.update("UPDATE accounts SET name = ?, type = ?, enabled = ? WHERE id = ? AND group_id = ?",
                account.getName(),
                account.getType(),
                account.getEnabled(),
                account.getId(),
                getCurrentUserGroupId());
    }
}
