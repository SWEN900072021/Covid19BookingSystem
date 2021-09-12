package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Questionnaire;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import com.example.covid19bookingsystem.mapper.QuestionnaireMapper;

@WebServlet(name = "questionnaireController", value = "/questionnaire")
public class QuestionnaireController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Questionnaire questionnaire = processQuestionnaireRequest(request);
        new QuestionnaireMapper().insert(questionnaire);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Questionnaire created</h3/");
    }

    private Questionnaire processQuestionnaireRequest(HttpServletRequest request) {
        Questionnaire questionnaire = new Questionnaire();

        long currentDate = System.currentTimeMillis();
        questionnaire.setDateTaken(new Date(currentDate));

<<<<<<< Updated upstream
=======
        // check blood problems to provide suitable vaccine
        if(request.getParameter("blood_problem").equals("yes")){
            questionnaire.setBloodProblem(true);
        }

>>>>>>> Stashed changes
        if(request.getParameter("allergy_medication").equals("no")
                && request.getParameter("vaccine_reaction").equals("no")
                && request.getParameter("long_term_health_problems").equals("no")
                && request.getParameter("immune_problems").equals("no")
                && request.getParameter("family_immune_problems").equals("no")
                && request.getParameter("immune_problem").equals("no")
                && request.getParameter("nervous_problem").equals("no")
<<<<<<< Updated upstream
                && request.getParameter("blood_problem").equals("no")
=======
>>>>>>> Stashed changes
        ){
            questionnaire.setOutcome("Approved");
        } else {
            questionnaire.setOutcome("Refused");
        }

        return questionnaire;
    }


}