package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class HealthCareProviderMapper {

    public void insert(HealthCareProvider healthCareProvider) {
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
