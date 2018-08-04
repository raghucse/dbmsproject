<%--
  Created by IntelliJ IDEA.
  User: nikithanagaraj
  Date: 7/15/18
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find </title>
    <style>
        body{
            max-width: 1400px;
            margin: auto !important;
        }
    </style>
    <!-- foundation-float.min.css: Compressed CSS with legacy Float Grid -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/css/foundation-float.min.css" integrity="sha256-SXzNzz68b8cy/1oRvdP128/5VlvegrZO5QDmBiqhlQc= sha384-5EVTk1nWllg1T/XoVEd82fhnOxUhRUOquz4AyO3+M0kbhUzhruouReQWPnihRxPs sha512-y0IHToRgzE99SfoteKLXR0MUOTRPMQHwc4nmU2/Uqhu4KulOzK18RbuicuhlgzPDdj23skwxTWUv7CJs+psXig==" crossorigin="anonymous">
    <!-- Compressed JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/js/foundation.min.js" integrity="sha256-G6jsRyH1fxbsvFIXSCuwYmI1aIDYBa28xscrvmYjJy0= sha384-vtoG68NvPc9azmFJr447vvY8qgdyA4FdaJ5/bqvzIM4eAdZfO0iyRRF8l2AAscYI sha512-43seCcNrHA0BQgrtyajB9sp8yOdv5c8QdYvgjP7zJ7v+dmzAcxYDQ2gupb9aztsNWBq1COIp/3NHYkQs4l/dkg==" crossorigin="anonymous"></script>

</head>
<body>
<form action="findcreditcard" method="post">
    <h1>Search for a creditcard by user name</h1>
    <p>
        <label for="username">Username</label>
        <input id="username" name="username" value="${fn:escapeXml(param.username)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="createcreditcard"><a href="createcreditcard">Create Theatre</a></div>
<br/>
<h1>Matching Creditcards</h1>
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
            <td><c:out value="${creditcard.getUsers()}" /></td>
            <td><a href="deletecreditcard?cardnumber=<c:out value="${creditcard.getCardnumber()}"/>">Delete</a></td>
            <td><a href="updatecreditcard?cardnumber=<c:out value="${creditcard.getCardnumber()}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

