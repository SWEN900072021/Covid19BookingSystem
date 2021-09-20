<%@ page import="com.example.covid19bookingsystem.domain.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.covid19bookingsystem.utils.EnumUtils" %>
<%@ page import="com.example.covid19bookingsystem.domain.VaccineRecipient" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.css' rel='stylesheet' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>View All Users</title>
    <%
        ArrayList<Account> accounts = (ArrayList<Account>) request.getSession().getAttribute("accountList");
        HashMap<Integer, String> vrVaccineTypes = null;
        if (request.getSession().getAttribute("vaccineTypes")!=null && request.getParameter("filterOption")!=null){
            vrVaccineTypes = (HashMap<Integer, String>) request.getSession().getAttribute("vaccineTypes");
        }

    %>
</head>
<body>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        View All Users
    </h4>
    <div class="card text-center border-secondary mb-3" style="width: 35rem;margin: 0 auto;float: none;margin-bottom: 2px;">
        <div class="card-body">
            <legend class="col-form-label col-sm-4 pt-0"><strong>Filter By:</strong></legend>
            <form action = "viewAllUsers" method = "post">
                <input
                        type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"
                />
                <fieldset class="row mb-3">
                    <div class="row">
                        <div class="col-sm-8" style="text-align: left">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id="AllVR" value="ALLVR">
                                <label class="form-check-label" for="AllVR">
                                    All Vaccine Recipients
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id="AZ" value="ASTRAZENECA">
                                <label class="form-check-label" for="AZ">
                                    Astrazeneca Vaccine Recipients
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id="Pfizer" value="PFIZER">
                                <label class="form-check-label" for="Pfizer">
                                    Pfizer Vaccine Recipients
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="row mb-3">
                    <div class="col-sm-12">
                        <button
                                type="submit"
                                class="btn btn-dark"
                                data-bs-toggle="tooltip"
                                data-bs-placement="right"
                                title="Confirm Filter Requirements"
                        >
                            Filter
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Account Type</th>
                    <th scope="col">Vaccine Type</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Account account: accounts){
                        String vaccineType = "N/A";
                        if (account.getAccountType()==EnumUtils.AccountType.VR && vrVaccineTypes!=null){
                            VaccineRecipient vr = (VaccineRecipient) account;
                            if (vrVaccineTypes.get(vr.getId())!=null){
                                vaccineType = vrVaccineTypes.get(vr.getId());
                            }
                        }
                %>
                <tr>
                    <td><%= account.getAccountId().toString()%></td>
                    <td><%= account.getUsername()%></td>
                    <td><%= account.getAccountType().toString()%></td>
                    <td><%= vaccineType%></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
