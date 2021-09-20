package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VaccineTypeMapper {

    public static void insert(VaccineType vaccineType) {
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

    public static ArrayList<VaccineType> getAllVaccineTypes() {
        String sql = "SELECT * FROM vaccine_type ";

        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<VaccineType> vaccineTypes = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                VaccineType vaccineType = new VaccineType();
                vaccineType.setName(rs.getString("name"));
                vaccineTypes.add(vaccineType);
            }
        } catch (SQLException e) {
            System.out.println("Vaccine Mapper - get all - Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vaccineTypes;
    }

}
