<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>COVID-19 Booking System</title>
    </head>
    <body>

        Welcome back,
        <sec:authentication property="name" />

        <p>
            <sec:authorize access="hasRole(\"ADMIN\")">
                <form name="create_hcp_admin" method="get" action="admin/createHCPAccount.jsp">
                    <input type="submit" value="Create HealthCareProviderAccount" >
                </form>
                <form name="create_vr_admin" method="get" action="admin/createVRAccount.jsp">
                    <input type="submit" value="Create VaccineRecipient Account" >
                </form>
            </sec:authorize>
        </p>

        <p>
            <sec:authorize access="hasRole(\"VR\")">
                <form name="fill_in_details" method="get" action="vr/createVaccineRecipient.jsp">
                    <input type="submit" value="Fill in VaccineRecipient details" >
                </form>
                <form name="searchTimeslot" method="get" action="vr/searchType.jsp">
                    <input type="submit" value="Search Timeslot" >
                </form>
            </sec:authorize>
        </p>

        <p>
            <sec:authorize access="hasRole(\"HCP\")">
                <form name="timeslot" method="get" action="hcp/addTimeslot.jsp">
                    <input type="submit" value="Create Timeslot" >
                </form>
                <form name="fill_in_details" method="get" action="hcp/createHealthCareProvider.jsp">
                    <input type="submit" value="Fill in HealthCareProvider details" >
                </form>
            </sec:authorize>
        </p>

<%--        <form action="logout" method="post">--%>
<%--            <sec:csrfInput />--%>
<%--            <input type="submit" value="Sign Out" />--%>
<%--        </form>--%>
    </body>
</html>