<%@ page import="com.example.covid19bookingsystem.domain.Account" %>
<%@ page import="com.example.covid19bookingsystem.domain.HealthCareProvider" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
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
        HealthCareProvider hcp = new HealthCareProvider();
        if (request.getSession().getAttribute("userDetails") != null) {
            hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
        }
    %>
</head>
<body>
    <h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Create a New Timeslot
    </h4>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "addTimeslot" method = "post">
                <input type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"/>
                <input
                        type="hidden"
                        name = "healthcareProvider"
                        value="<%= hcp.getId().toString()%>"
                >
                <div class="row mb-3">
                    <label for="inputDate" class="col-sm-4 col-form-label"><strong>Date (YYYY-MM-DD):</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="date"
                                class="form-control"
                                id="inputDate"
                                name = "date"
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
                                name = "time"
                        >
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputDuration" class="col-sm-4 col-form-label"><strong>Duration (in minutes):</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="number"
                                class="form-control"
                                id="inputDuration"
                                name = "duration"
                                placeholder="15"
                        >
                    </div>
                </div>
                <fieldset class="row mb-3">
                    <div class="row">
                        <legend class="col-form-label col-sm-4 pt-0"><strong>Vaccine Type:</strong></legend>
                        <div class="col-sm-8" style="text-align: left">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="vaccineType" id="AZ" value="ASTRAZENECA" checked>
                                <label class="form-check-label" for="AZ">
                                    Astrazeneca
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="vaccineType" id="Pfizer" value="PFIZER">
                                <label class="form-check-label" for="Pfizer">
                                    Pfizer
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="row mb-3">
                    <label for="inputAddressLine1" class="col-sm-4 col-form-label"><strong>Address Line 1:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="inputAddressLine1"
                                name = "addressLine1"
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
                                name = "addressLine2"
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
                                name = "postcode"
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
                                name = "state"
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
                                name = "country"
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
            <form action = "addTimeslot" method = "post">
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
    <div
            class="toast align-items-center text-white bg-primary border-0 position-fixed bottom-0 end-0"
            role="alert"
            aria-live="assertive"
            aria-atomic="true"
            style="display: block;margin: 10px"
    >
        <div class="d-flex">
            <div class="toast-body">
                Use <strong>Add timeslot</strong> to sequentially add timeslots to the current batch.<br/>
                Use <strong>Submit All Timeslots</strong> when you have finished adding all the timeslots to the current session.
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
</body>
</html>