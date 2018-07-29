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
    <title>Find a Movies</title>
</head>
<body>
<form action="findmovies" method="post">
    <h1>Search for a Movies by Language</h1>
    <p>
        <label for="language">Language</label>
        <input id="language" name="language" value="${fn:escapeXml(param.language)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="createmovies"><a href="createmovies">Create Movie</a></div>
<br/>
<h1>Matching Movies</h1>

<table border="1">
    <tr>
        <th>Movie ID</th>
        <th>Movie Name</th>
        <th>Movie Overview</th>
        <th>Movie ReleaseDate</th>
        <th>Country</th>
        <th>Language</th>
        <th>Genre</th>
        <th>Runtime</th>
        <th>Delete Movie</th>
        <th>Update Movie</th>

    </tr>
    <c:forEach items="${movies}" var="movies" >
        <tr>
            <td><c:out value="${movies.getMoviesId()}" /></td>
            <td><c:out value="${movies.getMoviename()}" /></td>
            <td><c:out value="${movies.getOverview()}" /></td>
            <td><c:out value="${movies.getReleasedate()}" /></td>
            <td><c:out value="${movies.getCountry()}" /></td>
            <td><c:out value="${movies.getLanguage()}" /></td>
            <td><c:out value="${movies.getGenre()}" /></td>
            <td><c:out value="${movies.getRuntime()}" /></td>
            <td><a href="deletemovies?movieid=<c:out value="${movies.getMoviesId()}"/>">Delete</a></td>
            <td><a href="updatemovie?movieid=<c:out value="${movies.getMoviesId()}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


