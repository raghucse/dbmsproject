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
    <title>Create a Theater</title>
</head>
<body>
<h1>Create Theater</h1>
<form action="theatrecreate" method="post">
    <p>
        <label for="theatreid">theatre id</label>
        <input id="theatreid" name="theatreid" value="">
    </p>
    <p>
        <label for="theatrename">theatrename</label>
        <input id="theatrename" name="theatrename" value="">
    </p>
    <p>
        <label for="location">Location</label>
        <input id="location" name="location" value="">
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