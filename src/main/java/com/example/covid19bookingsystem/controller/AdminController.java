package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Admin;
import com.example.covid19bookingsystem.mapper.AdminMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminController", value = "/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = processAdminRequest(request);
        new AdminMapper().insert(admin);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Vaccine Recipient created</h3/");
    }

    private Admin processAdminRequest(HttpServletRequest request) {
        Admin admin = new Admin();

        // account details
        admin.setUsername(request.getParameter("username"));
        // TODO: need to hash the password
        admin.setPassword(request.getParameter("password"));
        // TODO: implement password requirements check here + direct to error page if either of these fields are null

        return admin;
    }

}