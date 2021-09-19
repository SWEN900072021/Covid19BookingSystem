package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.example.covid19bookingsystem.utils.EnumUtils.TimeslotStatus.valueOf;

public class VaccineCertificateMapper {

    public static void insert(Integer vaccineRecipient, String vaccineType) {
        String sql = "INSERT INTO vaccine_certificate (vaccine_recipient, vaccine_type) VALUES (?, ?);";
        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, vaccineRecipient);
            statement.setString(1, vaccineType);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Vaccine Certificate Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> findVaccineTypesByVaccineRecipientId(Integer id) {
        String sql = "SELECT * FROM vaccine_certificate WHERE vaccine_recipient = ?;";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<String> vaccineTypes = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String vaccineType = rs.getString("vaccine_type");
                vaccineTypes.add(vaccineType);
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

        return vaccineTypes;
    }

}
