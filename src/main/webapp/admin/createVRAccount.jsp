<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Vaccine Recipient Account</title>
</head>
<body>
<form action = "account" method = "post">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    <input type="hidden" name="accountType" value="VR">
    <input type = "submit" value = "Create Account">
</form>
</body>
</html>