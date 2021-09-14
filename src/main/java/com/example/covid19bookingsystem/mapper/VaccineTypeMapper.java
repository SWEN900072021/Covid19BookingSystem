package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VaccineTypeMapper {

    public void insert(VaccineType vaccineType) {
        String sql = "INSERT INTO vaccine_type (name) VALUES (?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType.getName());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
