<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>COVID-19 Booking System</title>
    </head>
    <body>
        <h4
                class="display-4"
                style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
        >
            Welcome back, <sec:authentication property="name" />
        </h4>
        <br/>

        <div class="card text-center border-secondary mb-3" style="width: 25rem;margin: 0 auto;float: none;margin-bottom: 10px;">
            <div class="card-body">
                <div>
                    <sec:authorize access="hasRole('ADMIN')">
                        <form name="create_hcp_admin" method="get" action="createHealthCareProvider">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn btn-dark">Create Health Care Provider Account</button>
                                </div>
                            </div>
                        </form>
                        <form name="create_vr_admin" method="get" action="createVaccineRecipient">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn btn-dark">Create Vaccine Recipient Account</button>
                                </div>
                            </div>
                        </form>
                        <form name="add_vaccine_type" method="get" action="addVaccineType">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn btn-dark">Add Vaccine Type</button>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div>
                <div>
                    <sec:authorize access="hasRole('VR')">
                        <form name="searchTimeslot" method="get" action="searchTimeslot">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn btn-dark">Search Timeslot</button>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div>
                <div>
                    <sec:authorize access="hasRole('HCP')">
                        <form name="timeslot" method="get" action="addTimeslot">
                            <div class="form-group row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn btn-dark">Add Timeslot</button>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div>
                <br/>
                <br/>
                <div>
                    <form action="logout" method="post">
                        <sec:csrfInput />
                        <button type="submit" class="btn btn btn-danger">Sign Out</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>