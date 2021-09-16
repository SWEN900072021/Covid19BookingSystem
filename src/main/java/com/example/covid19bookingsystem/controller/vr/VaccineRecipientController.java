package com.example.covid19bookingsystem.controller.vr;

import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.example.covid19bookingsystem.utils.EnumUtils.Gender.valueOf;

@WebServlet(name = "vaccineRecipientController", value = "/vr/vaccinerecipient")
public class VaccineRecipientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VaccineRecipient vaccineRecipient = processVaccineRecipientRequest(request);
        VaccineRecipientMapper.insert(vaccineRecipient);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Vaccine Recipient created</h3/");
    }

    private VaccineRecipient processVaccineRecipientRequest(HttpServletRequest request) {
        VaccineRecipient vaccineRecipient = new VaccineRecipient();

        // personal details
        vaccineRecipient.setFirstName(request.getParameter("firstName"));
        vaccineRecipient.setLastName(request.getParameter("lastName"));

        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setPostcode(request.getParameter("postcode"));
        address.setState(request.getParameter("state"));
        address.setCountry(request.getParameter("country"));
        vaccineRecipient.setAddress(address);

        vaccineRecipient.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
        vaccineRecipient.setGender(valueOf(request.getParameter("gender")));

        // set contact details
        vaccineRecipient.setPhoneNumber(request.getParameter("phoneNumber"));
        vaccineRecipient.setEmail(request.getParameter("email"));

        return vaccineRecipient;
    }


}