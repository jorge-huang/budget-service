package com.jorgehuang.budgetservice.repository;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int getCurrentUserGroupId() throws DataAccessException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jdbcTemplate.queryForObject("SELECT group_id FROM group_members WHERE username = ?", Integer.class, username);
    }

    public List<Transaction> getAllTransactions() throws DataAccessException {
        String sql = "SELECT transactions.id, description, category, amount, date, account_id, accounts.name AS account_name FROM transactions JOIN accounts ON account_id = accounts.id AND transactions.group_id = ?";
        return jdbcTemplate.query(sql, new TransactionMapper(), getCurrentUserGroupId());
    }
}
