package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.covid19bookingsystem.utils.EnumUtils.AccountType.valueOf;

public class AccountMapper {

    // SQL state for a violation of the constraint imposed by a unique index or a unique constraint occurred
    private static final String UNIQUE_CONSTRAINT_VIOLATION = "23505";

    public static String insert(Account account) {
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
            return "SUCCESS";
        } catch (SQLException e) {
            if (e.getSQLState().equals(UNIQUE_CONSTRAINT_VIOLATION)) {
                System.out.println("USERNAME TAKEN ERROR: " + e.getMessage());
                return "USERNAME_TAKEN";
            }
            System.out.println("Account Mapper Error: " + e.getMessage());
            return "ERROR";
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Account findAccountByUsername(String username) {
        String sql = "SELECT * FROM account WHERE username = ?;";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Account account = new Account();
            account.setAccountId(rs.getInt("id"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            account.setAccountType(valueOf(rs.getString("account_type")));
            return account;
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Account findAccountByID(Integer id) {
        String sql = "SELECT * FROM account WHERE id = ?;";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Account account = new Account();
            account.setAccountId(rs.getInt("id"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            account.setAccountType(valueOf(rs.getString("account_type")));
            return account;
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<Account> getAllAccounts() {
        String sql = "SELECT * FROM account ;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setAccountType(valueOf(rs.getString("account_type")));
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }

    public static ArrayList<Account> getAllVaccineRecipients() {
        String sql = "SELECT * FROM account WHERE account_type = ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, EnumUtils.AccountType.VR.name());
            rs = statement.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setAccountType(EnumUtils.AccountType.valueOf(rs.getString("account_type")));
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

    public static ArrayList<Account> getAllHealthCareProviders() {
        String sql = "SELECT * FROM account WHERE account_type = ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, EnumUtils.AccountType.HCP.name());
            rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setAccountType(valueOf(rs.getString("account_type")));
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }

}
