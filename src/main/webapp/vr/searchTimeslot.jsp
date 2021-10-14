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
    <title>Search Type</title>
    <%
        if (request.getAttribute("failure") != null) {
    %>
    <script>
        $(document).ready(function () {
            $('#failureModal').modal('show');
        });
    </script>
    <%
        }
    %>
    <%
        String searchBy = null;
        String searchQuery = null;
        if (request.getSession().getAttribute("searchBy") != null && request.getSession().getAttribute("searchQuery") != null) {
            searchBy = (String) request.getSession().getAttribute("searchBy");
            searchQuery = (String) request.getSession().getAttribute("searchQuery");
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
    Search by "Area" postcode or "Health Care Provider" name
</h4>
<br/>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <form action="searchTimeslot" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <fieldset class="form-group">
                <%
                    if (request.getParameter("timeslot_taken") != null) {
                        if (request.getParameter("timeslot_taken").equals("true")) {
                %>
                <div class="form-group row">
                    <div class="alert alert-danger d-flex align-items-center" role="alert"
                         style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                        <div style="margin-left: 20px">
                            Timeslot has been booked by another user. Please try again.
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <div class="row">
                    <legend class="col-form-label col-sm-5 pt-0"><strong>Search by:</strong></legend>
                    <div class="col-sm-7" style="text-align: left">
                        <div class="form-check">
                            <%
                                if ("area".equals(searchBy)) {
                            %>
                            <input class="form-check-input" type="radio" name="searchBy" id="Area" value="area" checked>
                            <%
                                }
                            else if (searchBy == null) {
                            %>
                            <input class="form-check-input" type="radio" name="searchBy" id="Area" value="area" checked>
                            <%
                                }
                                else {
                            %>
                            <input class="form-check-input" type="radio" name="searchBy" id="Area" value="area">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="Area">
                                Area (By postcode)
                            </label>
                        </div>
                        <div class="form-check">
                            <%
                                if ("hcp".equals(searchBy)) {
                            %>
                            <input class="form-check-input" type="radio" name="searchBy" id="HCP"
                                   value="healthCareProvider" checked>
                            <%
                                }
                            else {
                            %>
                            <input class="form-check-input" type="radio" name="searchBy" id="HCP"
                                   value="healthCareProvider">
                            <%
                                }
                            %>
                            <label class="form-check-label" for="HCP">
                                Health Care Provider (By name)
                            </label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="form-group row">
                <label for="inputQuery" class="col-sm-5 col-form-label"><strong>Health Care Provider name /
                    <br/>Area postcode:</strong></label>
                <div class="col-sm-7">
                    <input
                            type="text"
                            class="form-control"
                            id="inputQuery"
                            name="queryField"
                            placeholder="Royal Melbourne Hospital / 3052"
                            <%
                                if (searchQuery != null) {
                            %>
                            value=<%= searchQuery %>
                            <%
                                }
                            %>
                    >
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-outline-dark">Search</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="failureModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Try Again</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p>Unable to find Timeslots. Try again.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
