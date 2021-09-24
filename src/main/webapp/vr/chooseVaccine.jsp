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
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Choose Vaccine
    </h4>
    <br/>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "chooseVaccine" method = "post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <%
                    if(questionnairePassed != null && !questionnairePassed){

                %>
                <div class="form-group row">
                    <label class="col-sm-12" style="color: #f0483c" >
                        Ineligible for selected vaccine, please choose another.
                    </label>
                    <br>
                </div>
                <%
                    }
                %>
                <div class="form-group row">
                    <label class="col-sm-5 col-form-label" for="inputVaccineType">Vaccine Type:</label>
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
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-outline-dark"> Next </button>
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
