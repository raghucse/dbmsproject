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
    <link rel="stylesheet" href="CSS/navbar.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
<div class="topnav">
    <a class="active" href="DashBoard.jsp">Home</a>
    <a href="logoutUser" class="pull-right">Logout</a>
    <a href="profile" class="pull-right">Profile</a>
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
        <%--<th>Movie ID</th>--%>
        <th>Movie Name</th>
        <th>Movie Overview</th>
        <th>Movie ReleaseDate</th>
        <th>Country</th>
        <th>Language</th>
        <th>Genre</th>
        <th>Runtime</th>
        <th>Average rating</th>
        <th>Recommend</th>
        <th>Review</th>
        <th>Shows</th>
    </tr>
    <c:forEach items="${movies}" var="movies" >
        <tr>
            <%--<form action="${pageContext.request.contextPath}/allmoviesuser" method="post">--%>
                <%--<td><c:out value="${movies.getMoviesId()}" /></td>--%>
                <td><c:out value="${movies.getMoviename()}" /></td>
                <td><c:out value="${movies.getOverview()}" /></td>
                <td><c:out value="${movies.getReleasedate()}" /></td>
                <td><c:out value="${movies.getCountry()}" /></td>
                <td><c:out value="${movies.getLanguage()}" /></td>
                <td><c:out value="${movies.getGenre()}" /></td>
                <td><c:out value="${movies.getRuntime()}" /></td>
                <td><c:out value="${movies.getAvg_rating()}" /></td>
                <td>
                    <a href="allmoviesuser?movieid=<c:out value="${movies.getMoviesId()}"/>">Recommend</a>
                        <%--<input type="submit" name="button2" value="Button 2" />--%>
                        <%--<input type="submit" name="button3" value="Button 3" />--%>

                    <%--<input type="checkbox" name="checkfield" id="g01-01" value="${movies.isRecommended()}"  onchange="doalert(this)"/>--%>
                </td>
                <td>
                    <a href="fetchreview?movieid=<c:out value="${movies.getMoviesId()}"/>">Review</a>
                                            <%--<input type="submit" name="button2" value="Button 2" />--%>
                        <%--<input type="submit" name="button3" value="Button 3" />--%>

                        <%--<input type="checkbox" name="checkfield" id="g01-01" value="${movies.isRecommended()}"  onchange="doalert(this)"/>--%>
                </td>
                <td>
                    <a href="fetchshowtime?movieid=<c:out value="${movies.getMoviesId()}"/>">Show Timings</a>

                </td>
            <%--</form>--%>


        </tr>
    </c:forEach>
</table>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>


