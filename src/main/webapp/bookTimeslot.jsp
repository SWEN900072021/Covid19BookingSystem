<%--
  Created by IntelliJ IDEA.
  User: Jay Parikh
  Date: 7/09/2021
  Time: 6:20 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.css' rel='stylesheet' />
    <link href='https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.13.1/css/all.css' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.9.0/main.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@5.9.0/main.js"></script>
    <title>Book Timeslot</title>
    <script>
        var dateClicked = '';

        function proceed () {
            var form = document.createElement('form');
            var input = document.createElement('input');
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "dateClicked");
            input.setAttribute("value", dateClicked);
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'timeslot');
            form.appendChild(input);
            document.body.appendChild(form)
            form.submit();
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
                    dateClicked = info.dateStr;
                },
            });
            calendar.render();
        });

    </script>
</head>
<body>
    <div
        style="max-width: 900px;margin: 40px auto;"
        id='calendar'
    >
    </div>
    <button type="button" onclick="proceed();">Submit Date</button>
</body>
</html>
