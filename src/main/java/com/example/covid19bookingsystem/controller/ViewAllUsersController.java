package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;
import com.example.covid19bookingsystem.mapper.VaccineTypeMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "viewAllUsersController", value = "/viewAllUsers")
public class ViewAllUsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("filterOption")==null){
            ArrayList<Account> accounts = AccountMapper.getUsers();
            request.getSession().setAttribute("accountList", accounts);
            request.getRequestDispatcher("/admin/viewAllUsers.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/admin/viewAllUsers.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("filterOption").equals("ALLVR")){
            ArrayList<Account> accounts = VaccineRecipientMapper.getAllVaccineRecipients();
            request.getSession().setAttribute("accountList", accounts);
            HashMap<Integer,String> vaccineTypes = VaccineRecipientMapper.getVRVaccineTypes();
            request.getSession().setAttribute("vaccineTypes", vaccineTypes);
        }
        else if (request.getParameter("filterOption").equals("ALL")){
            ArrayList<Account> accounts = AccountMapper.getUsers();
            request.getSession().setAttribute("accountList", accounts);
            request.getSession().setAttribute("vaccineTypes", null);
        }
        else if (request.getParameter("filterOption").equals("ALLHCP")){
            request.getSession().setAttribute("vaccineTypes", null);

        }
        else{
            // handle vaccine types
            String vaccineType = request.getParameter("filterOption");
            ArrayList<Account> accounts = VaccineRecipientMapper.getVaccineRecipientsByVaccineType(vaccineType);
        }

        doGet(request, response);
    }
}
