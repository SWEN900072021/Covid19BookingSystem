package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Timeslot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TimeslotMapper {

    public static void insert(Timeslot timeslot) {

        String sql = "INSERT INTO timeslot (health_care_provider, vaccination_type, date_time, duration, location) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, timeslot.getHealthcareProvider());
            statement.setString(2, timeslot.getVaccinationType().name());
            statement.setTimestamp(3, timeslot.getDateTime());
            statement.setInt(4, timeslot.getDuration());
            statement.setString(5, timeslot.getLocation());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Timeslot> getAllAvailableTimeslotDates() {
        String sql = "SELECT date_time FROM timeslot WHERE vaccine_recipient IS NULL";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Timeslot> timeslots = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Timeslot timeslot = new Timeslot();
                Timestamp date_time = Timestamp.valueOf(rs.getString(1));
                timeslot.setDateTime(date_time);
                timeslots.add(timeslot);
            }

        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return timeslots;
    }
}
