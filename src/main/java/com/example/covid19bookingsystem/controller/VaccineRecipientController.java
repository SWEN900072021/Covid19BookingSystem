package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.example.covid19bookingsystem.utils.EnumUtils.*;
import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@WebServlet(name = "vaccineRecipientController", value = "/vaccinerecipient")
public class VaccineRecipientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VaccineRecipient vaccineRecipient = processVaccineRecipientRequest(request);
        new VaccineRecipientMapper().insert(vaccineRecipient);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Vaccine Recipient created</h3/");
    }

    private VaccineRecipient processVaccineRecipientRequest(HttpServletRequest request) {
        VaccineRecipient vaccineRecipient = new VaccineRecipient();

        // account details
        vaccineRecipient.setUsername(request.getParameter("username"));
        // TODO: need to hash the password
        vaccineRecipient.setPassword(request.getParameter("password"));
        // TODO: implement password requirements check here + direct to error page if either of these fields are null

        // personal details
        vaccineRecipient.setFirstName(request.getParameter("firstName"));
        vaccineRecipient.setLastName(request.getParameter("lastName"));
        vaccineRecipient.setAddress(request.getParameter("address"));
        vaccineRecipient.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
        vaccineRecipient.setGender(request.getParameter("gender"));

        // set contact details
        vaccineRecipient.setPhoneNumber(request.getParameter("phoneNumber"));
        vaccineRecipient.setEmail(request.getParameter("email"));

        // vaccine details
        vaccineRecipient.setVaccineStatus(VaccineStatus.NOT_VACCINATED);

        return vaccineRecipient;
    }


}