package com.example.covid19bookingsystem.controller.hcp;

import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@WebServlet(name = "addTimeslotController", value = "/hcp/addTimeslot")
public class AddTimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timeslot timeslot = processTimeslotRequest(request);
        TimeslotMapper.insert(timeslot);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Timeslot created</h3/");
    }

    private Timeslot processTimeslotRequest(HttpServletRequest request) {
        Timeslot timeslot = new Timeslot();

        HealthCareProvider healthCareProvider = new HealthCareProvider();
        healthCareProvider.setAccountId(parseInt(request.getParameter("healthcareProvider")));
        timeslot.setHealthcareProvider(healthCareProvider);

        String dateTime = request.getParameter("date") + " " + request.getParameter("time") + ":00";
        timeslot.setDateTime(valueOf(dateTime));
        String duration = request.getParameter("duration");
        if (duration.isBlank()) {
            duration = "15";
        }
        timeslot.setVaccineType(request.getParameter("vaccineType"));
        timeslot.setDuration(parseInt(duration));

        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setPostcode(request.getParameter("postcode"));
        address.setState(request.getParameter("state"));
        address.setCountry(request.getParameter("country"));
        timeslot.setAddress(address);

        return timeslot;
    }

}