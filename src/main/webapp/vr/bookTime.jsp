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

        function selectTime(timeslotId) {
            var form = document.createElement('form');
            var csrfInput = document.createElement('input');
            var timeslotIdInput = document.createElement('input');
            var dateClickedInput = document.createElement('input');
            csrfInput.setAttribute("type", "hidden");
            csrfInput.setAttribute("name", "${_csrf.parameterName}");
            csrfInput.setAttribute("value", "${_csrf.token}");
            timeslotIdInput.setAttribute("type", "hidden");
            timeslotIdInput.setAttribute("name", "timeslotId");
            timeslotIdInput.setAttribute("value", timeslotId);
            dateClickedInput.setAttribute("type", "hidden");
            dateClickedInput.setAttribute("name", "dateClicked");
            dateClickedInput.setAttribute("value", "<%= dateClicked%>");
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'bookTime');
            form.appendChild(csrfInput);
            form.appendChild(timeslotIdInput);
            form.appendChild(dateClickedInput);
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
                for (Timeslot timeslot : allAvailableTimeslotDates) {
                    Date date = new Date(timeslot.getDateTime().getTime());
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("hh:mm aa");
                    if (dateFormatter.format(date).equals(dateClicked)) {
            %>
                        <button
                                class="list-group-item list-group-item-action"
                                onclick="selectTime('<%= timeslot.getId()%>');"
                        >
                           <strong><%= dateTimeFormatter.format(date).toUpperCase()%></strong>
                        </button>
            <%
                    }
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
                        <label for="dateTimeId" class="col-sm-6 col-form-label"><strong>Date:</strong></label>
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
                        <label for="vaccineTypeId" class="col-sm-6 col-form-label"><strong>Vaccine Type:</strong></label>
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
                        <label for="durationId" class="col-sm-6 col-form-label"><strong>Duration:</strong></label>
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
                        <label for="locationId" class="col-sm-6 col-form-label"><strong>Location:</strong></label>
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
                        <label for="hcpOrgId" class="col-sm-6 col-form-label"><strong>Health Care Provider Org Id:</strong></label>
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
                        <label for="hcpNameId" class="col-sm-6 col-form-label"><strong>Health Care Provider Name:</strong></label>
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
                        <label for="hcpTypeId" class="col-sm-6 col-form-label"><strong>Health Care Provider Type:</strong></label>
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmTimeslot()">Book Vaccine</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
            </div>
        </div>
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
