<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action = "account" method = "post">
    User name: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    <input type = "submit" value = "Login">
</form>
<form name="timeslot" method="get" action="timeslot.jsp">
    <input type="submit" value="Create Timeslot" >
</form>
</body>
</html>