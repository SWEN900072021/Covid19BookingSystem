package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchTypeController", value = "/searchType")
public class SearchTypeController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean result = processTimeslotRequest(request, response);
        if (!result) {
            request.getRequestDispatcher("searchType.jsp?failure=true").forward(request, response);
        }
    }

    private Boolean processTimeslotRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("searchBy").equals("area")) {
            List<HealthCareProvider> HCPs =
                    HealthCareProviderMapper.findHCPByPostCode(request.getParameter("queryField"));
            if (!HCPs.isEmpty()) {
                request.getSession().setAttribute("hcpList", HCPs);
                request.getRequestDispatcher("/bookDate").forward(request, response);
                return true;
            }
            else {
                return false;
            }
        }
        else  {
            return false;
        }
    }
}
