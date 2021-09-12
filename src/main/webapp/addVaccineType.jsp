<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Vaccine Type</title>
</head>
<body>
<form action = "addVaccineType" method = "post">
    Vaccine name: <input type = "text" name = "vaccine_name"><br/>
    Vaccine class:
    <select name="vaccine_class">
        <option value="mrna">Messenger RNA (mRNA)</option>
        <option value="inactiavated">Inactivated</option>
        <option value="attenuated">Live-attenuated</option>
        <option value="subunit">Subunit, recombinant, polysaccharide, and conjugate</option>
        <option value="toxoid">Toxoid</option>
        <option value="viral">Viral vector</option>
        ...
    </select><br/>
<<<<<<< Updated upstream
    Number of doses: <input type = "number" name = "dose_number"><br/>
=======
    Minimum age requirement: <input type = "number" name = "min_age"><br/>
    Maximum age requirement: <input type = "number" name = "max_age"><br/>
    Number of doses: <input type = "number" name = "dose_number"><br/>
    Blood problem restriction:
    <select name="blood_restriction">
        <option value="false">No</option>
        <option value="true">Yes</option>
        ...
    </select><br/>
>>>>>>> Stashed changes
    <input type = "submit" value = "Add vaccine type">
</form>
</body>
</html>
