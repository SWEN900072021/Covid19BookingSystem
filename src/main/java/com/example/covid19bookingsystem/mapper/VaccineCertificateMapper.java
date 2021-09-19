package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.VaccineCertificate;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.covid19bookingsystem.mapper.VaccineRecipientMapper.findVaccineRecipientById;

public class VaccineCertificateMapper {

    public static void insert(Integer vaccineRecipient, String vaccineType) {
        String sql = "INSERT INTO vaccine_certificate (vaccine_recipient, vaccine_type) VALUES (?, ?);";
        PreparedStatement statement = null;
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, vaccineRecipient);
            statement.setString(2, vaccineType);
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

    public static List<VaccineCertificate> findVaccineCertificatesByVaccineRecipientId(Integer id) {
        String sql = "SELECT * FROM vaccine_certificate WHERE vaccine_recipient = ?;";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<VaccineCertificate> vaccineCertificates = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Integer vaccineRecipientId = rs.getInt("vaccine_recipient");
                VaccineRecipient vaccineRecipient = findVaccineRecipientById(vaccineRecipientId);

                String vaccineReceived = rs.getString("vaccine_type");
                VaccineType vaccineType = new VaccineType();
                vaccineType.setName(vaccineReceived);

                VaccineCertificate vaccineCertificate = new VaccineCertificate();
                vaccineCertificate.setVaccineRecipient(vaccineRecipient);
                vaccineCertificate.setVaccineType(vaccineType);

                vaccineCertificates.add(vaccineCertificate);
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

        return vaccineCertificates;
    }

}
