package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineType;
import com.example.covid19bookingsystem.mapper.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "chooseVaccineController", value = "/chooseVaccine")
public class ChooseVaccineController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "vr/chooseVaccine.jsp";

        // VaccineTypes display for dropdown list
        ArrayList<VaccineType> allVaccineTypes = VaccineTypeMapper.getAllVaccineTypes();
        request.getSession().setAttribute("allVaccineTypes", allVaccineTypes);
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "vr/chooseVaccine.jsp";

        ForwardVaccineQuestionsForSelection(request, response);
    }

    private void ForwardVaccineQuestionsForSelection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("vaccineType") != null){

            List<Question> questionsForVaccineType = VaccineQuestionMapper.getQuestionsForVaccineType(request.getParameter("vaccineType"));

            request.getSession().setAttribute("vaccineType", request.getParameter("vaccineType"));
            request.getSession().setAttribute("questionNumber", questionsForVaccineType.size());
            request.getSession().setAttribute("questionsToDisplay", questionsForVaccineType);
            request.getRequestDispatcher("vr/vaccineQuestionnaire.jsp").forward(request, response);
        }
    }
}
