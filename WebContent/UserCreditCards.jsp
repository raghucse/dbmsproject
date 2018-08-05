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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        body{
            max-width: 1400px;
            margin: auto !important;
        }
        .top-bar-right a:hover{
            text-decoration: underline;
        }
    </style>
    <!-- foundation-float.min.css: Compressed CSS with legacy Float Grid -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/css/foundation-float.min.css" integrity="sha256-SXzNzz68b8cy/1oRvdP128/5VlvegrZO5QDmBiqhlQc= sha384-5EVTk1nWllg1T/XoVEd82fhnOxUhRUOquz4AyO3+M0kbhUzhruouReQWPnihRxPs sha512-y0IHToRgzE99SfoteKLXR0MUOTRPMQHwc4nmU2/Uqhu4KulOzK18RbuicuhlgzPDdj23skwxTWUv7CJs+psXig==" crossorigin="anonymous">
    <!-- Compressed JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/js/foundation.min.js" integrity="sha256-G6jsRyH1fxbsvFIXSCuwYmI1aIDYBa28xscrvmYjJy0= sha384-vtoG68NvPc9azmFJr447vvY8qgdyA4FdaJ5/bqvzIM4eAdZfO0iyRRF8l2AAscYI sha512-43seCcNrHA0BQgrtyajB9sp8yOdv5c8QdYvgjP7zJ7v+dmzAcxYDQ2gupb9aztsNWBq1COIp/3NHYkQs4l/dkg==" crossorigin="anonymous"></script>
</head>
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
            <th>User Name</th>
            <th>Delete Card</th>
            <th>Update Card</th>
        </tr>
        <c:forEach items="${creditcard}" var="creditcard" >
            <tr>
                <td><c:out value="${creditcard.getCardnumber()}" /></td>
                <td><c:out value="${creditcard.getDate()}" /></td>
                <td><c:out value="${creditcard.getUsers().getUserName()}" /></td>
                <td><a href="deletecreditcard?cardnumber=<c:out value="${creditcard.getCardnumber()}"/>">Delete</a></td>
                <td><a href="updatecreditcard?cardnumber=<c:out value="${creditcard.getCardnumber()}"/>">Update</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>