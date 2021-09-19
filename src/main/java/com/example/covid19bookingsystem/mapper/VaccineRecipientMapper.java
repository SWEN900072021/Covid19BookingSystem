package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.VaccineRecipient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.example.covid19bookingsystem.utils.EnumUtils.Gender.valueOf;

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
            statement.setString(12, vaccineRecipient.getEmailAddress());
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

    public static VaccineRecipient findVRById(Integer id) {
        String sql = "SELECT * FROM vaccine_recipient WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        VaccineRecipient vr = new VaccineRecipient();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                vr.setId(rs.getInt(1));
                vr.setFirstName(rs.getString("first_name"));
                vr.setLastName(rs.getString("last_name"));

                Address address = new Address();
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setPostcode(rs.getString("postcode"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                vr.setAddress(address);

                vr.setDateOfBirth(rs.getDate("date_of_birth"));
                vr.setGender(valueOf(rs.getString("gender")));
                vr.setPhoneNumber(rs.getString("phone_number"));
                vr.setEmailAddress(rs.getString("email_address"));
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

    public static VaccineRecipient findVRByAccount(Integer accountId) {
        String sql = "SELECT * FROM vaccine_recipient WHERE account_id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        VaccineRecipient vr = new VaccineRecipient();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            rs = statement.executeQuery();
            if (rs.next()) {
                vr.setId(rs.getInt(1));
                vr.setFirstName(rs.getString("first_name"));
                vr.setLastName(rs.getString("last_name"));

                Address address = new Address();
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setPostcode(rs.getString("postcode"));
                address.setState(rs.getString("state"));
                address.setCountry(rs.getString("country"));
                vr.setAddress(address);

                vr.setDateOfBirth(rs.getDate("date_of_birth"));
                vr.setGender(valueOf(rs.getString("gender")));
                vr.setPhoneNumber(rs.getString("phone_number"));
                vr.setEmailAddress(rs.getString("email_address"));
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
