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
    <title>Find a User</title>
</head>
<body>
<form action="findtheatre" method="post">
    <h1>Search for a theatre by location</h1>
    <p>
        <label for="location">Location</label>
        <input id="location" name="location" value="${fn:escapeXml(param.location)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="theatreCreate"><a href="theatrecreate">Create Theatre</a></div>
<br/>
<h1>Matching Theatres</h1>
<table border="1">
    <tr>
        <th>Theatre ID</th>
        <th>Theatre Name</th>
        <th>Theatre Location</th>
        <th>Theatre Type</th>
    </tr>
    <c:forEach items="${theatres}" var="theatre" >
        <tr>
            <td><c:out value="${theatre.getTheatreid()}" /></td>
            <td><c:out value="${theatre.getTheatrename()}" /></td>
            <td><c:out value="${theatre.getLocation()}" /></td>
            <td><c:out value="${theatre.getTheatreType()}" /></td>
            <td><a href="theatredelete?theatreid=<c:out value="${theatre.getTheatreid()}"/>">Delete</a></td>
            <td><a href="theatreupdate?theatreid=<c:out value="${theatre.getTheatreid()}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

