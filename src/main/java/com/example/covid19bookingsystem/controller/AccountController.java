package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.utils.EnumUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "accountController", value = "/account")
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from Post method in LoginServlet");
        String user = request.getParameter("userName");
        String pass = request.getParameter("passWord");
        Account account = new Account(user, pass, EnumUtils.AccountType.VR);
        AccountMapper accountMapper = new AccountMapper();
        accountMapper.insert(account);
        PrintWriter writer = response.getWriter();
        writer.println("<h3> Hello from Post: Your user name is: " + user + "</h3>");
    }

}