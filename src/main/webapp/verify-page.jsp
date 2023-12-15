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
    <title>Verify Page</title>
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
    </style>
</head>
<body>
<h1>Verify Page</h1>
</br>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
        response.getWriter().println(errorMessage);
    }
%>
<hr>
</br>
<div style="width: 100% ;display: flex;justify-content: center;">
    <form style="display: inline-block; text-align: center; width: 600px" method="POST" action="/verify">
        <p style="display: flex;justify-content: space-around">
            <span style="width: 160px">Verify code:</span>
            <input type="text" name="verifyCode">
        </p>
        <br>
        <input class="submit" type="submit" name="verify" value="VERIFY">
    </form>
</div>
</body>
</html>