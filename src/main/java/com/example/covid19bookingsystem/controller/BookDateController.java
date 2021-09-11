package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookDateController", value = "/bookDate")
public class BookDateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Timeslot> allAvailableTimeslotDates = TimeslotMapper.getAllAvailableTimeslotDates();
        request.getSession().setAttribute("allAvailableTimeslotDates", allAvailableTimeslotDates);
        request.getRequestDispatcher("/bookDate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("dateClicked") != null) {
            System.out.println("DATE CLICKED: " + request.getParameter("dateClicked"));
            request.getRequestDispatcher("/bookTime.jsp").forward(request, response);
        }
    }

}
