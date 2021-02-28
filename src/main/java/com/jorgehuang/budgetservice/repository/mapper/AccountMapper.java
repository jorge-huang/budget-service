package com.jorgehuang.budgetservice.repository.mapper;

import com.jorgehuang.budgetservice.domain.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setType(resultSet.getString("type"));
        account.setEnabled(resultSet.getBoolean("enabled"));
        return account;
    }
}