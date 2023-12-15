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
    <title>Login Page</title>
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
<h1>Login Page</h1>
</br>
<%
    String errorMessage = (String) (request.getAttribute("errorMessage"));
    if (errorMessage != null) {
        response.getWriter().print("<span class='error'>" + errorMessage + "</span>");
    }
%>
<hr>
</br>
<div style="width: 100% ;display: flex;justify-content: center;">
    <form style="display: inline-block; text-align: center; width: 400px" method="POST" action="/login">
        <p style="display: flex;justify-content: space-around">
            <span style="width: 50px">Email:</span>
            <input type="text" name="email">
        </p>
        <p style="display: flex;justify-content: space-around">
            <span style="width: 50px">Password:</span>
            <input type="password" name="password">
        </p>
        <p style="display: flex;justify-content: space-around">
            <span style="width: 100%"><a href="${pageContext.request.contextPath}/registration-page.jsp">Registration</a></span>
        </p>
        <p style="display: flex;justify-content: space-around">
            <span style="width: 100%"><a href="${pageContext.request.contextPath}/forgot-password.jsp">Forgot password</a></span>
        </p>
        <br>
        <input class="submit" type="submit" name="login" value="LOGIN">
    </form>
</div>
</body>
</html>