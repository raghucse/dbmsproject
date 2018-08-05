<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="CSS/navbar.css">
    <link rel="stylesheet" href="CSS/profile.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<div class="topnav">
    <a class="active" href="DashBoard.jsp">Home</a>
    <a href="logoutUser" class="pull-right">Logout</a>
    <a href="profile" class="pull-right">Profile</a>
</div>
<body>
<div class="container profile-box">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${user.getUserName()}</h3>
                </div>
                <div class="panel-body">
                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-user-information">
                            <tbody>
                            <tr>
                                <td>First name:</td>
                                <td>${user.getFirstName()}</td>
                            </tr>
                            <tr>
                                <td>Last name:</td>
                                <td>${user.getLastName()}</td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td>${user.getEmail()}</td>
                            </tr>
                            <td>Phone Number:</td>
                            <td>${user.getPhone()}</td>
                            </tr>
                            </tbody>
                        </table>

                        <span class="twPc-StatLabel twPc-block">Following:</span>
                        <span class="twPc-StatValue">${Following}</span>

                        <span class="twPc-StatValue pull-right">${Followers}</span>
                        <span class="twPc-StatLabel twPc-block pull-right">Followers:</span>
                    </div>
                </div>
                <div class="panel-footer">
                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
                       class="btn btn-sm btn-primary" href="usercreditcard">Credit cards</a>
                    <span class="pull-right">
                        <a data-original-title="Remove this user" data-toggle="tooltip" type="button"
                           class="btn btn-sm btn-danger" href="userdelete"><i class="glyphicon glyphicon-remove"></i></a>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
