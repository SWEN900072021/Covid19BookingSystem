<%@ page import="com.example.covid19bookingsystem.domain.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.covid19bookingsystem.utils.EnumUtils" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.covid19bookingsystem.mapper.VaccineTypeMapper" %>
<%@ page import="com.example.covid19bookingsystem.domain.VaccineType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
            rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
            crossorigin="anonymous"
    >
    <title>View All Users</title>
    <%
        ArrayList<Account> accounts = (ArrayList<Account>) request.getSession().getAttribute("accountList");
        HashMap<Integer, String> vrVaccineTypes = null;
        if (request.getSession().getAttribute("vaccineTypes")!=null ){
            vrVaccineTypes = (HashMap<Integer, String>) request.getSession().getAttribute("vaccineTypes");
        }
        ArrayList<VaccineType> vaccineTypes = VaccineTypeMapper.getAllVaccineTypes();

    %>
</head>
<body>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        View All Users
    </h4>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 35rem;margin: 0 auto;float: none;margin-bottom: 2px;">
        <div class="card-body">
            <legend class="col-form-label col-sm-4 pt-0" style="display: flex;"><strong>Filter By:</strong></legend>
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
                                <input class="form-check-input" type="radio" name="filterOption" id="All" value="ALL" checked>
                                <label class="form-check-label" for="All">
                                    All Users
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id="AllHCP" value="ALLHCP">
                                <label class="form-check-label" for="AllHCP">
                                    All Health Care Providers
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id="AllVR" value="ALLVR">
                                <label class="form-check-label" for="AllVR">
                                    All Vaccine Recipients
                                </label>
                            </div>
                            <%
                                for (VaccineType vaccineType: vaccineTypes){
                            %>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterOption" id=<%= vaccineType.getName()%> value=<%= vaccineType.getName()%>>
                                <label class="form-check-label" for=<%= vaccineType.getName()%>>
                                    <%= vaccineType.getName()%> Vaccine Recipients
                                </label>
                            </div>
                            <%
                                }
                            %>
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
    <table class="table table-bordered table-striped table-hover"
           style="width: 70rem;margin: 0 auto;float: none;margin-bottom: 10px;border: 1px">
        <thead class="table-dark">
            <tr style="text-align: center">
                <th scope="col">Username</th>
                <th scope="col">Account Type</th>
                <th scope="col">Vaccine Received</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Account account: accounts){
                String vaccineType = "N/A";
                if (account.getAccountType()==EnumUtils.AccountType.VR && vrVaccineTypes!=null){

                    if (vrVaccineTypes.get(account.getAccountId())!=null){
                        vaccineType = vrVaccineTypes.get(account.getAccountId());
                    }
                }
        %>
            <tr style="text-align: center">
                <td><%= account.getUsername()%></td>
                <td><%= account.getAccountType().toString()%></td>
                <td><%= vaccineType%></td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div>
        <form name="return_home" method="get" action="home"
              style="position: absolute;bottom: 0;left: 1%;">
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn btn-dark">Return Home</button>
                </div>
            </div>
        </form>
    </div>
    <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
            integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
            crossorigin="anonymous"
    >
    </script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
            integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
            crossorigin="anonymous"
    >
    </script>
</body>
</html>
