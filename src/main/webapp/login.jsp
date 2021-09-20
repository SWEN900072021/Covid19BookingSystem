<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
</head>
<body>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Login
</h4>
<br/>
<div class="card text-center border-secondary mb-3"
     style="width: 25rem;margin: 0 auto;float: none;margin-bottom: 10px;">
    <div class="card-body">
        <form action="login" method='POST'>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="form-group row">
                <label for="usernameId" class="col-sm-4 col-form-label"><strong>Username:</strong></label>
                <div class="col-sm-8">
                    <input
                            type='text'
                            class="form-control"
                            id="usernameId"
                            name='username'
                    >
                </div>
            </div>
            <div class="form-group row">
                <label for="passwordId" class="col-sm-4 col-form-label"><strong>Password:</strong></label>
                <div class="col-sm-8">
                    <input
                            type='password'
                            class="form-control"
                            id="passwordId"
                            name='password'
                    >
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-dark">Login</button>
                </div>
            </div>
        </form>
        <br/>
        <p>Don't have an account?</p>
        <form method="get" action="createVaccineRecipient">
            <div class="form-group row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn btn-dark">Create a Vaccine Recipient Account</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>