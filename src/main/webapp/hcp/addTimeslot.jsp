<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Create a Timeslot</title>
</head>
<body>
    <h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Create a New Timeslot
    </h4>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "addTimeslot" method = "post">
                <div class="form-group row">
                    <label for="inputHcpId" class="col-sm-4 col-form-label">Healthcare Provider ID:</label>
                    <div class="col-sm-8">
                        <input
                            type="text"
                            class="form-control"
                            id="inputHcpId"
                            name = "healthcareProvider"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputDate" class="col-sm-4 col-form-label">Date (YYYY-MM-DD):</label>
                    <div class="col-sm-8">
                        <input
                                type="date"
                                class="form-control"
                                id="inputDate"
                                name = "date"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputTime" class="col-sm-4 col-form-label">Time (HH:MM):</label>
                    <div class="col-sm-8">
                        <input
                                type="time"
                                class="form-control"
                                id="inputTime"
                                name = "time"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputDuration" class="col-sm-4 col-form-label">Duration (in minutes):</label>
                    <div class="col-sm-8">
                        <input
                                type="number"
                                class="form-control"
                                id="inputDuration"
                                name = "duration"
                                placeholder="15"
                        >
                    </div>
                </div>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-4 pt-0">Vaccine Type:</legend>
                        <div class="col-sm-8" style="text-align: left">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="vaccineType" id="AZ" value="ASTRAZENECA" checked>
                                <label class="form-check-label" for="AZ">
                                    Astrazeneca
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="vaccineType" id="Pfizer" value="PFIZER">
                                <label class="form-check-label" for="Pfizer">
                                    Pfizer
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="form-group row">
                    <label for="inputLocation" class="col-sm-4 col-form-label">Location:</label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="inputLocation"
                                name = "location"
                                placeholder="Room X"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn btn-dark">Create Timeslot</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>