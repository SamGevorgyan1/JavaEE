<%--
  Created by IntelliJ IDEA.
  User: Digi School
  Date: 10/4/2023
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <style>
        body {
            text-align: center;
        }
        input.submit {
            width: 150px;
            background-color: #ff8800;
            color: white;
            border-radius: 10px;
            padding: 10px;
            font-size: 18px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>Registration Page</h1>
</br>
<%
    String errorMessage = (String) (request.getAttribute("errorMessage"));
    if (errorMessage != null) {
        response.getWriter().print("<span class='error'>" + errorMessage + "</span>");
    }
%>
<hr>
</br>
<div style="width: 100%; display: flex; justify-content: center">
    <form style="display: inline-block; text-align: center; width: 400px" method="post" action="${pageContext.request.contextPath}/registration">
        <p style="display: flex; justify-content: space-around"><span style="width: 50px">name:</span> <input
                type="text" name="name"><br><br></p>
        <p style="display: flex; justify-content: space-around"><span style="width: 50px">surname:</span> <input
                type="text" name="surname"><br><br></p>
        <p style="display: flex; justify-content: space-around"><span style="width: 50px">age:</span> <input
                type="text" name="age"><br><br></p>
        <p style="display: flex; justify-content: space-around"><span style="width: 50px">email:</span> <input
                type="text" name="email"><br><br></p>
        <p style="display: flex; justify-content: space-around"><span style="width: 50px">password:</span> <input
                type="password" name="password"><br><br></p>
        <input type=submit class="submit" name="submit"><br><br>
    </form>
</div>
</body>
</html>