package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static List<Timeslot> findTimeslotByHcpAndVaccineType(HealthCareProvider HCP, String vaccineType) {
        String sql = "SELECT * FROM timeslot " +
                "WHERE vaccine_recipient IS NULL " +
                "AND vaccination_type = ? " +
                "AND health_care_provider = ? " +
                "AND date_time >= ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Timeslot> timeslots = new ArrayList<>();

        try {
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = today.format(format);

            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType);
            statement.setInt(2, HCP.getId());
            statement.setTimestamp(3, Timestamp.valueOf(formatDateTime));
            rs = statement.executeQuery();
            while (rs.next()) {
                Timeslot timeslot = new Timeslot();
                timeslot.setId(rs.getInt("id"));
                timeslot.setHealthcareProvider(rs.getInt("health_care_provider"));
                // TODO: set questionnaire when ready
                timeslot.setVaccinationType(VaccineType.valueOf(rs.getString("vaccination_type").toUpperCase()));
                timeslot.setDateTime(Timestamp.valueOf(rs.getString("date_time")));
                timeslot.setDuration(rs.getInt("duration"));
                timeslot.setLocation(rs.getString("location"));
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
