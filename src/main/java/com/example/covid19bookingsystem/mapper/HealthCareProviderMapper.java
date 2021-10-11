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

    public static String insert(HealthCareProvider healthCareProvider) {
        String sql = "INSERT INTO health_care_provider (account_id, organisational_id, health_care_provider_name, health_care_provider_type, postcode) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        String result = AccountMapper.insert(healthCareProvider);

        if ("SUCCESS".equals(result)) {
            try {
                statement = DBConnection.getDbConnection().prepareStatement(sql);
                statement.setInt(1, healthCareProvider.getAccountId());
                statement.setString(2, healthCareProvider.getOrganisationalId());
                statement.setString(3, healthCareProvider.getHealthCareProviderName());
                statement.setString(4, healthCareProvider.getHealthCareProviderType().toString());
                statement.setString(5, healthCareProvider.getPostcode());
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

    public static List<HealthCareProvider> findHealthCareProvidersByPostCode(String postcode) {
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
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCPs;
    }

    public static List<HealthCareProvider> findHealthCareProvidersByName(String name) {
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
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return HCPs;
    }

    public static HealthCareProvider findHealthCareProviderById(Integer id) {
        String sql = "SELECT * FROM health_care_provider WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        HealthCareProvider hcp = new HealthCareProvider();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                hcp.setOrganisationalId(rs.getString("organisational_id"));
                hcp.setHealthCareProviderName(rs.getString("health_care_provider_name"));
                hcp.setHealthCareProviderType(EnumUtils.HealthCareProviderType.valueOf(rs.getString("health_care_provider_type").toUpperCase()));
                hcp.setPostcode(rs.getString("postcode"));
            }
        } catch (SQLException e) {
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hcp;
    }

    public static HealthCareProvider findHealthCareProviderByAccountId(Integer accountId) {
        String sql = "SELECT * FROM health_care_provider WHERE account_id = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;

        HealthCareProvider hcp = new HealthCareProvider();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            rs = statement.executeQuery();
            if (rs.next()) {
                hcp.setId(rs.getInt("id"));
                hcp.setOrganisationalId(rs.getString("organisational_id"));
                hcp.setHealthCareProviderName(rs.getString("health_care_provider_name"));
                hcp.setHealthCareProviderType(EnumUtils.HealthCareProviderType.valueOf(rs.getString("health_care_provider_type").toUpperCase()));
                hcp.setPostcode(rs.getString("postcode"));
            }
        } catch (SQLException e) {
            System.out.println("HealthCareProvider Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hcp;
    }

}
