<%@ page import="java.util.List" %>
<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.css' rel='stylesheet' />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <link href='https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.13.1/css/all.css' rel='stylesheet'>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
            crossorigin="anonymous">
    </script>
    <title>Book Time</title>
    <%
        String dateClicked = request.getParameter("dateClicked");
        List<Timeslot> allAvailableTimeslotDates =
                (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
        List<String> strTimes = new ArrayList<>();

        for (Timeslot timeslot: allAvailableTimeslotDates) {
            Date date = new Date(timeslot.getDateTime().getTime());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            if (dateFormatter.format(date).equals(dateClicked)) {
                strTimes.add(timeFormatter.format(date));
            }
        }
    %>
</head>
<body>
    <h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Select an available Time and "Proceed"
    </h4>
    <br/>
</body>
</html>
