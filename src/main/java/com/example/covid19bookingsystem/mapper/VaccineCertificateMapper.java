package com.example.covid19bookingsystem.mapper;


import com.example.covid19bookingsystem.domain.VaccineCertificate;
import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class VaccineCertificateMapper {
    public void insert(VaccineCertificate vaccineCertificate) {
        String sql = "INSERT INTO vaccine_certificate (vaccine_recipient, healthcare_provider, vaccination_type, date_issued) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, vaccineCertificate.getVaccineRecipient());
            statement.setInt(2, vaccineCertificate.getHealthCareProvider());
            statement.setString(3, vaccineCertificate.getVaccinationType().toString());
            statement.setDate(4, vaccineCertificate.getDateIssued());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineCertificate Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
