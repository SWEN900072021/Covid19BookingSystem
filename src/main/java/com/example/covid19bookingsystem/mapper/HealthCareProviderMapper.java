package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthCareProviderMapper {

    public static void insert(HealthCareProvider healthCareProvider) {
        String sql = "INSERT INTO health_care_provider (username, password, organisational_id, health_care_provider_name, health_care_provider_type, postcode) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, healthCareProvider.getUsername());
            statement.setString(2, healthCareProvider.getPassword());
            statement.setInt(3, healthCareProvider.getOrganisationalId());
            statement.setString(4, healthCareProvider.getHealthCareProviderName());
            statement.setString(5, healthCareProvider.getHealthCareProviderType().toString());
            statement.setString(6, healthCareProvider.getPostcode());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<HealthCareProvider> findHCPByPostCode(String postcode) {
        String sql = "SELECT id FROM health_care_provider WHERE postcode = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<HealthCareProvider> HCPs = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, postcode);
            rs = statement.executeQuery();
            while (rs.next()) {
                HealthCareProvider HCP = new HealthCareProvider();
                HCP.setId(rs.getInt("id"));
                HCPs.add(HCP);
            }
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCPs;
    }

}
