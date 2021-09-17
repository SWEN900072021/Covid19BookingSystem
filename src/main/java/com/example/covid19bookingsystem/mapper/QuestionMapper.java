package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper {

    public static void insert(Question question) {
        String sql = "INSERT INTO question (question, success_answer) VALUES (?, ?) RETURNING id;";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, question.getQuestion());
            statement.setBoolean(2, question.getSuccessAnswer());

            rs = statement.executeQuery();
            if (rs.next()) {
                question.setId(rs.getInt(1));
                System.out.println(rs.getInt(1));
            }


        } catch (SQLException e) {
            System.out.println("Question Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
