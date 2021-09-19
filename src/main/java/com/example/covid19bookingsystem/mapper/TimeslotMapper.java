package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.utils.EnumUtils;

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

        String sql = "INSERT INTO timeslot (health_care_provider, vaccine_type, status, date_time, duration, " +
                "address_line_1, address_line_2, postcode, state, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, timeslot.getHealthcareProvider().getId());
            statement.setString(2, timeslot.getVaccineType());
            statement.setString(3, timeslot.getStatus().name());
            statement.setTimestamp(4, timeslot.getDateTime());
            statement.setInt(5, timeslot.getDuration());
            statement.setString(6, timeslot.getAddress().getAddressLine1());
            statement.setString(7, timeslot.getAddress().getAddressLine2());
            statement.setString(8, timeslot.getAddress().getPostcode());
            statement.setString(9, timeslot.getAddress().getState());
            statement.setString(10, timeslot.getAddress().getCountry());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        }
    }

    public static List<Timeslot> findTimeslotByHcpAndVaccineType(HealthCareProvider HCP, String vaccineType) {
        String sql = "SELECT * FROM timeslot " +
                "WHERE status = ? " +
                "AND vaccine_type = ? " +
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
            statement.setString(1, EnumUtils.TimeslotStatus.UNBOOKED.toString());
            statement.setString(2, vaccineType);
            statement.setInt(3, HCP.getId());
            statement.setTimestamp(4, Timestamp.valueOf(formatDateTime));
            rs = statement.executeQuery();
            while (rs.next()) {
                Timeslot timeslot = new Timeslot();
                timeslot.setId(rs.getInt("id"));

                HealthCareProvider healthCareProvider = new HealthCareProvider();
                healthCareProvider.setId(rs.getInt("health_care_provider"));
                timeslot.setHealthcareProvider(healthCareProvider);

                timeslot.setVaccineType(rs.getString("vaccine_type"));
                timeslot.setDateTime(Timestamp.valueOf(rs.getString("date_time")));
                timeslot.setDuration(rs.getInt("duration"));

                Address address = new Address();
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setPostcode(rs.getString("postcode"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                timeslot.setAddress(address);

                timeslots.add(timeslot);
            }

        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return timeslots;
    }

    public static void update(Timeslot timeslot, VaccineRecipient vr) {
        String sql = "UPDATE timeslot SET vaccine_recipient = ?, status = ? WHERE id = ?";

        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, vr.getId());
            statement.setString(2, EnumUtils.TimeslotStatus.BOOKED.name());
            statement.setInt(3, timeslot.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        }
    }

    public static List<Timeslot> findTimeslotsByPostCodeAndVaccineType(String postcode, String vaccineType) {
        String sql = "SELECT * FROM timeslot " +
                "WHERE status = ? " +
                "AND vaccine_type = ? " +
                "AND date_time >= ? " +
                "AND postcode = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Timeslot> timeslots = new ArrayList<>();

        try {
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = today.format(format);

            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, EnumUtils.TimeslotStatus.UNBOOKED.toString());
            statement.setString(2, vaccineType);
            statement.setTimestamp(3, Timestamp.valueOf(formatDateTime));
            statement.setString(4, postcode);
            rs = statement.executeQuery();
            while (rs.next()) {
                Timeslot timeslot = new Timeslot();
                timeslot.setId(rs.getInt("id"));

                HealthCareProvider healthCareProvider = new HealthCareProvider();
                healthCareProvider.setId(rs.getInt("health_care_provider"));
                timeslot.setHealthcareProvider(healthCareProvider);

                timeslot.setVaccineType(rs.getString("vaccine_type"));
                timeslot.setDateTime(Timestamp.valueOf(rs.getString("date_time")));
                timeslot.setDuration(rs.getInt("duration"));

                Address address = new Address();
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setPostcode(rs.getString("postcode"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                timeslot.setAddress(address);

                timeslots.add(timeslot);
            }

        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return timeslots;
    }

    public static List<Timeslot> findAllAvailableTimeslots() {
        String sql = "SELECT * FROM timeslot WHERE status = ? AND date_time >= ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Timeslot> timeslots = new ArrayList<>();

        try {
            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = today.format(format);

            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, EnumUtils.TimeslotStatus.UNBOOKED.toString());
            statement.setTimestamp(2, Timestamp.valueOf(formatDateTime));
            rs = statement.executeQuery();
            while (rs.next()) {
                Timeslot timeslot = new Timeslot();
                timeslot.setId(rs.getInt("id"));

                HealthCareProvider healthCareProvider = new HealthCareProvider();
                healthCareProvider.setId(rs.getInt("health_care_provider"));
                timeslot.setHealthcareProvider(healthCareProvider);

                timeslot.setVaccineType(rs.getString("vaccine_type"));
                timeslot.setDateTime(Timestamp.valueOf(rs.getString("date_time")));
                timeslot.setDuration(rs.getInt("duration"));

                Address address = new Address();
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setPostcode(rs.getString("postcode"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                timeslot.setAddress(address);

                timeslots.add(timeslot);
            }

        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return timeslots;
    }
}
