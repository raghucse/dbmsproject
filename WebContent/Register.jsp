<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="CSS/register.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2>User Registration</h2>
                <p>Enter your details</p>
            </div>
            <form id="Login" action="movieusercreate" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="UserName"
                           value="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                           value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First name"
                           value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name"
                           value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="email" name="email" placeholder="email" value="">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="phone" value="">
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
            <span id="successMessage"><b>${messages.success}</b></span>
            <div class="forgot">
                <a href="Login.jsp">Login</a>
            </div>
        </div>

    </div>
</div>
</div>
</body>
</html>