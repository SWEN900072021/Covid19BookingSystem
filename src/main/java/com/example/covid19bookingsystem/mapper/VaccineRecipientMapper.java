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
        String sql = "INSERT INTO vaccine_recipient (id, account, first_name, last_name, address, date_of_birth, gender, phone_number, " +
                "email_address, vaccination_status, vaccination_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement findStatement = null;

        // NOTE:placing this here for now but best kept somewhere else
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setString(1, Integer.toString(vaccineRecipient.getId()));
            findStatement.setString(2, Integer.toString(vaccineRecipient.getAccount()));
            findStatement.setString(3, vaccineRecipient.getFirst_name());
            findStatement.setString(4, vaccineRecipient.getLast_name());
            findStatement.setString(5, vaccineRecipient.getAddress());
            findStatement.setString(6, dateFormat.format(vaccineRecipient.getDate_of_birth()));
            findStatement.setString(7, vaccineRecipient.getGender());
            findStatement.setString(8, Integer.toString(vaccineRecipient.getPhone_number()));
            findStatement.setString(9, vaccineRecipient.getEmail());
            findStatement.setObject(10, vaccineRecipient.getVaccine_status(), Types.OTHER);
            findStatement.setObject(11, vaccineRecipient.getVaccine_type(), Types.OTHER);
            findStatement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
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
