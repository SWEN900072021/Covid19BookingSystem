<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Record a Vaccination</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
<% List<Timeslot> allBookedTimeslots = (List<Timeslot>) request.getSession().getAttribute("bookedTimeslots"); %>
<div class="card text-center border-secondary mb-3"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <div class="list-group">
            <%
                for (Timeslot timeslot : allBookedTimeslots) {
            %>
            <form action="recordVaccination" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button class="btn btn-light" type="submit" name="timeslotSubmitted" value="<%= timeslot.getId()%>">
                    Time: <%= timeslot.getDateTime().toString() %> | Vaccine
                    Recipient: <%= timeslot.getVaccineRecipient().getFirstName() %> <%= timeslot.getVaccineRecipient().getLastName() %>
                    | Vaccine Type: <%= timeslot.getVaccineType() %>
                </button>
            </form>
            <%
                }
            %>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
        integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
        crossorigin="anonymous"></script>
</body>
</html>