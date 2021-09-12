package com.example.covid19bookingsystem.mapper;


import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Questionnaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionnaireMapper {
    public void insert(Questionnaire questionnaire) {
        String sql = "INSERT INTO questionnaire (date_taken, outcome) VALUES (?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setDate(1, questionnaire.getDateTaken());
            statement.setString(2, questionnaire.getOutcome());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Questionnaire Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
