package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.datasource.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VaccineRecipientMapper {
    public void insert(VaccineRecipient vaccineRecipient) {
        String sql = "INSERT INTO vaccine_recipient (username, password, first_name, last_name, address, date_of_birth, gender, phone_number, " +
                "email_address, vaccination_status, vaccination_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        // NOTE:placing this here for now but best kept somewhere else
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineRecipient.getUsername());
            statement.setString(2, vaccineRecipient.getPassword());
            statement.setString(3, vaccineRecipient.getFirstName());
            statement.setString(4, vaccineRecipient.getLastName());
            statement.setString(5, vaccineRecipient.getAddress());
            statement.setDate(6, vaccineRecipient.getDateOfBirth());
            statement.setString(7, vaccineRecipient.getGender());
            statement.setString(8, vaccineRecipient.getPhoneNumber());
            statement.setString(9, vaccineRecipient.getEmail());
            statement.setString(10, vaccineRecipient.getVaccineStatus().toString());
            statement.setString(11, vaccineRecipient.getVaccineType().toString());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
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
