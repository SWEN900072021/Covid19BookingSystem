package com.example.covid19bookingsystem.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reset session variables
        if (request.getSession().getAttribute("usernameUsed")!=null){
            request.getSession().removeAttribute("usernameUsed");
        }
        if (request.getSession().getAttribute("vrDetails")!=null){
            request.getSession().removeAttribute("vrDetails");
        }
        if (request.getSession().getAttribute("hcpDetails")!=null) {
            request.getSession().removeAttribute("hcpDetails");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}