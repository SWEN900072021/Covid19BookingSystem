package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        if (request.getParameter("timeslotId") != null) {
            processBookTimeRequest(request, response);
        }
        else if (request.getParameter("confirmed") != null) {
            submitTimeslotRequest(request, response);
        }
        else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

    private void submitTimeslotRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("chosenTimeslot") != null &&
                request.getSession().getAttribute("userDetails") != null) {
            Timeslot timeslot = (Timeslot) request.getSession().getAttribute("chosenTimeslot");
            VaccineRecipient vr = (VaccineRecipient) request.getSession().getAttribute("userDetails");
            String result = TimeslotMapper.bookTimeslot(timeslot, vr);
            if ("SUCCESS".equals(result)) {
                request.getRequestDispatcher("/outcome.jsp?result=success").forward(request, response);
            }
            else if ("VERSION_MISMATCH".equals(result)) {
                request.getRequestDispatcher("/outcome.jsp?result=version_mismatch").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/outcome.jsp?result=error").forward(request, response);
            }
        }
    }

    private void processBookTimeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
        List<Timeslot> allAvailableTimeslotDates =
                (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
        HashMap<String, String> confirmationDetails = new HashMap<String, String>();
        for (Timeslot timeslot : allAvailableTimeslotDates) {
            if (timeslot.getId() == Integer.parseInt(request.getParameter("timeslotId"))) {
                confirmationDetails.put("dateTime", dateTimeFormatter.format(timeslot.getDateTime().getTime()).toUpperCase());
                confirmationDetails.put("vaccineType", timeslot.getVaccineType());
                confirmationDetails.put("duration", timeslot.getDuration().toString());
                confirmationDetails.put("location", timeslot.getAddress().getFullAddress());
                confirmationDetails.put("hcpOrgId", timeslot.getHealthcareProvider().getOrganisationalId().toString());
                confirmationDetails.put("hcpName", timeslot.getHealthcareProvider().getHealthCareProviderName());
                confirmationDetails.put("hcpType", timeslot.getHealthcareProvider().getHealthCareProviderType().name());
                confirmationDetails.put("hcpPostcode", timeslot.getHealthcareProvider().getPostcode());
                confirmationDetails.put("version", timeslot.getVersion().toString());
                request.getSession().setAttribute("chosenTimeslot", timeslot);
            }
        }
        if (request.getSession().getAttribute("chosenTimeslot") != null) {
            request.setAttribute("confirmationDetails", confirmationDetails);
            request.getRequestDispatcher("vr/bookTime.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

}
