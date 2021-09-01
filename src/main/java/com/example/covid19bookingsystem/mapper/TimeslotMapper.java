package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Timeslot;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimeslotMapper {

    public void insert(Timeslot timeslot) {

        String sql = "INSERT INTO timeslot (healthcare_provider, date_time, duration, location) VALUES (?, ?, ?, ?);";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, timeslot.getHealthcareProvider());
            statement.setTimestamp(2, timeslot.getDateTime());
            statement.setInt(3, timeslot.getDuration());
            statement.setString(4, timeslot.getLocation());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
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
