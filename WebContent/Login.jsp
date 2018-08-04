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
	<form action="loginUser" method="get">
		<h1>Enter Credentials to login</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>
        <p>
			<label for="password">Password</label>
			<input id="password" name="password" type="password" value="${fn:escapeXml(param.password)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="failure"><b>${messages.failed}</b></span>
		</p>
	</form>
	<br/>
	<div id="register"><a href="Register.jsp">Create User</a></div>
	<br/>
</body>
</html>
