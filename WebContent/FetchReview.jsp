<%--
  Created by IntelliJ IDEA.
  User: harsh
  Date: 8/6/18
  Time: 6:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=ISO-8859-1"
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
    <title>Recommendations</title>
</head>
<body>
<div class="row-expanded top-bar" style="background: black;margin-bottom: 30px;" >
    <div class="top-bar-left">
        <ul class="dropdown menu" style="background: black"  data-dropdown-menu>
            <li class="menu-text" style="color:white;font-size: 18px;">MovieTalk</li>
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
<form action="fetchreview" method="post">
    <h4 style="text-align: center">Here are the list of Reviews available</h4>
    <h6>Filter By Username by adding it into the input box</h6>
    <p>
        <label for="username">UserName</label>
        <input id="username" name="username" value="${fn:escapeXml(param.username)}">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/>
<div id="createreviews"><a  class="button" href="createreviews">Create Reviews</a></div>
<br/>
<h1>Matching Reviews</h1>

<table border="1">
    <tr>
        <th>Review ID</th>
        <th>UserName</th>
        <th>Movie ID </th>
        <th>Rating</th>
        <th>Content</th>
        <th>Delete Review</th>
        <th>Update Review</th>
    </tr>

        <tr>
            <td><c:out value="${reviews.getReviewsid()}" /></td>
            <td><c:out value="${reviews.getUsers().getUserName()}" /></td>
            <td><c:out value="${reviews.getMovies().getMoviename()}" /></td>
            <td><c:out value="${reviews.getRating()}" /></td>
            <td><c:out value="${reviews.getContent()}" /></td>
            <td>
                <form action="removereview" method="post">
                    <p>
                        <%--<label for="reviewid">ReviewId</label>--%>
                        <input id="reviewid" name="reviewid" value="<c:out value="${reviews.getReviewsid()}"/>" style="display:none;"/>
                    </div>
                    </p>
                    <p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
                    </p>
                </form>

                <a href="removereview?reviewid=<c:out value="${reviews.getReviewsid()}"/>">Delete</a></td>
            <td><a href="editreview?reviewid=<c:out value="${reviews.getReviewsid()}"/>">Update</a></td>
        </tr>
</table>
</body>
</html>


