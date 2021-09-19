<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Sign Up</title>
</head>
<body>
    <h4
            class="display-4"
            style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
    >
        Create a New Vaccine Recipient Account
    </h4>
    <br/>
    <div class="card text-center border-secondary mb-3" style="width: 50rem;margin: 0 auto;float: none;margin-bottom: 10px;">
        <div class="card-body">
            <form action = "createVaccineRecipient" method = "post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="form-group row">
                    <label for="usernameId" class="col-sm-4 col-form-label"><strong>Username:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="usernameId"
                                name = "username"
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
                                name = "password"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="firstNameId" class="col-sm-4 col-form-label"><strong>First Name:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="firstNameId"
                                name = "firstName"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lastNameId" class="col-sm-4 col-form-label"><strong>Last Name:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="lastNameId"
                                name = "lastName"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="addressFirstLineId" class="col-sm-4 col-form-label"><strong>Address Line 1:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="addressFirstLineId"
                                name = "addressLine1"
                                placeholder="Unit Number..."
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="addressSecondLineId" class="col-sm-4 col-form-label"><strong>Address Line 2:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="addressSecondLineId"
                                name = "addressLine2"
                                placeholder="Street Address..."
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="stateId" class="col-sm-4 col-form-label"><strong>State:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="stateId"
                                name = "state"
                                placeholder="VIC, NSW, NT, WA,..."
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="postcodeId" class="col-sm-4 col-form-label"><strong>Postcode:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="postcodeId"
                                name = "postcode"
                                placeholder="3000, 3008,..."
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="countryId" class="col-sm-4 col-form-label"><strong>Country:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="countryId"
                                name = "country"
                                placeholder="Australia..."
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="dobId" class="col-sm-4 col-form-label"><strong>Date of Birth:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="date"
                                class="form-control"
                                id="dobId"
                                name = "dateOfBirth"
                                required
                        >
                    </div>
                </div>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-4 pt-0"><strong>Gender:</strong></legend>
                        <div class="col-sm-8" style="text-align: left">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gender" id="female" value="Female" checked>
                                <label class="form-check-label" for="female">
                                    Female
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gender" id="male" value="Male">
                                <label class="form-check-label" for="male">
                                    Male
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gender" id="undisclosed" value="Undisclosed">
                                <label class="form-check-label" for="undisclosed">
                                    Prefer Not to Answer
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="form-group row">
                    <label for="phoneNumberId" class="col-sm-4 col-form-label"><strong>Phone Number:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="phoneNumberId"
                                name = "phoneNumber"
                        >
                    </div>
                </div>
                <div class="form-group row">
                    <label for="emailId" class="col-sm-4 col-form-label"><strong>Email:</strong></label>
                    <div class="col-sm-8">
                        <input
                                type="text"
                                class="form-control"
                                id="emailId"
                                name = "email"
                        >
                    </div>
                </div>
                <input type="hidden" name="accountType" value="VR">
                <br/>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn btn-dark">Create Account</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>