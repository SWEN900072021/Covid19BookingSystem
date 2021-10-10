package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.mapper.VaccineCertificateMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.covid19bookingsystem.mapper.TimeslotMapper.findBookedTimeslotsByOrganisationalId;

@WebServlet(name = "recordVaccinationController", value = "/recordVaccination")
public class RecordVaccinationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userDetails") != null) {
            HealthCareProvider hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
            List<Timeslot> timeslots = findBookedTimeslotsByOrganisationalId(hcp.getOrganisationalId());
            if (!timeslots.isEmpty()) {
                request.getSession().setAttribute("bookedTimeslots", timeslots);

                String view = "/hcp/recordVaccination.jsp";
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            }
            else {
                request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timeslot timeslotSubmitted = null;

        if (request.getParameter("timeslotSubmitted") != null) {
            Integer id = Integer.valueOf(request.getParameter("timeslotSubmitted"));
            List<Timeslot> timeslots = (List<Timeslot>) request.getSession().getAttribute("bookedTimeslots");

            for(Timeslot timeslot: timeslots) {
                if (timeslot.getId().equals(id)) {
                    timeslotSubmitted = timeslot;
                }
            }
            
            String result = TimeslotMapper.recordVaccinationCompleted(timeslotSubmitted);

            if ("SUCCESS".equals(result)) {
                VaccineCertificateMapper.insert(timeslotSubmitted.getVaccineRecipient().getId(), timeslotSubmitted.getVaccineType());
                request.getSession().setAttribute("success", "true");
            }
            else if ("VERSION_MISMATCH".equals(result)) {
                request.getSession().setAttribute("success", "version_mismatch");
            }
            else {
                request.getSession().setAttribute("success", "false");
            }

            doGet(request, response);
        }
    }
}