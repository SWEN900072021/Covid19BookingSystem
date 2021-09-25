<%@ page import="com.example.covid19bookingsystem.domain.Account" %>
<%@ page import="com.example.covid19bookingsystem.domain.HealthCareProvider" %>
<%@ page import="com.example.covid19bookingsystem.domain.VaccineType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <title>Create a Timeslot</title>
    <script>
        <%
            if (request.getAttribute("alert") != null) {
        %>
        alert("No timeslots added!");
        <%
            }
        %>
    </script>
    <%

        ArrayList<VaccineType> allVaccineTypes = (ArrayList<VaccineType>) request.getSession().getAttribute("allVaccineTypes");
        ArrayList<String> vaccineNames = new ArrayList<>();

        for (VaccineType vaccineType: allVaccineTypes) {
            String vaccineName = vaccineType.getName();
            vaccineNames.add(vaccineName);
        }

        HealthCareProvider hcp = new HealthCareProvider();
        if (request.getSession().getAttribute("userDetails") != null) {
            hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
        }
    %>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
    </symbol>
</svg>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Create a New Timeslot
</h4>
<br/>
<div class="alert alert-primary d-flex align-items-center" role="alert"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
    <div>
        Use <strong>Add timeslot</strong> to sequentially add timeslots to the current batch.<br/>
        Use <strong>Submit All Timeslots</strong> when you have finished adding all the timeslots to the current session.
    </div>
</div>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <form action="addTimeslot" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input
                    type="hidden"
                    name="healthcareProvider"
                    value="<%= hcp.getId().toString()%>"
            >
            <div class="row mb-3">
                <label for="inputDate" class="col-sm-4 col-form-label"><strong>Date (YYYY-MM-DD):</strong></label>
                <div class="col-sm-8">
                    <input
                            type="date"
                            class="form-control"
                            id="inputDate"
                            name="date"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputTime" class="col-sm-4 col-form-label"><strong>Time (HH:MM):</strong></label>
                <div class="col-sm-8">
                    <input
                            type="time"
                            class="form-control"
                            id="inputTime"
                            name="time"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputDuration" class="col-sm-4 col-form-label"><strong>Duration (in
                    minutes):</strong></label>
                <div class="col-sm-8">
                    <input
                            type="number"
                            class="form-control"
                            id="inputDuration"
                            name="duration"
                            placeholder="15"
                    >
                </div>
            </div>
            <fieldset class="row mb-3">
                <label class="col-sm-4 col-form-label" for="inputVaccineType"><strong>Vaccine Type:</strong></label>
                <div class="col-sm-8">
                    <select id="inputVaccineType" class="form-control" name = "vaccineType">
                        <%
                            for (String vaccineName: vaccineNames) {
                        %>
                        <option value=<%= vaccineName%>> <%= vaccineName%> </option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </fieldset>
            <div class="row mb-3">
                <label for="inputAddressLine1" class="col-sm-4 col-form-label"><strong>Address Line 1:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="inputAddressLine1"
                            name="addressLine1"
                            placeholder="Street Name"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputAddressLine2" class="col-sm-4 col-form-label"><strong>Address Line 2:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="inputAddressLine2"
                            name="addressLine2"
                            placeholder="Street Name"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputPostcode" class="col-sm-4 col-form-label"><strong>Postcode:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="inputPostcode"
                            name="postcode"
                            placeholder="postcode"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputState" class="col-sm-4 col-form-label"><strong>State:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="inputState"
                            name="state"
                            placeholder="Victoria"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <label for="inputCountry" class="col-sm-4 col-form-label"><strong>Country:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="inputCountry"
                            name="country"
                            placeholder="Australia"
                    >
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-12">
                    <button
                            type="submit"
                            class="btn btn-dark"
                            data-bs-toggle="tooltip"
                            data-bs-placement="right"
                            title="Add timeslot to batch"
                    >
                        Add Timeslot
                    </button>
                </div>
            </div>
        </form>
        <br/>
        <form action="addTimeslot" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type="hidden" name="commit" value="true"/>
            <div class="row mb-3">
                <div class="col-sm-12">
                    <button
                            type="submit"
                            class="btn btn-dark"
                            data-bs-toggle="tooltip"
                            data-bs-placement="right"
                            title="Create all timeslots in batch"
                    >
                        Submit All Timeslots
                    </button>
                </div>
            </div>
        </form>
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