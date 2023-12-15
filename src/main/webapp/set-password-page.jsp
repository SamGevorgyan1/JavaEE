<%--
Created by IntelliJ IDEA.
User: gamer
Date: 04.10.2023
Time: 20:09
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Password Page</title>
    <style>
        body {
            text-align: center;
        }
        input.submit {
            width: 150px;
            background-color: blue;
            color: white;
            border-radius: 10px;
            padding: 10px;
            font-size: 18px;
            text-decoration: none;
        }
        .error {
            color: red;
            text-align: center;
        }
        .success {
            color: green;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>New Password Page</h1>
</br>
<%
    String errorMessage = request.getAttribute("errorMessage") != null ? String.valueOf(request.getAttribute("errorMessage")) : null;
    if (errorMessage != null) {
        response.getWriter().print("<span class='error'>" + errorMessage + "</span>");
    }
    String verifyCode = request.getAttribute("verifyCode") != null ? String.valueOf(request.getAttribute("verifyCode")) : null;
    if (verifyCode != null) {
        response.getWriter().print("<span class='error'>Verify Code:" + verifyCode + "</span>");
    }
%>
<hr>
</br>
<div style="width: 100% ;display: flex;justify-content: center;">
    <form style="display: inline-block; text-align: center; width: 450px" method="GET" action="/forgot-password">
        <p style="display: flex;justify-content: space-around">
            <span style="width: 160px">Reset token:</span>
            <input type="text" name="resetToken">
        </p>
        <p style="display: flex;justify-content: space-around">
            <span style="width: 160px">New Password:</span>
            <input type="password" name="newPassword">
        </p>
        <p style="display: flex;justify-content: space-around">
            <span style="width: 160px">Confirm New Password:</span>
            <input type="password" name="confirmNewPassword">
        </p>
        <br>
        <input class="submit" type="submit" name="savePassword" value="SAVE">
    </form>
</div>
</body>
</html>