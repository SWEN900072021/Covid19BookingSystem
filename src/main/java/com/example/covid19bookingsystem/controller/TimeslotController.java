package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@WebServlet(name = "timeslotController", value = "/timeslot")
public class TimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DATE CLICKED: " + request.getParameter("dateClicked"));
//        Timeslot timeslot = processTimeslotRequest(request);
//        new TimeslotMapper().insert(timeslot);
//
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println("<h3>Timeslot created</h3/");
    }

    private Timeslot processTimeslotRequest(HttpServletRequest request) {
        Timeslot timeslot = new Timeslot();

        timeslot.setVaccineRecipient(parseInt(request.getParameter("vaccineRecipient")));
        timeslot.setHealthcareProvider(parseInt(request.getParameter("healthcareProvider")));
        timeslot.setQuestionnaire(parseInt(request.getParameter("questionnaire")));

        timeslot.setVaccinationType(VaccineType.valueOf(request.getParameter("vaccinationType")));
        String dateTime = request.getParameter("date") + " " + request.getParameter("time") + ":00";
        timeslot.setDateTime(valueOf(dateTime));
        String duration = request.getParameter("duration");
        if (duration.isBlank()) {
            duration = "15";
        }
        timeslot.setDuration(parseInt(duration));
        timeslot.setLocation(request.getParameter("location"));

        return timeslot;
    }

}