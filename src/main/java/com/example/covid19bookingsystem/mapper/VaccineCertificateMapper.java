package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static List<String> findVaccineCertificatesByVaccineRecipientId(Integer id) {
        String sql = "SELECT * FROM vaccine_certificate WHERE vaccine_recipient = ?;";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<String> vaccineCertificates = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String vaccineType = rs.getString("vaccine_type");
                vaccineCertificates.add(vaccineType);
            }

        } catch (SQLException e) {
            System.out.println("Vaccine Certificate Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return vaccineCertificates;
    }

    public static HashMap<Integer,String> getAllCertificates(HashMap<Integer, Integer> vaccineRecipients){
        String sql = "SELECT * FROM vaccine_certificate;";
        PreparedStatement statement = null;
        ResultSet rs = null;

        HashMap<Integer,String> vaccineTypes = new HashMap<>();
        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()){
                Integer vrAccountId = vaccineRecipients.get(rs.getInt("vaccine_recipient"));
                if (vaccineTypes.containsKey(vrAccountId)) {
                    String newVaccineType = vaccineTypes.get(vrAccountId);
                    String sumVaccineTypes = String.format("%s, %s", rs.getString("vaccine_type"), newVaccineType);
                    vaccineTypes.put(vrAccountId, sumVaccineTypes);
                } else {
                    vaccineTypes.put(vrAccountId, rs.getString("vaccine_type"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Vaccine Certificate Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vaccineTypes;
    }

    public static ArrayList<Integer> getVaccineRecipientsByVaccineType(String vaccineType, HashMap<Integer, Integer> vaccineRecipients){
        String sql = "SELECT * FROM vaccine_certificate WHERE vaccine_type=?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> vaccineTypes = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType);
            rs = statement.executeQuery();
            while (rs.next()){
                Integer vrAccountId = vaccineRecipients.get(rs.getInt("vaccine_recipient"));
                vaccineTypes.add(vrAccountId);
            }

        } catch (SQLException e) {
            System.out.println("Vaccine Certificate Mapper Error: " + e.getMessage());
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
