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
    <title>Create a User</title>
</head>
<body>
<h1>Create User</h1>
<form action="createmovies" method="post">
    <p>
        <label for="movieid">Movie id</label>
        <input id="movieid" name="movieid" value="">
    </p>
    <p>
        <label for="moviename">Movie Name</label>
        <input id="moviename" name="moviename" value="">
    </p>
    <p>
        <label for="overview">overview</label>
        <input id="overview" name="overview" value="">
    </p>
    <p>
        <label for="releasedate">Release Date</label>
        <input id="releasedate" name="releasedate" value="">
    </p>
    <p>
        <label for="country">Country</label>
        <input id="country" name="country" value="">
    </p>
    <p>
        <label for="language">Language</label>
        <input id="language" name="language" value="">
    </p>
    <p>
        <label for="genre">Genre</label>
        <input id="genre" name="genre" value="">
    </p>
    <p>
        <label for="runtime">Runtime</label>
        <input id="runtime" name="runtime" value="">
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