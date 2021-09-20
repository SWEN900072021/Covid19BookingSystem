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

@WebServlet(name = "viewAllAvailableTimeslotsController", value = "/viewAllAvailableTimeslots")
public class ViewAllTimeslotsController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Timeslot> availableTimeslots = TimeslotMapper.findAllAvailableTimeslots();
        if (!availableTimeslots.isEmpty()) {
            String view = "admin/viewAllAvailableTimeslots.jsp";
            request.getSession().setAttribute("unbookedTimeslots", availableTimeslots);
            request.getRequestDispatcher(view).forward(request, response);
        }
        else {
            request.getRequestDispatcher("home").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
