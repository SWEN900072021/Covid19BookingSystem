<%@ page import="com.example.covid19bookingsystem.domain.Timeslot" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
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
    <title>Edit Timeslot</title>
    <%
        HashMap<String, String> chosenTimeslotDetails = new HashMap<>();
        if (request.getAttribute("chosenTimeslotDetails") != null) {
            chosenTimeslotDetails = (HashMap<String, String>) request.getAttribute("chosenTimeslotDetails");
    %>
            <script>
                $(document).ready(function () {
                    $('#editTimeslotModal').modal('show');
                });
            </script>
    <%
        }
    %>
    <script>
        function selectTimeslot(timeslotId) {
            var form = document.createElement('form');
            var csrfInput = document.createElement('input');
            var timeslotIdInput = document.createElement('input');
            csrfInput.setAttribute("type", "hidden");
            csrfInput.setAttribute("name", "${_csrf.parameterName}");
            csrfInput.setAttribute("value", "${_csrf.token}");
            timeslotIdInput.setAttribute("type", "hidden");
            timeslotIdInput.setAttribute("name", "timeslotId");
            timeslotIdInput.setAttribute("value", timeslotId);
            form.setAttribute('method', 'post');
            form.setAttribute('action', 'editTimeslot');
            form.appendChild(csrfInput);
            form.appendChild(timeslotIdInput);
            document.body.appendChild(form)
            form.submit();
        }
    </script>
</head>
<body>
<%
    List<Timeslot> editableTimeslots = (List<Timeslot>) request.getSession().getAttribute("editableTimeslots");
%>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Edit Timeslots
</h4>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 60rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <div class="list-group">
            <%
                for (Timeslot timeslot : editableTimeslots) {
            %>
            <button
                    class="list-group-item list-group-item-action"
                    onclick="selectTimeslot('<%= timeslot.getId()%>');"
            >
                <strong>Time:</strong> <%= timeslot.getDateTime().toString() %>
                | <strong>Vaccine Type:</strong> <%= timeslot.getVaccineType() %>
            </button>
            <%
                }
            %>
        </div>
    </div>
</div>
<div id="editTimeslotModal" class="modal fade bd-example-modal-lg">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Timeslot Details</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" style="text-align: center">
                <form>
                    <div class="form-group row">
                        <label for="dateId" class="col-sm-6 col-form-label"><strong>Date:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="date"
                                    class="form-control"
                                    id="dateId"
                                    value="<%= chosenTimeslotDetails.get("date")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="timeId" class="col-sm-6 col-form-label"><strong>Time:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="time"
                                    class="form-control"
                                    id="timeId"
                                    value="<%= chosenTimeslotDetails.get("time")%>"
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
                                    placeholder="<%= chosenTimeslotDetails.get("vaccineType")%>"
                                    disabled
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="durationId" class="col-sm-6 col-form-label"><strong>Duration (in minutes):</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="durationId"
                                    value="<%= chosenTimeslotDetails.get("duration")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="addressLine1Id" class="col-sm-6 col-form-label"><strong>Address Line 1:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="addressLine1Id"
                                    value="<%= chosenTimeslotDetails.get("addressLine1")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="addressLine2Id" class="col-sm-6 col-form-label"><strong>Address Line 2:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="addressLine2Id"
                                    value="<%= chosenTimeslotDetails.get("addressLine2")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="stateId" class="col-sm-6 col-form-label"><strong>State:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="stateId"
                                    value="<%= chosenTimeslotDetails.get("state")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="postcodeId" class="col-sm-6 col-form-label"><strong>Postcode:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="postcodeId"
                                    value="<%= chosenTimeslotDetails.get("postcode")%>"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="countryId" class="col-sm-6 col-form-label"><strong>Country:</strong></label>
                        <div class="col-sm-6">
                            <input
                                    type="text"
                                    class="form-control"
                                    id="countryId"
                                    value="<%= chosenTimeslotDetails.get("country")%>"
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
                                    placeholder="<%= chosenTimeslotDetails.get("hcpOrgId")%>"
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
                                    placeholder="<%= chosenTimeslotDetails.get("hcpName")%>"
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
                                    placeholder="<%= chosenTimeslotDetails.get("hcpType")%>"
                                    disabled
                            >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmTimeslot()">Confirm Details</button>
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
