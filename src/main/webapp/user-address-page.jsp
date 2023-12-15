<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 18.10.2023
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Address</title>
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
<h1>Add Address Page</h1>
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
    <form style="display: inline-block; text-align: center; width: 400px" method="post" action="/add-address">
        <p style="display: flex; justify-content: space-around">
            <span style="width: 50px">Country:</span>
            <input type="text" name="country">
        </p>
        <p style="display: flex; justify-content: space-around">
            <span style="width: 50px">City:</span>
            <input type="text" name="city">
        </p>
        <p style="display: flex; justify-content: space-around">
            <span style="width: 50px">Street:</span>
            <input type="text" name="street">
        </p>
        <p style="display: flex; justify-content: space-around">
            <span style="width: 50px">Home:</span>
            <input type="text" name="home">
        </p>
        <input type=submit class="submit" name="submit" value="save"><br><br>
    </form>
</div>
</body>
</html>
