package com.example.covid19bookingsystem.mapper;


import com.example.covid19bookingsystem.Questionaire;
import com.example.covid19bookingsystem.VaccineCertificate;
import com.example.covid19bookingsystem.datasource.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionaireMapper {
    public void insert(Questionaire questionaire) {
        String sql = "INSERT INTO questionaire (id, personId, first_name, last_name, eligibility, result, date_created) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement findStatement = null;

        try {
            findStatement = DBConnection.getDbConnection().prepareStatement(sql);
            findStatement.setInt(1, questionaire.getId());
            findStatement.setInt(2, questionaire.getPersonId());
            findStatement.setString(3, questionaire.getFirstName());
            findStatement.setString(4, questionaire.getLastName());
            findStatement.setString(5, questionaire.getEligibility());
            findStatement.setString(6, questionaire.getResult());
            findStatement.setString(7, questionaire.getDateTaken().toString());
            findStatement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
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
