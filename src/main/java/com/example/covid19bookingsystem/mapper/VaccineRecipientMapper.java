package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VaccineRecipientMapper {
    public static void insert(VaccineRecipient vaccineRecipient) {
        String sql = "INSERT INTO vaccine_recipient (account_id, first_name, last_name, address_line_1, address_line_2, postcode, state, country, date_of_birth, gender, phone_number, " +
                "email_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        // NOTE:placing this here for now but best kept somewhere else
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, vaccineRecipient.getAccountId());
            statement.setString(2, vaccineRecipient.getFirstName());
            statement.setString(3, vaccineRecipient.getLastName());
            statement.setString(4, vaccineRecipient.getAddress().getAddressLine1());
            statement.setString(5, vaccineRecipient.getAddress().getAddressLine2());
            statement.setString(6, vaccineRecipient.getAddress().getPostcode());
            statement.setString(7, vaccineRecipient.getAddress().getState());
            statement.setString(8, vaccineRecipient.getAddress().getCountry());
            statement.setDate(9, vaccineRecipient.getDateOfBirth());
            statement.setString(10, vaccineRecipient.getGender().toString());
            statement.setString(11, vaccineRecipient.getPhoneNumber());
            statement.setString(12, vaccineRecipient.getEmail());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static VaccineRecipient findVRByAccount(Integer accountId) {
        String sql = "SELECT id FROM vaccine_recipient WHERE account_id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        VaccineRecipient vr = new VaccineRecipient();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            rs = statement.executeQuery();
            if (rs.next()) {
                vr.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vr;
    }
}
