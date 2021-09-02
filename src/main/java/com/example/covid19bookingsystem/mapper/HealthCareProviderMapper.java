package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.HealthCareProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class HealthCareProviderMapper {

    public void insert(HealthCareProvider healthCareProvider) {
        String sql = "INSERT INTO healthcare_provider (organisational_id, account, hcp_name, hcp_type, postcode) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setInt(1, healthCareProvider.getOrganisationalId());
            statement.setInt(2, healthCareProvider.getAccount());
            statement.setString(3, healthCareProvider.getName());
            statement.setObject(4, healthCareProvider.getHcpType(), Types.OTHER);
            statement.setInt(5, healthCareProvider.getPostcode());
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
