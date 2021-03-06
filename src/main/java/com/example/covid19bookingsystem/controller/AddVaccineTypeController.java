package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.*;
import com.example.covid19bookingsystem.mapper.QuestionMapper;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.mapper.VaccineQuestionMapper;
import com.example.covid19bookingsystem.mapper.VaccineTypeMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

@WebServlet(name = "addVaccineTypeController", value = "/addVaccineType")
public class AddVaccineTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "admin/addVaccineType.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VaccineType vaccineType = processVaccineTypeRequest(request);
        VaccineTypeMapper.insert(vaccineType);

        ArrayList<Question> questions = processQuestionRequest(request);
        for (Question question : questions){
            Question questionWithID = QuestionMapper.insert(question);
            VaccineQuestionMapper.insert(vaccineType, questionWithID);
        }

        request.getRequestDispatcher("/outcome.jsp?success=true").forward(request, response);

    }

    private VaccineType processVaccineTypeRequest(HttpServletRequest request) {
        VaccineType vaccineType = new VaccineType();
        vaccineType.setName(request.getParameter("name").toUpperCase());

        return vaccineType;
    }

    private ArrayList<Question> processQuestionRequest(HttpServletRequest request) {
        ArrayList<Question> questions = new ArrayList<Question>();

        int i;
        int questionNumber = parseInt(request.getParameter("questionNumber"));

        for(i=0; i<questionNumber; i++) {
            if (request.getParameter("question" + i) != null) {
                Question question = new Question();
                question.setQuestion(request.getParameter("question" + i));
                question.setSuccessAnswer(parseBoolean(request.getParameter("selectOption" + i)));
                questions.add(question);
            }
            else{
                return questions;
            }
        }
        return questions;
    }
}