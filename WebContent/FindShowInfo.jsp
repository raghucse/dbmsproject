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
    <title>Show Info</title>
</head>
<body>
<div id="createshow"><a href="createshowinfo">Create showinfo</a></div>
<div id="findshowinfo"><a href="/findshowinfo">find shows showinfo</a></div>

<br/>
<h1>Matching Shows</h1>
<table border="1">
    <tr>
        <th>ShowInfoID</th>
        <th>TheatreId</th>
        <th>MovieId</th>
        <th>Price</th>
        <th>ShowTime</th>
        <th>Delete Show Info</th>
        <th>Update ShowInfo</th>
    </tr>
    <c:forEach items="${showinfo}" var="showinfo" >
        <tr>
            <td><c:out value="${showinfo.getShowinfoid()}" /></td>
            <td><c:out value="${showinfo.getTheatre().getTheatreid()}" /></td>
            <td><c:out value="${showinfo.getMovies().getMoviesId}" /></td>
            <td><c:out value="${showinfo.getPrice()}" /></td>
            <td><c:out value="${showinfo.getShowtime()}" /></td>
            <td><a href="deleteshowinof?showinfoid=<c:out value="${showinfo.getShowinfoid()}"/>">Delete</a></td>
            <td><a href="updateshowinfo?showinfo=<c:out value="${showinfo.getShowinfoid()}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

