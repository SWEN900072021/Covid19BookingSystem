package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.mapper.HealthCareProviderMapper;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;
import com.example.covid19bookingsystem.utils.EnumUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "homeController", value = "/home")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "/home.jsp";
        if (request.getSession().getAttribute("UoW") != null) {
            request.getSession().removeAttribute("UoW");
        }
        if (request.getSession().getAttribute("success") != null) {
            request.getSession().removeAttribute("success");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if (!currentUserName.equals("admin")) {
                Account account = AccountMapper.findAccountByUsername(currentUserName);
                if (account != null && account.getAccountType() == EnumUtils.AccountType.valueOf("HCP")) {
                    HealthCareProvider hcp = HealthCareProviderMapper.findHealthCareProviderByAccountId(account.getAccountId());
                    request.getSession().setAttribute("userDetails", hcp);
                } else if (account != null && account.getAccountType() == EnumUtils.AccountType.valueOf("VR")) {
                    VaccineRecipient vr = VaccineRecipientMapper.findVaccineRecipientByAccountId(account.getAccountId());
                    request.getSession().setAttribute("userDetails", vr);
                }
            }
        }
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}