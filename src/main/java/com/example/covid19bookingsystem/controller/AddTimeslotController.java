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
import java.util.Locale;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@WebServlet(name = "addTimeslotController", value = "/addTimeslot")
public class AddTimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timeslot timeslot = processTimeslotRequest(request);
        TimeslotMapper.insert(timeslot);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Timeslot created</h3/");
    }

    private Timeslot processTimeslotRequest(HttpServletRequest request) {
        Timeslot timeslot = new Timeslot();

        timeslot.setHealthcareProvider(parseInt(request.getParameter("healthcareProvider")));
        String dateTime = request.getParameter("date") + " " + request.getParameter("time") + ":00";
        timeslot.setDateTime(valueOf(dateTime));
        String duration = request.getParameter("duration");
        if (duration.isBlank()) {
            duration = "15";
        }
        timeslot.setVaccinationType(VaccineType.valueOf(request.getParameter("vaccineType").toUpperCase()));
        timeslot.setDuration(parseInt(duration));
        timeslot.setLocation(request.getParameter("location"));

        return timeslot;
    }

}