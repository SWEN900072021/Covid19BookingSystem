package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.utils.MappingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "timeslotController", value = "/timeslot")
public class TimeslotController extends HttpServlet {

    private final MappingUtils mapper = new MappingUtils();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timeslot timeslot = mapper.requestToTimeslot(request);
        TimeslotMapper timeslotMapper = new TimeslotMapper();
        timeslotMapper.insert(timeslot);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Timeslot created</h3/");
    }

}