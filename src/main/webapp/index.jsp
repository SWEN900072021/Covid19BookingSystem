<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>COVID-19 Booking System</title>
</head>
<body>

<h1>Admin Create VR Account</h1>
<form name="create account" method="get" action="admin/createVRAccount.jsp">
    <input type="submit" value="Sign up" >
</form>
<h1>Admin Create HCP Account</h1>
<form name="create account" method="get" action="admin/createHCPAccount.jsp">
    <input type="submit" value="Sign up" >
</form>


<h1>VR Create Account</h1>
<form name="create account" method="get" action="public/createAccount.jsp">
    <input type="submit" value="Sign up" >
</form>

<h1>HCP Add Timeslot</h1>
<form name="create account" method="get" action="hcp/addTimeslot.jsp">
    <input type="submit" value="Add timeslot" >
</form>

</body>
</html>