package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.VaccineRecipient;
import com.example.covid19bookingsystem.datasource.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VaccineRecipientMapper {
    public void insert(VaccineRecipient vaccineRecipient) {
        // NOTE: not sure how unique account variable works in sql. Omitted for now
        String sql = "INSERT INTO vaccine_recipient (id, first_name, last_name, address, date_of_birth, gender, phone_number, " +
                "email_address, vaccination_status, vaccination_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement findStatement = null;

        // NOTE:placing this here for now but best kept somewhere else
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setString(1, Integer.toString(vaccineRecipient.getId()));
            findStatement.setString(2, vaccineRecipient.getFirst_name());
            findStatement.setString(3, vaccineRecipient.getLast_name());
            findStatement.setString(4, vaccineRecipient.getAddress());
            findStatement.setString(5, dateFormat.format(vaccineRecipient.getDate_of_birth()));
            findStatement.setString(6, vaccineRecipient.getGender());
            findStatement.setString(7, Integer.toString(vaccineRecipient.getPhone_number()));
            findStatement.setString(8, vaccineRecipient.getEmail());
            findStatement.setString(9, vaccineRecipient.getVaccine_status().name());
            findStatement.setString(10, vaccineRecipient.getVaccine_type().name());
            findStatement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
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

    public void update(VaccineRecipient vaccineRecipient) {
        try {


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(VaccineRecipient vaccineRecipient) {
        try{

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
