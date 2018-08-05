<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="stylesheet" href="CSS/navbar.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/css/foundation-float.min.css" integrity="sha256-SXzNzz68b8cy/1oRvdP128/5VlvegrZO5QDmBiqhlQc= sha384-5EVTk1nWllg1T/XoVEd82fhnOxUhRUOquz4AyO3+M0kbhUzhruouReQWPnihRxPs sha512-y0IHToRgzE99SfoteKLXR0MUOTRPMQHwc4nmU2/Uqhu4KulOzK18RbuicuhlgzPDdj23skwxTWUv7CJs+psXig==" crossorigin="anonymous">

</head>
<div class="topnav">
    <a class="active" href="DashBoard.jsp">Home</a>
    <a href="logoutUser" class="pull-right">Logout</a>
    <a href="profile" class="pull-right">Profile</a>
</div>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2>Add credit card</h2>
                <p>Please enter your Credit card details</p>
            </div>
            <form id="Login" action="usercreditcard" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="cardnumber" name="cardnumber" placeholder="Card Number"
                           value="">
                </div>
                <div class="form-group">
                    <input type="date" class="form-control" id="expiration" name="expiration" placeholder="Expiration Date"
                           value="">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
            <span id="failure"><b>${messages.failed}</b></span>
        </div>
    </div>
    <table border="1">
        <tr>
            <th>CardNumber</th>
            <th>Card Expiration</th>
        </tr>
        <c:forEach items="${creditcard}" var="creditcard" >
            <tr>
                <td><c:out value="${creditcard.getCardnumber()}" /></td>
                <td><c:out value="${creditcard.getDate()}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>