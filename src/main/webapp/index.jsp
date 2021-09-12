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
        <form name="creat_hcp_admin" method="get" action="createHCPAcc_Admin.jsp">
            <input type="submit" value="Create HCP for Admin" >
        </form>
        <form name="creat_vr_admin" method="get" action="createVRAcc_Admin.jsp">
            <input type="submit" value="Create VR for Admin" >
        </form>
        <form name="create_vr" method="get" action="createVRAcc.jsp">
            <input type="submit" value="Create VR" >
        </form>
        <form name="timeslot" method="get" action="addTimeslot.jsp">
            <input type="submit" value="Create Timeslot" >
        </form>
        <form name="searchTimeslot" method="get" action="searchType.jsp">
            <input type="submit" value="Search Timeslot" >
        </form>
    </body>
</html>