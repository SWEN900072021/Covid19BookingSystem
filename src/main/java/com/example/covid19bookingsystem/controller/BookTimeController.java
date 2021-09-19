package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

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
        String view = "vr/bookTime.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("timeClicked") != null && request.getParameter("dateClicked") != null) {
            processBookTimeRequest(request, response);
        }
        if (request.getParameter("confirmed") != null) {
            submitTimeslotRequest(request, response);
        }
    }

    private void submitTimeslotRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("chosenTimeslot") != null &&
                request.getSession().getAttribute("userDetails") != null) {
            Timeslot timeslot = (Timeslot) request.getSession().getAttribute("chosenTimeslot");
            VaccineRecipient vr = (VaccineRecipient) request.getSession().getAttribute("userDetails");
            TimeslotMapper.book(timeslot, vr);
            response.sendRedirect("home");
        }
    }

    private void processBookTimeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateTime = request.getParameter("dateClicked") + " " + request.getParameter("timeClicked") + ":00";
        List<Timeslot> allAvailableTimeslotDates =
                (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
        HashMap<String, String> confirmationDetails = new HashMap<String, String>();
        for (Timeslot timeslot : allAvailableTimeslotDates) {
            if (Objects.equals(timeslot.getDateTime(), valueOf(dateTime))) {
                confirmationDetails.put("dateTime", dateTime);
                confirmationDetails.put("vaccineType", timeslot.getVaccineType());
                confirmationDetails.put("duration", timeslot.getDuration().toString());
                confirmationDetails.put("location", timeslot.getAddress().getFullAddress());
                confirmationDetails.put("hcpOrgId", timeslot.getHealthcareProvider().getOrganisationalId().toString());
                confirmationDetails.put("hcpName", timeslot.getHealthcareProvider().getHealthCareProviderName());
                confirmationDetails.put("hcpType", timeslot.getHealthcareProvider().getHealthCareProviderType().name());
                confirmationDetails.put("hcpPostcode", timeslot.getHealthcareProvider().getPostcode());
                request.getSession().setAttribute("chosenTimeslot", timeslot);
            }
        }
        request.setAttribute("confirmationDetails", confirmationDetails);
        request.getRequestDispatcher("vr/bookTime.jsp").forward(request, response);
    }

}
