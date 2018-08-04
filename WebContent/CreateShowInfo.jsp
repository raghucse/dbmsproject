
<%--
  Created by IntelliJ IDEA.
  User: nikithanagaraj
  Date: 7/15/18
  Time: 6:50 PM
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
    <title>Create ShowInfo</title>
</head>
<body>
<h1>Create ShowInfo</h1>
<form action="createshowinfo" method="post">
    <p>
        <label for="showinfoid">Show Info Id</label>
        <input id="showinfoid" name="showinfoid" value="">
    </p>
    <p>
        <label for="theatreid">theatre Id</label>
        <input id="theatreid" name="theatreid" value="">
    </p>
    <p>
        <label for="movieid">theatre Id</label>
        <input id="movieid" name="movieid" value="">
    </p>
    <p>
        <label for="showtime">Show Time</label>
        <input id="showtime" name="showtime" value="">
    </p>
    <p>
        <label for="price">Price</label>
        <input id="price" name="price" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>
