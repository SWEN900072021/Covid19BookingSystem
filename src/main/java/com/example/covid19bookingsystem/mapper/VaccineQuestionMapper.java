package com.example.covid19bookingsystem.mapper;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.VaccineType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VaccineQuestionMapper {

    public static void insert(VaccineType vaccineType, Question question) {
        String sql = "INSERT INTO vaccine_question (vaccine_type, question_id) VALUES (?::varchar(50), ?::integer);";
        PreparedStatement statement = null;

        try {
            statement = DBConnection.getDbConnection().prepareStatement(sql);
            statement.setString(2, vaccineType.getName());
            statement.setInt(1, (Integer) question.getId());
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

}
