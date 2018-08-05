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
    <style>
        body{
            max-width: 1400px;
            margin: auto !important;
        }
        .top-bar {
            background-color: #000000;
        }
        .top-bar-right a:hover{
            text-decoration: underline;
        }

    </style>
    <!-- foundation-float.min.css: Compressed CSS with legacy Float Grid -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/css/foundation-float.min.css" integrity="sha256-SXzNzz68b8cy/1oRvdP128/5VlvegrZO5QDmBiqhlQc= sha384-5EVTk1nWllg1T/XoVEd82fhnOxUhRUOquz4AyO3+M0kbhUzhruouReQWPnihRxPs sha512-y0IHToRgzE99SfoteKLXR0MUOTRPMQHwc4nmU2/Uqhu4KulOzK18RbuicuhlgzPDdj23skwxTWUv7CJs+psXig==" crossorigin="anonymous">
    <!-- Compressed JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.5.0-rc.2/dist/js/foundation.min.js" integrity="sha256-G6jsRyH1fxbsvFIXSCuwYmI1aIDYBa28xscrvmYjJy0= sha384-vtoG68NvPc9azmFJr447vvY8qgdyA4FdaJ5/bqvzIM4eAdZfO0iyRRF8l2AAscYI sha512-43seCcNrHA0BQgrtyajB9sp8yOdv5c8QdYvgjP7zJ7v+dmzAcxYDQ2gupb9aztsNWBq1COIp/3NHYkQs4l/dkg==" crossorigin="anonymous"></script>
    <title>Movies</title>
</head>
<body>
<div class="row-expanded top-bar" style="background: black;margin-bottom: 30px;" >
    <div class="top-bar-left">
        <ul class="dropdown menu" style="background: black"  data-dropdown-menu>
            <li class="menu-text" style="color:white;font-size: 18px;"> MovieTalk</li>
        </ul>
    </div>
    <div class="top-bar-right">
        <ul class="menu" style="background: none;">
            <li  style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findmovieusers">Users</a> </li>
            <li  style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findcreditcard">CreditCard</a> </li>
            <li style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findmovies">Movies</a> </li>
            <li style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findtheatre">Theatre</a> </li>
            <li  style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findrecommendations">Recommendations</a> </li>
            <li style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findshowinfo">ShowTimes</a> </li>
            <li style="color:white;font-size: 16px;"><a style="color:white;font-size: 16px;" href="findreviews">Reviews</a> </li>

        </ul>


    </div>
</div>
<form action="findmovies" method="post">
    <h4 style="text-align: center">Here are the list of Movies available</h4>
    <h6>Filter By Language by adding it into the input box</h6>
    <p>
        <label for="language">Language</label>
        <input id="language" name="language" value="${fn:escapeXml(param.language)}">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/>
<div id="createmovies"><a  class="button" href="createmovies">Create Movie</a></div>
<br/>
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
        <th>Average rating</th>
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
            <td><c:out value="${movies.getAvg_rating()}" /></td>
            <td><a href="deletemovies?movieid=<c:out value="${movies.getMoviesId()}"/>">Delete</a></td>
            <td><a href="updatemovie?movieid=<c:out value="${movies.getMoviesId()}"/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


