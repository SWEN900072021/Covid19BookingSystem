package com.example.covid19bookingsystem.controller.admin;

import com.example.covid19bookingsystem.domain.*;
import com.example.covid19bookingsystem.mapper.QuestionMapper;
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

@WebServlet(name = "addVaccineType", value = "/addVaccineType")
public class addVaccineType extends HttpServlet {

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
            QuestionMapper.insert(question);
            VaccineQuestionMapper.insert(vaccineType, question);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Vaccine Type added</h3/");

    }

    private VaccineType processVaccineTypeRequest(HttpServletRequest request) {
        VaccineType vaccineType = new VaccineType();
        vaccineType.setName(request.getParameter("name"));

        return vaccineType;
    }

    private ArrayList<Question> processQuestionRequest(HttpServletRequest request) {
        ArrayList<Question> questions = new ArrayList<Question>();

        int i;

        for(i=0; i<10; i++) {
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