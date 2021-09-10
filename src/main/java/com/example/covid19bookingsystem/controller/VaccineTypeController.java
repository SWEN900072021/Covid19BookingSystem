package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.example.covid19bookingsystem.utils.EnumUtils.VaccineStatus;
import static com.example.covid19bookingsystem.utils.EnumUtils.VaccineType;

@WebServlet(name = "vaccineTypeController", value = "/addVaccineType")
public class VaccineTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}