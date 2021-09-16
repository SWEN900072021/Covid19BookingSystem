package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {

    public static void insert(Account account) {
        String sql = "INSERT INTO account (username, password, account_type) VALUES (?, ?, ?) RETURNING id;";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getAccountType().name());
            rs = statement.executeQuery();
            if (rs.next()) {
                account.setAccountId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
