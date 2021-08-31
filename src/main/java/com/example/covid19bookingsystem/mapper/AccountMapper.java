package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AccountMapper {
    public void insert(String username, String password) {
        String sql = "INSERT INTO account (username, password, account_type) VALUES (?, ?, ?::account_type);";
        PreparedStatement findStatement = null;

        String accountType = "VR";

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setString(1, username);
            findStatement.setString(2, password);
            findStatement.setString(3, accountType);
            findStatement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                if (findStatement != null) {
                    findStatement.close();
                }
                if (DBConnection.getDbConnection() != null) {
                    DBConnection.getDbConnection().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
