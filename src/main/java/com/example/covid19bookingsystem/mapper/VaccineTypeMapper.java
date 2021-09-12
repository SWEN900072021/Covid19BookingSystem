package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VaccineTypeMapper {
    public void insert(VaccineType vaccineType) {
        String sql = "INSERT INTO vaccination_type (name, class, dose_number, min_age, max_age, blood_restriction) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;


        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType.getVaccineName());
            statement.setString(2, vaccineType.getVaccineClass());
            statement.setInt(3, vaccineType.getNumberDoses());
            statement.setInt(4, vaccineType.getMinAge());
            statement.setInt(5, vaccineType.getMaxAge());
            statement.setBoolean(6, vaccineType.getBloodProblemRestriction());

            statement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineType Mapper Error: " + e.getMessage());
        } finally {
            try {
                if (DBConnection.getDbConnection() != null) {
                    DBConnection.getDbConnection().close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

}
