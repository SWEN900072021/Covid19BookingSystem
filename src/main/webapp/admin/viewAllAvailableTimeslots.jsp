<%@ page import="java.util.List" %>
<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
        crossorigin="anonymous"
    >
    <title>View All Timeslots</title>
    <%
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
        List<Timeslot> availableTimeslots = (List<Timeslot>) request.getSession().getAttribute("unbookedTimeslots");
    %>
</head>
<body>
<h4
    class="display-4"
    style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    List of All Available Timeslots
</h4>
<br/>
<table class="table table-bordered table-striped table-hover"
       style="width: 85rem;margin: 0 auto;float: none;margin-bottom: 10px;border: 1px">
    <thead class="table-dark">
        <tr style="text-align: center">
            <th scope="col">Date and Time</th>
            <th scope="col">Vaccine Type</th>
            <th scope="col">Duration</th>
            <th scope="col">Address (Line 1)</th>
            <th scope="col">Address (Line 2)</th>
            <th scope="col">State</th>
            <th scope="col">Postcode</th>
            <th scope="col">Country</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (Timeslot timeslot : availableTimeslots) {
        %>
                <tr style="text-align: center">
                    <td><%= dateTimeFormatter.format(new Date(timeslot.getDateTime().getTime())).toUpperCase()%></td>
                    <td><%= timeslot.getVaccineType()%></td>
                    <td><%= timeslot.getDuration().toString()%></td>
                    <td><%= timeslot.getAddress().getAddressLine1()%></td>
                    <td><%= timeslot.getAddress().getAddressLine2()%></td>
                    <td><%= timeslot.getAddress().getState()%></td>
                    <td><%= timeslot.getAddress().getPostcode()%></td>
                    <td><%= timeslot.getAddress().getCountry()%></td>
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
