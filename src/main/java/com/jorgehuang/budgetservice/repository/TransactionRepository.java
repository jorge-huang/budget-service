package com.jorgehuang.budgetservice.repository;

import com.jorgehuang.budgetservice.domain.Transaction;
import com.jorgehuang.budgetservice.repository.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int getCurrentUserGroupId() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return jdbcTemplate.queryForObject("SELECT group_id FROM group_members WHERE username = ?", Integer.class, username);
    }

    public List<Transaction> getByDateRange(Date startDate, Date endDate) throws Exception {
        String sql = "SELECT transactions.id, description, category, amount, date, account_id, accounts.name AS account_name FROM transactions JOIN accounts ON account_id = accounts.id AND transactions.group_id = ? AND date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new TransactionMapper(), getCurrentUserGroupId(), startDate, endDate);
    }

    public List<Transaction> getById(Integer id) throws Exception {
        String sql = "SELECT transactions.id, description, category, amount, date, account_id, accounts.name AS account_name FROM transactions JOIN accounts ON account_id = accounts.id AND transactions.group_id = ? AND transactions.id = ?";
        return jdbcTemplate.query(sql, new TransactionMapper(), getCurrentUserGroupId(), id);
    }

    public int create(Transaction transaction) throws Exception {
        String sql = "INSERT INTO transactions (description, category, amount, date, account_id, group_id) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                transaction.getDescription(),
                transaction.getCategory(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getAccountId(),
                getCurrentUserGroupId());
    }

    public int update(Transaction transaction) throws Exception {
        String sql = "UPDATE transactions SET description = ?, category = ?, amount = ?, date = ?, account_id = ? WHERE id = ? AND group_id = ?";
        return jdbcTemplate.update(sql,
                transaction.getDescription(),
                transaction.getCategory(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getAccountId(),
                transaction.getId(),
                getCurrentUserGroupId());
    }

    public int delete(Integer id) throws Exception {
        String sql = "DELETE FROM transactions WHERE id = ? AND group_id = ?";
        return jdbcTemplate.update(sql, id, getCurrentUserGroupId());
    }
}
