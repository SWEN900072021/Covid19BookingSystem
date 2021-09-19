package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.VaccineRecipient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.covid19bookingsystem.mapper.VaccineCertificateMapper.findVaccineCertificatesByVaccineRecipientId;

@WebServlet(name = "vaccineCertificateController", value = "/vaccineCertificate")
public class VaccineCertificateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userDetails") != null) {
            VaccineRecipient vr = (VaccineRecipient) request.getSession().getAttribute("userDetails");
            List<String> vaccineCertificates = findVaccineCertificatesByVaccineRecipientId(vr.getId());
            request.getSession().setAttribute("vaccineCertificates", vaccineCertificates);

            String view = "/vr/viewVaccineCertificate.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        } else {
            String view = "home";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}