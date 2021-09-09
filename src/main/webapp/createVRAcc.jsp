<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form action = "vaccinerecipient" method = "post">
    Username: <input type = "text" name = "username"><br/>
    Password: <input type = "password" name = "password"><br/>
    First Name: <input type = "text" name = "firstName"><br/>
    Last Name: <input type = "text" name = "lastName"><br/>
    Address: <input type = "text" name = "address"><br/>
    Date of Birth: <input type = "date" name = "dateOfBirth"><br/>
    <p>Gender: </p>
    <input type="radio" id="female" name="gender" value="Female">
    <label for="female">Female</label><br>
    <input type="radio" id="male" name="gender" value="Male">
    <label for="male">Male</label><br>
    <input type="radio" id="undisclosed" name="gender" value="Undisclosed">
    <label for="undisclosed">Prefer Not to Answer</label><br>
    Phone Number: <input type = "text" name = "phoneNumber"><br/>
    Email: <input type = "text" name = "email"><br/>
    <input type = "submit" value = "Create Timeslot">
</form>
</body>
</html>
