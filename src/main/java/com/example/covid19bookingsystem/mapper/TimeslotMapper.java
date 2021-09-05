package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Timeslot;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TimeslotMapper {

    public void insert(Timeslot timeslot) {

        String sql = "INSERT INTO timeslot (vaccine_recipient, health_care_provider, questionnaire, vaccination_type, date_time, duration, location) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, timeslot.getVaccineRecipient());
            statement.setInt(2, timeslot.getHealthcareProvider());
            statement.setInt(3, timeslot.getQuestionnaire());
            statement.setString(4, timeslot.getVaccinationType().toString());
            statement.setTimestamp(5, timeslot.getDateTime());
            statement.setInt(6, timeslot.getDuration());
            statement.setString(7, timeslot.getLocation());
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
