package com.example.covid19bookingsystem.datasource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DBConnection {

    private static Connection dbConnection;

    private static Connection connection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' +
                    dbUri.getPort() + dbUri.getPath();

            System.out.println("USERNAME: " + username);
            System.out.println("PASSWORD: " + password);
            System.out.println("DB_URL: " + dbUrl);

            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getDbConnection() {
        if (dbConnection == null) {
            dbConnection = connection();
        }
        return dbConnection;
    }

}
