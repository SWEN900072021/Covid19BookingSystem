<%@ page import="com.example.covid19bookingsystem.domain.HealthCareProvider" %>
<%@ page import="com.example.covid19bookingsystem.utils.EnumUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title>Create Health Care Provider Account</title>
    <%
        HealthCareProvider account = null;
        if (request.getSession().getAttribute("usernameUsed") != null){
            account = (HealthCareProvider) request.getSession().getAttribute("hcpDetails");
        }

    %>
</head>
<body>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Create a New Health Care Provider Account
</h4>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <form action="createHealthCareProvider" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <%
                if (account != null){
            %>
            <div class="form-group row">
                <div class="alert alert-danger d-flex align-items-center" role="alert"
                     style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                    <div>
                        Username has been taken. Please pick another username.
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="form-group row">
                <label for="usernameId" class="col-sm-4 col-form-label"><strong>Username:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="usernameId"
                            name="username"
                    >
                </div>
            </div>
            <div class="form-group row">
                <label for="passwordId" class="col-sm-4 col-form-label"><strong>Password:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="password"
                            class="form-control"
                            id="passwordId"
                            name="password"
                    >
                </div>
            </div>
            <div class="form-group row">
                <label for="organisationalId" class="col-sm-4 col-form-label"><strong>Organisation ID:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="organisationalId"
                            name="organisationalId"
                            <%
                                if (account != null && account.getOrganisationalId() != null) {
                            %>
                            value=<%= account.getOrganisationalId()%>
                            <%
                                }
                            %>
                    >
                </div>
            </div>
            <div class="form-group row">
                <label for="hcpNameId" class="col-sm-4 col-form-label"><strong>Health Care Provider
                    Name:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="hcpNameId"
                            name="healthCareProviderName"
                            <%
                                if (account != null && account.getHealthCareProviderName() != null) {
                            %>
                            value=<%= account.getHealthCareProviderName()%>
                            <%
                                }
                            %>
                    >
                </div>
            </div>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-sm-4 pt-0"><strong>Gender:</strong></legend>
                    <div class="col-sm-8" style="text-align: left">
                        <div class="form-check">
                            <%
                                if (account != null && account.getHealthCareProviderType() == EnumUtils.HealthCareProviderType.CLINIC) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="clinic"
                                   value="CLINIC" checked>
                            <%
                                }
                            else if (account == null) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="clinic"
                                   value="CLINIC" checked>
                            <%
                                }
                                else {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="clinic"
                                   value="CLINIC">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="clinic">
                                Clinic
                            </label>
                        </div>
                        <div class="form-check">
                            <%
                                if (account != null && account.getHealthCareProviderType() == EnumUtils.HealthCareProviderType.HOSPITAL) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="hospital"
                                   value="HOSPITAL" checked>
                            <%
                                }
                            else {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="hospital"
                                   value="HOSPITAL">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="hospital">
                                Hospital
                            </label>
                        </div>
                        <div class="form-check">
                            <%
                                if (account != null && account.getHealthCareProviderType() == EnumUtils.HealthCareProviderType.GP) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="gp"
                                   value="GP" checked>
                            <%
                                }
                            else {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="gp"
                                   value="GP">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="gp">
                                GP
                            </label>
                        </div>
                        <div class="form-check">
                            <%
                                if (account != null && account.getHealthCareProviderType() == EnumUtils.HealthCareProviderType.POPUP) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="popup"
                                   value="POPUP" checked>
                            <%
                                }
                            else {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="popup"
                                   value="POPUP">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="popup">
                                Popup
                            </label>
                        </div>
                        <div class="form-check">
                            <%
                                if (account != null && account.getHealthCareProviderType() == EnumUtils.HealthCareProviderType.OTHER) {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="other"
                                   value="OTHER" checked>
                            <%
                                }
                            else {
                            %>
                            <input class="form-check-input" type="radio" name="healthCareProviderType" id="other"
                                   value="OTHER">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="other">
                                Other
                            </label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="form-group row">
                <label for="postcodeId" class="col-sm-4 col-form-label"><strong>Postcode:</strong></label>
                <div class="col-sm-8">
                    <input
                            type="text"
                            class="form-control"
                            id="postcodeId"
                            name="postcode"
                            placeholder="3000, 3008,..."
                            <%
                                if (account != null && account.getPostcode() != null) {
                            %>
                            value=<%= account.getPostcode()%>
                            <%
                                }
                            %>
                    >
                </div>
            </div>
            <input type="hidden" name="accountType" value="HCP">
            <br/>
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn btn-dark">Create Account</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div>
    <form name="return_home" method="get" action="home"
          style="position: absolute;bottom: 0;left: 1%;">
        <div class="form-group row">
            <div class="col-sm-12">
                <button type="submit" class="btn btn btn-dark">Return Home</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
