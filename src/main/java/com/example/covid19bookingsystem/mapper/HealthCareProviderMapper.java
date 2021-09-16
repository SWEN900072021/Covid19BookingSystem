package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.utils.EnumUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthCareProviderMapper {

    public static void insert(HealthCareProvider healthCareProvider) {
        String sql = "INSERT INTO health_care_provider (account_id, organisational_id, health_care_provider_name, health_care_provider_type, postcode) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, healthCareProvider.getAccountId());
            statement.setInt(2, healthCareProvider.getOrganisationalId());
            statement.setString(3, healthCareProvider.getHealthCareProviderName());
            statement.setString(4, healthCareProvider.getHealthCareProviderType().toString());
            statement.setString(5, healthCareProvider.getPostcode());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCPs;
    }

    public static List<HealthCareProvider> findHCPByName(String name) {
        String sql = "SELECT id FROM health_care_provider WHERE health_care_provider_name = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<HealthCareProvider> HCPs = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, name);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCPs;
    }

    public static HealthCareProvider findHCPByObject(HealthCareProvider HCP) {
        String sql = "SELECT * FROM health_care_provider WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, HCP.getId());
            rs = statement.executeQuery();
            if (rs.next()) {
                HCP.setOrganisationalId(rs.getInt("organisational_id"));
                HCP.setHealthCareProviderName(rs.getString("health_care_provider_name"));
                HCP.setHealthCareProviderType(EnumUtils.HealthCareProviderType.valueOf(rs.getString("health_care_provider_type").toUpperCase()));
                HCP.setPostcode(rs.getString("postcode"));
            }
        } catch (SQLException e) {
            System.out.println("Timeslot Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCP;
    }

}
