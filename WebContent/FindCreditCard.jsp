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
    </tr>
    <c:forEach items="${creditcard}" var="creditcard" >
        <tr>
            <td><c:out value="${creditcard.getCardnumber()}" /></td>
            <td><c:out value="${creditcard.getDate()}" /></td>
            <td><c:out value="${creditcard.getUsers()}" /></td>
            <td><a href="deletecreditcard?cardnumber=<c:out value="${creditcard.getCardnumber()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

