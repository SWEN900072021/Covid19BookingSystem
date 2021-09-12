<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head></head>
<body>
<h1>Login</h1>
<form name='login' action="login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>

<h1>Create Account</h1>
<form name="create_vr_account" method="get" action="public/createAccount.jsp">
    <input type="submit" value="Create Account" >
</form>
</body>
</html>