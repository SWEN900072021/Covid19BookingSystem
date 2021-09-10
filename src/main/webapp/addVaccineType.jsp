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
    Number of doses: <input type = "number" name = "dose_number"><br/>
    <input type = "submit" value = "Add vaccine type">
</form>
</body>
</html>
