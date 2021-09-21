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
</head>
<body>
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
                <div class="row">
                    <legend class="col-form-label col-sm-5 pt-0">Search by:</legend>
                    <div class="col-sm-7" style="text-align: left">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="searchBy" id="Area" value="area" checked>
                            <label class="form-check-label" for="Area">
                                Area (By postcode)
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="searchBy" id="HCP"
                                   value="healthCareProvider">
                            <label class="form-check-label" for="HCP">
                                Health Care Provider (By name)
                            </label>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="form-group row">
                <label for="inputQuery" class="col-sm-5 col-form-label">Health Care Provider name / Area
                    postcode:</label>
                <div class="col-sm-7">
                    <input
                            type="text"
                            class="form-control"
                            id="inputQuery"
                            name="queryField"
                            placeholder="Royal Melbourne Hospital / 3052"
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
</body>
</html>
