package com.example.covid19bookingsystem.mapper;


import com.example.covid19bookingsystem.domain.VaccineCertificate;
import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class VaccineCertificateMapper {
    public void insert(VaccineCertificate vaccineCertificate) {
        String sql = "INSERT INTO certificate (id, first_name, last_name, vaccine_type, date_created) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement findStatement = null;

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setInt(1, vaccineCertificate.getId());
            findStatement.setString(2, vaccineCertificate.getFirstName());
            findStatement.setString(3, vaccineCertificate.getLastName());
            findStatement.setObject(4, EnumUtils.VaccineType.PFIZER, Types.OTHER);
            findStatement.setString(5, vaccineCertificate.getDateCreated().toString());
            findStatement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineCertificate Mapper Error: " + e.getMessage());
        } finally {
            try {
                if (findStatement != null) {
                    findStatement.close();
                }
                if (DBConnection.getDbConnection() != null) {
                    DBConnection.getDbConnection().close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
