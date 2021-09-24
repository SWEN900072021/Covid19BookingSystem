<%@ page import="com.example.covid19bookingsystem.domain.VaccineType" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Choose Vaccine</title>
    <%
        Boolean questionnairePassed = (Boolean) request.getSession().getAttribute("questionnairePassed");
        ArrayList<VaccineType> allVaccineTypes = (ArrayList<VaccineType>) request.getSession().getAttribute("allVaccineTypes");
        ArrayList<String> vaccineNames = new ArrayList<>();

        for (VaccineType vaccineType: allVaccineTypes) {
            String vaccineName = vaccineType.getName();
            vaccineNames.add(vaccineName);
        }
    %>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
</svg>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Choose Vaccine
    </h4>
    <br/>
    <%
        if(questionnairePassed != null && !questionnairePassed){

    %>
        <div class="alert alert-danger d-flex align-items-center" role="alert"
             style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
            <div style="margin-left: 15px;">
                Ineligible for selected vaccine, please choose another.
            </div>
        </div>
        <br/>
    <%
        }
    %>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "chooseVaccine" method = "post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="form-group row">
                    <label class="col-sm-5 col-form-label" for="inputVaccineType"><strong>Vaccine Type:</strong></label>
                    <div class="col-sm-7">
                        <select id="inputVaccineType" class="form-control" name = "vaccineType">
                        <%
                            for (String vaccineName: vaccineNames) {
                        %>
                                <option value=<%= vaccineName%>> <%= vaccineName%> </option>
                        <%
                            }
                        %>
                        </select>
                    </div>
                </div>
                <br/>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-dark"> Next </button>
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
