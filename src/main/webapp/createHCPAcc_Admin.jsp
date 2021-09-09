<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Health Care Provider Account</title>
</head>
<body>
<form action = "healthcareprovider" method = "post">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    Organisation ID: <input type = "text" name = "organisationalId"><br/>
    Health Care Provider Name: <input type = "text" name = "healthCareProviderName"><br/>
    <p>Health Care Provider Type: </p>
    <input type="radio" id="clinic" name="hcpType" value="CLINIC">
    <label for="clinic">Clinic</label><br>
    <input type="radio" id="hospital" name="hcpType" value="HOSPITAL">
    <label for="hospital">Hospital</label><br>
    <input type="radio" id="gp" name="hcpType" value="GP">
    <label for="gp">GP</label><br>
    <input type="radio" id="popup" name="hcpType" value="POPUP">
    <label for="popup">Popup</label><br>
    <input type="radio" id="other" name="hcpType" value="OTHER">
    <label for="other">Other</label><br>
    Postcode: <input type = "text" name = "postcode"><br/>
</form>
</body>
</html>
