package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;
import com.example.covid19bookingsystem.utils.EnumUtils.HealthCareProviderType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "healthCareProviderController", value = "/healthcareprovider")
public class HealthCareProviderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HealthCareProvider healthCareProvider = processHealthCareProviderRequest(request);
        HealthCareProviderMapper.insert(healthCareProvider);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>HealthCareProvider created</h3/");
    }

    private HealthCareProvider processHealthCareProviderRequest(HttpServletRequest request) {
        HealthCareProvider healthCareProvider = new HealthCareProvider();

        // account details
        healthCareProvider.setUsername(request.getParameter("username"));
        // TODO: need to hash the password
        healthCareProvider.setPassword(request.getParameter("password"));
        // TODO: implement password requirements check here + direct to error page if either of these fields are null

        // organisation details
        healthCareProvider.setOrganisationalId(Integer.valueOf(request.getParameter("organisationalId")));
        healthCareProvider.setHealthCareProviderName(request.getParameter("healthCareProviderName"));
        healthCareProvider.setHealthCareProviderType(HealthCareProviderType.valueOf(request.getParameter("healthCareProviderType")));
        healthCareProvider.setPostcode(request.getParameter("postcode"));

        return healthCareProvider;
    }


}