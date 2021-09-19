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
            List<Timeslot> timeslots = TimeslotMapper.findTimeslotsByPostCode(request.getParameter("queryField"),
                    request.getParameter("vaccineType"));
            System.out.println("In area");
            if (!timeslots.isEmpty()) {
                System.out.println("In if");
                request.getSession().setAttribute("allAvailableTimeslotDates", timeslots);
                request.getRequestDispatcher("/bookDate").forward(request, response);
                return true;
            }
            return false;
        } else {
            List<HealthCareProvider> HCPs = HealthCareProviderMapper.findHCPByName(request.getParameter("queryField"));
            return findTimeslotsHelper(HCPs, request, response);
        }
    }

    private Boolean findTimeslotsHelper(List<HealthCareProvider> HCPs, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HCPs.isEmpty()) {
            List<Timeslot> timeslots = new ArrayList<>();
            for (HealthCareProvider HCP : HCPs) {
                List<Timeslot> timeslotsForHCP = TimeslotMapper.findTimeslotByHcpAndVaccineType(HCP, request.getParameter("vaccineType"));
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
