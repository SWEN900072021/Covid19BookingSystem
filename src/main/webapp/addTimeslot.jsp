<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a Timeslot</title>
</head>
<body>
<form action = "addTimeslot" method = "post">
    Healthcare Provider ID: <input type = "text" name = "healthcareProvider"><br/>
    Date (YYYY-MM-DD): <input type = "text" name = "date"><br/>
    Time (HH:MM) : <input type = "text" name = "time"><br/>
    Duration (in minutes): <input type = "text" name = "duration"><br/>
    Location: <input type = "text" name = "location"><br/>
    <input type = "submit" value = "Create Timeslot">
</form>
</body>
</html>