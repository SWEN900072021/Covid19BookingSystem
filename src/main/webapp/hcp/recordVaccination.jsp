<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Record a Vaccination</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <%
        String success = (String) request.getSession().getAttribute("success");
    %>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
    </symbol>
</svg>
<% List<Timeslot> allBookedTimeslots = (List<Timeslot>) request.getSession().getAttribute("bookedTimeslots"); %>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Record Completed Vaccinations
</h4>
<br/>
<div class="alert alert-primary d-flex align-items-center" role="alert"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
    <div>
        Clicking on any record below, will automatically record the vaccination as <strong>Completed</strong>.
    </div>
    </div>
    <%
        if (success != null) {
            if (success.equals("version_mismatch")) {
    %>
    <div class="alert alert-danger d-flex align-items-center" role="alert"
         style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
        <div>
            Someone else has already recorded this vaccination as <strong>Completed</strong>.
        </div>
    </div>
    <%
            }
        }
    %>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 60rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <%
            for (Timeslot timeslot : allBookedTimeslots) {
        %>
                <div class="list-group">
                    <form action="recordVaccination" method="post">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <button class="btn btn-outline-dark" type="submit" name="timeslotSubmitted"
                                value="<%= timeslot.getId()%>" style="width: 100%">
                            <strong>Time:</strong> <%= timeslot.getDateTime().toString() %> | <strong>Vaccine
                            Recipient:</strong> <%= timeslot.getVaccineRecipient().getFirstName() %> <%= timeslot.getVaccineRecipient().getLastName() %>
                            | <strong>Vaccine Type:</strong> <%= timeslot.getVaccineType() %>
                        </button>
                    </form>
                </div>
        <%
            }
        %>
    </div>
</div>
<div>
    <form name="return_home" method="get" action="home"
          style="position: absolute;bottom: 1%;left: 1%;">
        <div class="form-group row">
            <div class="col-sm-12">
                <button type="submit" class="btn btn btn-dark">Return Home</button>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
        integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
        crossorigin="anonymous"></script>
</body>
</html>