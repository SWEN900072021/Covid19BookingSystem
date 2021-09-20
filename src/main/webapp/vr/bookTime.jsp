<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
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
    <title>Book Time</title>

    <%
        HashMap<String, String> confirmationDetails = new HashMap<>();
        String dateClicked = request.getParameter("dateClicked");
        List<Timeslot> allAvailableTimeslotDates =
                (List<Timeslot>) request.getSession().getAttribute("allAvailableTimeslotDates");
        List<String> strTimes = new ArrayList<>();

        for (Timeslot timeslot : allAvailableTimeslotDates) {
            Date date = new Date(timeslot.getDateTime().getTime());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("HH:mm");
            if (dateFormatter.format(date).equals(dateClicked)) {
                strTimes.add(dateTimeFormatter.format(date));
            }
        }
        Collections.sort(strTimes);
    %>

    <%
        if (request.getAttribute("confirmationDetails") != null) {
            confirmationDetails = (HashMap<String, String>) request.getAttribute("confirmationDetails");
    %>
    <script>
        $(document).ready(function () {
            $('#confirmationModal').modal('show');
        });
    </script>
    <%
        }
    %>

    <script>

        function confirmTimeslot() {
            var form = document.createElement('form');
            var csrfInput = document.createElement('input');
            var confirmInput = document.createElement('input');
            csrfInput.setAttribute("type", "hidden");
            csrfInput.setAttribute("name", "${_csrf.parameterName}");
            csrfInput.setAttribute("value", "${_csrf.token}");
            confirmInput.setAttribute("type", "hidden");
            confirmInput.setAttribute("name", "confirmed");
            confirmInput.setAttribute("value", "true");
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'bookTime');
            form.appendChild(csrfInput);
            form.appendChild(confirmInput);
            document.body.appendChild(form)
            form.submit();
        }

        function selectTime(timeClicked) {
            var form = document.createElement('form');
            var csrfInput = document.createElement('input');
            var dateInput = document.createElement('input');
            var timeInput = document.createElement('input');
            csrfInput.setAttribute("type", "hidden");
            csrfInput.setAttribute("name", "${_csrf.parameterName}");
            csrfInput.setAttribute("value", "${_csrf.token}");
            timeInput.setAttribute("type", "hidden");
            timeInput.setAttribute("name", "timeClicked");
            timeInput.setAttribute("value", timeClicked);
            dateInput.setAttribute("type", "hidden");
            dateInput.setAttribute("name", "dateClicked");
            dateInput.setAttribute("value", '<%= dateClicked%>');
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'bookTime');
            form.appendChild(csrfInput);
            form.appendChild(dateInput);
            form.appendChild(timeInput);
            document.body.appendChild(form)
            form.submit();
        }
    </script>
</head>
<body>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Select an available Time and "Proceed"
</h4>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <div class="list-group">
            <%
                for (String dateTime : strTimes) {
            %>
            <button
                    class="list-group-item list-group-item-action"
                    onclick="selectTime('<%= dateTime%>');"
            >
                <%= dateTime%>
            </button>
            <%
                }
            %>
        </div>
    </div>
</div>
<div id="confirmationModal" class="modal fade bd-example-modal-lg">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Details</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" style="text-align: center">
                <form>
                    <div class="form-group row">
                        <label for="dateTimeId" class="col-sm-6 col-form-label">Date and Time:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="dateTimeId"
                                    placeholder="<%= confirmationDetails.get("dateTime")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="vaccineTypeId" class="col-sm-6 col-form-label">Vaccine Type:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="vaccineTypeId"
                                    placeholder="<%= confirmationDetails.get("vaccineType")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="durationId" class="col-sm-6 col-form-label">Duration:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="durationId"
                                    placeholder="<%= confirmationDetails.get("duration")%> minutes"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="locationId" class="col-sm-6 col-form-label">Location:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="locationId"
                                    placeholder="<%= confirmationDetails.get("location")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="hcpOrgId" class="col-sm-6 col-form-label">Health Care Provider Org Id:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="hcpOrgId"
                                    placeholder="<%= confirmationDetails.get("hcpOrgId")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="hcpNameId" class="col-sm-6 col-form-label">Health Care Provider Name:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="hcpNameId"
                                    placeholder="<%= confirmationDetails.get("hcpName")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="hcpTypeId" class="col-sm-6 col-form-label">Health Care Provider Type:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="hcpTypeId"
                                    placeholder="<%= confirmationDetails.get("hcpType")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="hcpPostcodeId" class="col-sm-6 col-form-label">Health Care Provider
                            Postcode:</label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="hcpPostcodeId"
                                    placeholder="<%= confirmationDetails.get("hcpPostcode")%>"
                                    disabled
                            >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="confirmTimeslot()">Book Vaccine</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
