<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="CSS/login.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2>User Login</h2>
                <p>Please enter your Username and password</p>
            </div>
            <form id="Login" action="loginUser" method="get">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="UserName"
                           value="${fn:escapeXml(param.username)}">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                           value="${fn:escapeXml(param.password)}">
                </div>
                <div class="forgot">
                    <a href="Register.jsp">Register</a>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <span id="failure"><b>${messages.failed}</b></span>
        </div>
    </div>
</div>
</div>
</body>
</html>
