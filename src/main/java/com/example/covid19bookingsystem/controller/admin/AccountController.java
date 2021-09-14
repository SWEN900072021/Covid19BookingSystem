package com.example.covid19bookingsystem.controller.admin;

import com.example.covid19bookingsystem.domain.Account;
import com.example.covid19bookingsystem.mapper.AccountMapper;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.covid19bookingsystem.utils.EnumUtils.AccountType.valueOf;

@WebServlet(name = "accountController", value = "/admin/account")
public class AccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = processAccountRequest(request);
        new AccountMapper().insert(account);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("<h3>Account created</h3/");
    }

    private Account processAccountRequest(HttpServletRequest request) {
        Account account = new Account();

        account.setUsername(request.getParameter("username"));
        String encodedPassword = new Pbkdf2PasswordEncoder("eduardo", 69, 420).encode(request.getParameter("password"));
        account.setPassword(encodedPassword);
        String accountType = request.getParameter("accountType");
        account.setAccountType(valueOf(accountType));
        return account;
    }

}