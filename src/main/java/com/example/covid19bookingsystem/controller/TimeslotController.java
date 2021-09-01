package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "timeslotController", value = "/timeslot")
public class TimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimeslotMapper timeslotMapper = new TimeslotMapper();
        timeslotMapper.insert(request);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Timeslot created</h3/");
    }

}