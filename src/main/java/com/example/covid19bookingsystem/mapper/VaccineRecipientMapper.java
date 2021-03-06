package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.VaccineRecipient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.covid19bookingsystem.utils.EnumUtils.Gender.valueOf;

public class VaccineRecipientMapper {
    public static String insert(VaccineRecipient vaccineRecipient) {
        String sql = "INSERT INTO vaccine_recipient (account_id, first_name, last_name, address_line_1, address_line_2, postcode, state, country, date_of_birth, gender, phone_number, " +
                "email_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        String result = AccountMapper.insert(vaccineRecipient);

        if ("SUCCESS".equals(result)) {
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
                return "SUCCESS";
            } catch (SQLException e) {
                System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
                return "ERROR";
            } finally {
                try {
                    DBConnection.close(statement, null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if ("USERNAME_TAKEN".equals(result)) {
            return "USERNAME_TAKEN";
        } else {
            return "ERROR";
        }
    }

    public static VaccineRecipient findVaccineRecipientById(Integer id) {
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

    public static VaccineRecipient findVaccineRecipientByAccountId(Integer accountId) {
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

    public static HashMap<Integer, String> getAllVRVaccineTypes() {
        String sql = "SELECT * FROM vaccine_recipient ;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        // <id,account_id>
        HashMap<Integer, Integer> vaccineRecipients = new HashMap<>();
        HashMap<Integer, String> vrVaccineTypes = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                vaccineRecipients.put(rs.getInt("id"), rs.getInt("account_id"));
            }

            vrVaccineTypes = VaccineCertificateMapper.getAllCertificates(vaccineRecipients);

        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vrVaccineTypes;
    }

    public static ArrayList<Account> getVRVCMappingbyType(String vaccineType) {
        String sql = "SELECT * FROM vaccine_recipient ;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        // <id,account_id>
        HashMap<Integer, Integer> vaccineRecipients = new HashMap<>();
        ArrayList<Integer> vrVaccineTypes = null;
        ArrayList<Account> vrList = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                vaccineRecipients.put(rs.getInt("id"), rs.getInt("account_id"));
            }
            vrVaccineTypes = VaccineCertificateMapper.getVaccineRecipientsByVaccineType(vaccineType, vaccineRecipients);

            for (Integer id : vrVaccineTypes) {
                vrList.add(AccountMapper.findAccountByID(id));
            }

        } catch (SQLException e) {
            System.out.println("VaccineRecipient Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vrList;
    }

}
