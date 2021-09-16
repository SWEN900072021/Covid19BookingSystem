package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.sql.Timestamp.valueOf;

@WebServlet(name = "bookTimeController", value = "/bookTime")
public class BookTimeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("timeClicked") != null && request.getParameter("dateClicked") != null) {
            processBookTimeRequest(request, response);
        }
    }

    private void processBookTimeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateTime = request.getParameter("dateClicked") + " " + request.getParameter("timeClicked") + ":00";
        System.out.println("SELECTED DATETIME: " + dateTime);
        List<Timeslot> allAvailableTimeslotDates =
                (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
        List<HealthCareProvider> HCPs =
                (List<HealthCareProvider>) request.getSession().getAttribute("hcpList");
        HashMap<String, String> confirmationDetails = new HashMap<String, String>();
        for (Timeslot timeslot : allAvailableTimeslotDates) {
            if (Objects.equals(timeslot.getDateTime(), valueOf(dateTime))) {
                confirmationDetails.put("dateTime", dateTime);
                confirmationDetails.put("vaccineType", timeslot.getVaccineType());
                confirmationDetails.put("duration", timeslot.getDuration().toString());
                //confirmationDetails.put("location", timeslot.getLocation());
                for (HealthCareProvider HCP : HCPs) {
                    if (Objects.equals(timeslot.getHealthcareProvider(), HCP.getAccountId())) {
                        HealthCareProvider hcpDetails = HealthCareProviderMapper.findHCPByObject(HCP);
                        confirmationDetails.put("hcpOrgId", hcpDetails.getOrganisationalId().toString());
                        confirmationDetails.put("hcpName", hcpDetails.getHealthCareProviderName());
                        confirmationDetails.put("hcpType", hcpDetails.getHealthCareProviderType().name());
                        confirmationDetails.put("hcpPostcode", hcpDetails.getPostcode());
                        break;
                    }
                }
            }
        }
        request.setAttribute("confirmationDetails", confirmationDetails);
        request.getRequestDispatcher("bookTime.jsp").forward(request, response);
    }

}
