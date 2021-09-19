package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.VaccineType;
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
import java.util.List;

@WebServlet(name = "vaccineQuestionnaireController", value = "/vaccineQuestionnaire")
public class VaccineQuestionnaireController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "vr/vaccineQuestionnaire.jsp";

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "vr/vaccineQuestionnaire.jsp";

        List<Question> questionsForVaccineType
                = VaccineQuestionMapper.getQuestionIdsForVaccineType(request.getParameter("vaccineType"));

        List<Question> questionsToDisplay = new ArrayList<>();

        for (Question question : questionsForVaccineType) {

            Question questionToDisplay = QuestionMapper.getQuestionById(question);
            questionsToDisplay.add(questionToDisplay);

        }

        int i = 1;
        boolean questionnairePassed = true;

        for (Question q: questionsToDisplay) {
            if (q.getSuccessAnswer() != Boolean.parseBoolean(request.getParameter("vaccineQuestionAnswer"+i))){
                questionnairePassed = false;
            }
            i++;
        }

        if (questionnairePassed){
            request.getSession().setAttribute("vaccineType", request.getParameter("vaccineType"));
            request.getRequestDispatcher("vr/searchTimeslot.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("questionnairePassed", questionnairePassed);
            request.getRequestDispatcher("vr/chooseVaccine.jsp").forward(request, response);
        }


    }
}
