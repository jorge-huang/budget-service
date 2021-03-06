package com.jorgehuang.budgetservice.repository.mapper;

import com.jorgehuang.budgetservice.domain.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setCategory(resultSet.getString("category"));
        transaction.setAmount(resultSet.getDouble("amount"));
        transaction.setDate(resultSet.getDate("date"));
        transaction.setAccountId(resultSet.getInt("account_id"));
        transaction.setAccountName(resultSet.getString("account_name"));

        return transaction;
    }
}