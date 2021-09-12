package com.example.covid19bookingsystem.controller;

<<<<<<< Updated upstream
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;
=======
import com.example.covid19bookingsystem.domain.VaccineType;
import com.example.covid19bookingsystem.mapper.VaccineTypeMapper;
>>>>>>> Stashed changes

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
<<<<<<< Updated upstream
import java.sql.Date;

import static com.example.covid19bookingsystem.utils.EnumUtils.VaccineStatus;
import static com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;
=======

import static java.lang.Integer.parseInt;
import static java.lang.Boolean.parseBoolean;
>>>>>>> Stashed changes

@WebServlet(name = "vaccineTypeController", value = "/addVaccineType")
public class VaccineTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< Updated upstream

    }

=======
        VaccineType vaccineType = processVaccineTypeRequest(request);
        new VaccineTypeMapper().insert(vaccineType);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Vaccine type created</h3/");
    }

    private VaccineType processVaccineTypeRequest(HttpServletRequest request) {
        VaccineType vaccineType = new VaccineType();
        vaccineType.setVaccineName(request.getParameter("vaccine_name").toUpperCase());
        vaccineType.setVaccineClass(request.getParameter("vaccine_class"));
        vaccineType.setNumberDoses(parseInt(request.getParameter("dose_number")));
        vaccineType.setMinAge(parseInt(request.getParameter("min_age")));
        vaccineType.setMaxAge(parseInt(request.getParameter("max_age")));
        vaccineType.setBloodProblemRestriction(parseBoolean(request.getParameter("blood_restriction")));

        return vaccineType;
    }
>>>>>>> Stashed changes
}