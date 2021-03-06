package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchTimeslotController", value = "/searchTimeslot")
public class SearchTimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "vr/searchTimeslot.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean result = processSearchTypeRequest(request, response);
        if (!result) {
            request.setAttribute("failure", "true");
            doGet(request, response);
        }
    }

    private Boolean processSearchTypeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("searchBy").equals("area")) {
            request.getSession().setAttribute("searchBy", "area");
            request.getSession().setAttribute("searchQuery", request.getParameter("queryField"));
            List<Timeslot> timeslots = TimeslotMapper.findTimeslotsByPostCodeAndVaccineType(request.getParameter("queryField"),
                    (String) request.getSession().getAttribute("vaccineType"));
            if (!timeslots.isEmpty()) {
                request.getSession().setAttribute("allAvailableTimeslotDates", timeslots);
                request.getRequestDispatcher("/bookDate").forward(request, response);
                return true;
            }
            return false;
        } else {
            request.getSession().setAttribute("searchBy", "hcp");
            request.getSession().setAttribute("searchQuery", request.getParameter("queryField"));
            List<HealthCareProvider> HCPs = HealthCareProviderMapper.findHealthCareProvidersByName(request.getParameter("queryField"));
            return findTimeslotsHelper(HCPs, request, response);
        }
    }

    private Boolean findTimeslotsHelper(List<HealthCareProvider> HCPs, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HCPs.isEmpty()) {
            List<Timeslot> timeslots = new ArrayList<>();
            for (HealthCareProvider HCP : HCPs) {
                List<Timeslot> timeslotsForHCP = TimeslotMapper.findTimeslotsByHcpAndVaccineType(HCP,
                        (String) request.getSession().getAttribute("vaccineType"));
                if (!timeslotsForHCP.isEmpty()) {
                    timeslots.addAll(timeslotsForHCP);
                }
            }
            if (!timeslots.isEmpty()) {
                request.getSession().setAttribute("allAvailableTimeslotDates", timeslots);
                request.getRequestDispatcher("/bookDate").forward(request, response);
                return true;
            }
        }
        return false;
    }
}