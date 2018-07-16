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
	<form action="findusers" method="post">
		<h1>Search for a User by User Name</h1>
		<p>
			<label for="username">User Name</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create User</a></div>
	<br/>
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Password</th>
                <th>Phone</th>
                <th>Delete BlogUser</th>
                <th>Update BlogUser</th>
            </tr>

                <tr>
                    <td><c:out value="${users.getUserName()}" /></td>
                    <td><c:out value="${users.getFirstName()}" /></td>
                    <td><c:out value="${users.getLastName()}" /></td>
                    <td><c:out value="${users.getPassword()}" /></td>
                    <td><c:out value="${users.getPhone()}" /></td>
                    <td><a href="userdelete?username=<c:out value="${users.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${users.getUserName()}"/>">Update</a></td>
                </tr>
           
       </table>
</body>
</html>

