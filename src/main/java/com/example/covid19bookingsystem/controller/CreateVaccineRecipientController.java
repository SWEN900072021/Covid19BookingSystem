package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.VaccineRecipient;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import com.example.covid19bookingsystem.mapper.VaccineRecipientMapper;
import com.example.covid19bookingsystem.utils.EnumUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.example.covid19bookingsystem.utils.EnumUtils.Gender.valueOf;

@WebServlet(name = "createVaccineRecipientController", value = "/createVaccineRecipient")
public class CreateVaccineRecipientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "createVRAccount.jsp";
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VaccineRecipient vrAccount = processVRAccount(request);
        processVaccineRecipientRequest(request, vrAccount);
        String result = VaccineRecipientMapper.insert(vrAccount);

        if ("SUCCESS".equals(result)) {
            request.getRequestDispatcher("/outcome.jsp?success=true").forward(request, response);
        }
        else if ("USERNAME_TAKEN".equals(result)){
            request.getSession().setAttribute("usernameUsed", true);
            request.getSession().setAttribute("vrDetails", vrAccount);
            doGet(request, response);
        }
        else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

    private VaccineRecipient processVRAccount(HttpServletRequest request) {
        VaccineRecipient vrAccount = new VaccineRecipient();
        vrAccount.setUsername(request.getParameter("username"));
        String encodedPassword = new Pbkdf2PasswordEncoder("eduardo", 69, 420).encode(request.getParameter("password"));
        vrAccount.setPassword(encodedPassword);
        vrAccount.setAccountType(EnumUtils.AccountType.valueOf(request.getParameter("accountType").toUpperCase()));
        return vrAccount;
    }

    private void processVaccineRecipientRequest(HttpServletRequest request, VaccineRecipient vrAccount) {

        // personal details
        vrAccount.setFirstName(request.getParameter("firstName"));
        vrAccount.setLastName(request.getParameter("lastName"));

        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setPostcode(request.getParameter("postcode"));
        address.setState(request.getParameter("state"));
        address.setCountry(request.getParameter("country"));
        vrAccount.setAddress(address);

        vrAccount.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
        vrAccount.setGender(valueOf(request.getParameter("gender").toUpperCase()));

        // set contact details
        vrAccount.setPhoneNumber(request.getParameter("phoneNumber"));
        vrAccount.setEmailAddress(request.getParameter("email"));
    }


}