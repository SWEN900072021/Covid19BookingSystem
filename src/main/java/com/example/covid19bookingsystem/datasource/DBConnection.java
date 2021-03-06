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

            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Connection getDbConnection() throws SQLException {
        if (dbConnection == null || dbConnection.isClosed()) {
            dbConnection = connection();
        }
        return dbConnection;
    }

    public static void close(PreparedStatement stmt, ResultSet rs) throws SQLException {
        if (dbConnection != null) {
            dbConnection.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

}
