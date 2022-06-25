
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form method="post" action='login'>
            <label>User name:</label>
            <input type="text" name="user_in" value="">
            <br>
            <label>Password:</label>
            <input type="password" name="pass_in" value="">
            <br>
            <input type="submit">
        </form>
        ${message}
    </body>
</html>
