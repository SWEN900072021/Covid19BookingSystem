package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineQuestionMapper {

    public static void insert(VaccineType vaccineType, Question question) {
        String sql = "INSERT INTO vaccine_question (vaccine_type, question_id) VALUES (?::varchar(50), ?::integer);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType.getName());
            statement.setInt(2, question.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Vaccine Question Mapper Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Question> getQuestionIdsForVaccineType(String vaccineType) {
        String sql = "SELECT question_id FROM vaccine_question WHERE vaccine_type = ?";

        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1, vaccineType);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("question_id"));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println("Vaccine Question Mapper - get questions - Error: " + e.getMessage());
        } finally {
            try {
                DBConnection.close(statement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

}
