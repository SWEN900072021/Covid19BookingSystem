<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
            rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
            crossorigin="anonymous"
    >
    <title>Outcome</title>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
    </symbol>
    <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
    </symbol>
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
</svg>
<h4
        class="display-4"
        style="display: flex;justify-content: center;margin-top: 20px;font-size: 45px"
>
    Outcome
</h4>
<br/>
<%
    if (request.getParameter("success") != null) {
        if (request.getParameter("success").equals("true")) {
%>
            <div class="alert alert-success d-flex align-items-center" role="alert"
                 style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                <div>
                    Your request has been successful.
                </div>
            </div>
<%
        } else if (request.getParameter("success").equals("version_mismatch")) {
%>
            <div class="alert alert-danger d-flex align-items-center" role="alert"
                 style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Someone else has already booked this timeslot. Please try again.
                </div>
            </div>
<%
        } else if (request.getParameter("success").equals("lock_error")) {
%>
            <div class="alert alert-danger d-flex align-items-center" role="alert"
                 style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Someone else has is editing this timeslot. Please try again later.
                </div>
            </div>
            <br/>
            <form name="return_editing_timeslots" method="get" action="editTimeslot"
                  style="margin: 0 auto;float: none;margin-bottom: 10px;text-align: center">
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn btn-dark">Edit Other Timeslots</button>
                    </div>
                </div>
            </form>
<%
        } else {
%>
            <div class="alert alert-danger d-flex align-items-center" role="alert"
                 style="width: 40rem;margin: 0 auto;float: none;margin-bottom: 10px;">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    You encountered an error. Please try again.
                </div>
            </div>
<%
        }
    }
%>
<br/>
<form name="return_home" method="get" action="home"
      style="margin: 0 auto;float: none;margin-bottom: 10px;text-align: center">
    <div class="form-group row">
        <div class="col-sm-12">
            <button type="submit" class="btn btn btn-dark">Return Home</button>
        </div>
    </div>
</form>
<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
        crossorigin="anonymous"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
        integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
        crossorigin="anonymous"
></script>
</body>
</html>
