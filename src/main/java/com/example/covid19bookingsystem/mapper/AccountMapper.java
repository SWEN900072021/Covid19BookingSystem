package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {
    public void insert(String username, String password) {
        String sql = "INSERT INTO account (username, password, account_type) VALUES (?, ?, ?);";
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        String accountType = "VR";

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setString(1, username);
            findStatement.setString(2, password);
            findStatement.setString(3, accountType);
            findStatement.execute();

            rs = findStatement.getResultSet();
            if (rs.next()) {
                System.out.println("Username and password are correct.");
            } else {
                System.out.println("Username and/or password are incorrect.");
            }
        } catch (SQLException e) {
            //do something
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
