package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.utils.EnumUtils;
import com.example.covid19bookingsystem.utils.UnitOfWork;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@WebServlet(name = "addTimeslotController", value = "/addTimeslot")
public class AddTimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("UoW") == null){
            request.getSession().setAttribute("UoW", new UnitOfWork());
        }
        request.getRequestDispatcher("/hcp/addTimeslot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("commit") != null){
            try {
                //commit UoW
                UnitOfWork uow = (UnitOfWork) request.getSession().getAttribute("UoW");
                if (uow != null){
                    if(uow.getNewObjList().isEmpty()) {
                        request.setAttribute("alert", "true");
                        doGet(request, response);
                    }
                    else {
                        uow.commit();
                        request.getSession().setAttribute("UoW", null);
                        request.getRequestDispatcher("/home").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            request.setAttribute("alert", null);
            Timeslot timeslot = processTimeslotRequest(request);
            UnitOfWork uow = (UnitOfWork) request.getSession().getAttribute("UoW");
            uow.registerNew(timeslot);
            request.getSession().setAttribute("UoW", uow);
            doGet(request, response);
        }
    }

    private Timeslot processTimeslotRequest(HttpServletRequest request) {
        Timeslot timeslot = new Timeslot();

        HealthCareProvider healthCareProvider = new HealthCareProvider();
        healthCareProvider.setId(parseInt(request.getParameter("healthcareProvider")));
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
        timeslot.setStatus(EnumUtils.TimeslotStatus.UNBOOKED);

        return timeslot;
    }

}