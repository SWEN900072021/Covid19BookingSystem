package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.covid19bookingsystem.mapper.TimeslotMapper.findTimeslotByHcpAndStatus;

@WebServlet(name = "recordVaccinationController", value = "/recordVaccination")
public class RecordVaccinationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("userDetails") != null) {
            HealthCareProvider hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
            List<Timeslot> timeslots = findTimeslotByHcpAndStatus(hcp.getId());
            request.getSession().setAttribute("bookedTimeslots", timeslots);

            String view = "/hcp/recordVaccination.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        }
        else {
            String view = "home";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("timeslotSubmitted") != null) {
            System.out.println(request.getParameter("timeslotSubmitted"));
            // update timeslot with id -> COMPLETED
            // add row in vaccine_certificate table with vr id and vaccine type
            doGet(request, response);
        }
    }
}