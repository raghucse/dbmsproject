<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="CSS/dashboard.css">
    <link rel="stylesheet" href="CSS/navbar.css">
    <link rel="stylesheet" href="CSS/main.css">
</head>
<body>
<div class="topnav">
    <a class="active" href="DashBoard.jsp">Home</a>
    <a href="logoutUser" class="pull-right">Logout</a>
    <a href="profile" class="pull-right">Profile</a>
</div>	
<div class="container" style="margin-top: 8%;">
    <div class="col-md-6 col-md-offset-3">
        <div class="row">
            <div id="logo" class="text-center">
                <p style="font-size: 18px;">Search for movies by Movie Name or users by User Name</p>
            </div>
            <form role="form" id="form-buscar" action="dashboardsearch" method="get">
                <div class="form-group">
                    <div class="input-group">
                        <input id="1" class="form-control" type="text" name="search" placeholder="Search..." required/>
                        <span class="input-group-btn">
                            <button class="btn" type="submit">
                                <i class="glyphicon glyphicon-search" aria-hidden="true"></i> Search
                            </button>
						</span>
                    </div>
                </div>
                <div class="form-group row" style="width: 60%;margin: 20px auto;">
                    <div class="col-md-6">
                        <input type="radio" id="user" name="selection" value="0"/>
                        <label for="user">User</label>
                    </div>
                    <div class="col-md-6">
                        <input type="radio" id="movie" name="selection" value="1"/>
                        <label for="movie">Movie</label>
                    </div>
                </div>
            </form>
        </div>
                <a href="/usermovies" class="btn btn-lg btn-block">Show Recommended Movies</a>
                <a href="AllMoviesUser.jsp" class="btn btn-lg btn-block">Show All Movies</a>
        </div>
    </div>

</div>
</body>
</html>