<%@ page import="com.model.User" %>
<%@ page import="com.model.Address" %>
<%@ page import="com.repository.impl.AddressRepositoryImpl" %>
<%@ page import="com.repository.AddressRepository" %><%--
Created by IntelliJ IDEA.
User: gamer
Date: 13.10.2023
Time: 0:25
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User Information page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
            margin-top: 20px;
            color: #1a202c;
            text-align: left;
            background-color: #e2e8f0;
        }
        .main-body {
            padding: 15px;
        }
        .card {
            box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0 rgba(0, 0, 0, .06);
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid rgba(0, 0, 0, .125);
            border-radius: .25rem;
        }
        .card-body {
            flex: 1 1 auto;
            min-height: 1px;
            padding: 1rem;
        }
        .gutters-sm {
            margin-right: -8px;
            margin-left: -8px;
        }
        .gutters-sm > .col, .gutters-sm > [class*=col-] {
            padding-right: 8px;
            padding-left: 8px;
        }
        .mb-3 {
            margin-bottom: 1rem !important;
        }
    </style>
</head>
<%
    User user = (User) session.getAttribute("user");
    AddressRepository addressRepository =new  AddressRepositoryImpl();
    Address addressDb = addressRepository.getAddressByUserId(user.getId());
    Address address = Address.builder().country("").city("").street("").home("").build();
    if (addressDb != null) {
        address = addressDb;
    }
%>
<body>
<h1 class="text-center">Update User Information Page</h1>
</br>
<hr>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4><%=user.getName() + " " + user.getSurname()%>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <form method="POST" action="/update-user-info">
                            <h3>User</h3>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="name" value="<%= user.getName()%>">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Surname</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="surname" value="<%= user.getSurname()%>">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Age</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="age" value="<%= user.getAge()%>">
                                </div>
                            </div>
                            <hr>
                            <h3>Address</h3>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Country</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="country" value="<%= address.getCountry() %>">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">City</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="city" value="<%= address.getCity() %>">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Street</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="street" value="<%= address.getStreet() %>">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Home</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" name="home" value="<%= address.getHome() %>">
                                </div>
                            </div>
                            <hr>
                            <div class="row ">
                                <div class="col-sm-6 text-center">
                                    <input class="btn btn-success" type="submit" value="SAVE">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
</body>
</html>