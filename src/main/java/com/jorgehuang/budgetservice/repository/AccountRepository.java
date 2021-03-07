package com.jorgehuang.budgetservice.repository;

import com.jorgehuang.budgetservice.domain.Account;
import com.jorgehuang.budgetservice.repository.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int getCurrentUserGroupId() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jdbcTemplate.queryForObject("SELECT group_id FROM group_members WHERE username = ?", Integer.class, username);
    }

    public List<Account> getAll() throws Exception {
        return jdbcTemplate.query("SELECT * FROM accounts WHERE group_id = ?", new AccountMapper(), getCurrentUserGroupId());
    }

    public int create(Account account) throws Exception {
        return jdbcTemplate.update("INSERT INTO accounts (name, type, group_id) VALUES (?, ?, ?)",
                account.getName(),
                account.getType(),
                getCurrentUserGroupId());
    }

    public Account getById(int id) throws Exception {
        return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id = ? AND group_id = ?", new AccountMapper(), id, getCurrentUserGroupId());
    }

    public int delete(int id) throws Exception {
        return jdbcTemplate.update("DELETE FROM accounts WHERE id = ? AND group_id = ?", id, getCurrentUserGroupId());
    }

    public int update(Account account) throws Exception {
        return jdbcTemplate.update("UPDATE accounts SET name = ?, type = ?, enabled = ? WHERE id = ? AND group_id = ?",
                account.getName(),
                account.getType(),
                account.getEnabled(),
                account.getId(),
                getCurrentUserGroupId());
    }
}
