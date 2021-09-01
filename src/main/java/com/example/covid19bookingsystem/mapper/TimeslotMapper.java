package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("JpaQueryApiInspection")
public class TimeslotMapper {

    public void insert(HttpServletRequest request) {
        String healthcareProvider = request.getParameter("healthcareProvider");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String dateTime = date + " " + time + ":00";
        String duration = request.getParameter("duration");
        if (duration.isBlank()) {
            duration = "15";
        }
        String location = request.getParameter("location");

        String sql = "INSERT INTO timeslot (healthcare_provider, date_time, duration, location) VALUES (?::INT, ?::TIMESTAMP, ?::INT, ?);";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, healthcareProvider);
            statement.setString(2, dateTime);
            statement.setString(3, duration);
            statement.setString(4, location);
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
