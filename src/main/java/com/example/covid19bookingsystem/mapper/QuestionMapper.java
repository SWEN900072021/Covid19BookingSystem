package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Question;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionMapper {

    public void insert(Question question) {
        String sql = "INSERT INTO question (question, success_answer) VALUES (?, ?);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, question.getQuestion());
            statement.setBoolean(2, question.getSuccessAnswer());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Account Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
