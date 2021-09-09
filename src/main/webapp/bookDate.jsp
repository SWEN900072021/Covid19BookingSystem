<%--
  Created by IntelliJ IDEA.
  User: Jay Parikh
  Date: 7/09/2021
  Time: 6:20 pm
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
    <head>
        <link href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.css' rel='stylesheet' />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
              crossorigin="anonymous">
        <link href='https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.13.1/css/all.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.9.0/main.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.9.0/main.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
                crossorigin="anonymous">
        </script>
        <title>Book Timeslot</title>
        <%
            List<Timeslot> allAvailableTimeslotDates =
                    (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
            List<String> stringDates = new ArrayList<>();

            for (Timeslot timeslot: allAvailableTimeslotDates) {
                Date date = new Date(timeslot.getDateTime().getTime());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = formatter.format(date);
                stringDates.add(strDate);
            }

            Set<String> set = new HashSet<>(stringDates);
            stringDates.clear();
            stringDates.addAll(set);
        %>
        <script>
            var dateClicked = '';

            function proceed () {
                if (dateClicked === '') {
                    alert("No dates were chosen!");
                }
                else {
                    console.log(dateClicked);
                    var form = document.createElement('form');
                    var input = document.createElement('input');
                    input.setAttribute("name", "dateClicked");
                    input.setAttribute("value", dateClicked);
                    form.setAttribute('method', 'post');
                    form.setAttribute('action', 'bookDate');
                    form.appendChild(input);
                    document.body.appendChild(form)
                    form.submit();
                }
            }

            document.addEventListener('DOMContentLoaded', function() {
                var calendarEl = document.getElementById('calendar');
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: 'dayGridMonth',
                    themeSystem: 'bootstrap',
                    headerToolbar: {
                        left: 'prev,next today',
                        center: 'title'
                    },
                    selectable: true,
                    selectAllow: function (e) {
                        if (e.end.getTime() / 1000 - e.start.getTime() / 1000 <= 86400) {
                            return true;
                        }
                    },
                    dateClick: function(info) {
                        <%
                            for (String date : stringDates) {
                        %>
                                if(info.dateStr === '<%= date%>') {
                                    dateClicked = info.dateStr;
                                }
                        <%
                            }
                        %>
                        if (dateClicked !== info.dateStr) {
                            alert("This date does not have any timeslots");
                            dateClicked = '';
                        }
                    },
                    selectConstraint: {

                    },
                    events: [
                    <%
                        for (String date : stringDates) {
                    %>
                            {
                                title: 'Available',
                                start: '<%= date%>'
                            },
                    <%
                        }
                    %>
                    ]
                });
                calendar.render();
            });

        </script>
    </head>
    <body>
        <h4
                class="display-4"
                style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
        >
            Select an available date and "Proceed"
        </h4>
        <br/>
        <div
            style="max-width: 900px;margin: 40px auto;"
            id='calendar'
        >
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button
                    type="button"
                    class="btn btn-outline-dark btn-lg"
                    onclick="proceed();"
            >
                Proceed
            </button>
        </div>
    </body>
</html>
