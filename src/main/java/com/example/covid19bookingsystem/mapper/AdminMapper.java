package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Account;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AdminMapper {

    public void insert(Account account) {
        String sql = "INSERT INTO admin (username, password) VALUES (?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (DBConnection.getDbConnection() != null) {
                    DBConnection.getDbConnection().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
