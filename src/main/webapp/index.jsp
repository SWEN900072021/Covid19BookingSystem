<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>COVID-19 Booking System</title>
</head>
<body>

<form name = "login" method = "post" action="${pageContext.request.contextPath}/login">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    <input type = "submit" value = "Login">
</form>
<form name="create account" method="get" action="public/createAccount.jsp">
    <input type="submit" value="Sign up" >
</form>

</body>
</html>