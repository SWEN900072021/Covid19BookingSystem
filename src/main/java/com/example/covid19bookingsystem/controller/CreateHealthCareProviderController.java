package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;
import com.example.covid19bookingsystem.utils.EnumUtils;
import com.example.covid19bookingsystem.utils.EnumUtils.HealthCareProviderType;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createHealthCareProviderController", value = "/createHealthCareProvider")
public class CreateHealthCareProviderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "admin/createHCPAccount.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HealthCareProvider healthCareProviderAccount = processHCPAccount(request);
        processHealthCareProviderRequest(request, healthCareProviderAccount);
        Boolean result = HealthCareProviderMapper.insert(healthCareProviderAccount);

        if (result) {
            request.getRequestDispatcher("/outcome.jsp?success=true").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

    private HealthCareProvider processHCPAccount(HttpServletRequest request) {
        HealthCareProvider hcpAccount = new HealthCareProvider();
        hcpAccount.setUsername(request.getParameter("username"));
        String encodedPassword = new Pbkdf2PasswordEncoder("eduardo", 69, 420).encode(request.getParameter("password"));
        hcpAccount.setPassword(encodedPassword);
        hcpAccount.setAccountType(EnumUtils.AccountType.valueOf(request.getParameter("accountType").toUpperCase()));
        return hcpAccount;
    }

    private void processHealthCareProviderRequest(HttpServletRequest request, HealthCareProvider healthCareProvider) {
        // organisation details
        healthCareProvider.setOrganisationalId(Integer.valueOf(request.getParameter("organisationalId")));
        healthCareProvider.setHealthCareProviderName(request.getParameter("healthCareProviderName"));
        healthCareProvider.setHealthCareProviderType(HealthCareProviderType.valueOf(request.getParameter("healthCareProviderType")));
        healthCareProvider.setPostcode(request.getParameter("postcode"));
    }

}