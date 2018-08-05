<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="CSS/searchprofile.css">
    <link rel="stylesheet" href="CSS/navbar.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="topnav">
    <a class="active" href="DashBoard.jsp">MovieTalk</a>
    <a href="logoutUser" class="pull-right">Logout</a>
    <a href="profile" class="pull-right">Profile</a>
</div>
<div class="container">
    <div class="row">
        <h1>Profile</h1>
        <div class="twPc-div">
            <a class="twPc-bg twPc-block"></a>

            <div>
                <c:if test="${not empty Users}">
                    <a class="btn btn-primary pull-right" href="followUser">Follow</a>
                </c:if>
                <a class="twPc-avatarLink">
                    <img alt="Profile Image" src="https://image.ibb.co/gshJmK/contact.png" class="twPc-avatarImg">
                </a>

                <div class="twPc-divUser">
                    <div class="twPc-divName">
                        <a>${Users.getFirstName()} ${Users.getLastName()}</a>
                    </div>
                    <span>
						<a><span>${Users.getEmail()}</span></a>
					</span>
                </div>

                <div class="twPc-divStats">
                    <ul class="twPc-Arrange">
                        <c:if test="${not empty Following}">
                            <li class="twPc-ArrangeSizeFit">
                                <span class="twPc-StatLabel twPc-block">Following</span>
                                <span class="twPc-StatValue">${Following}</span>
                            </li>
                        </c:if>
                        <c:if test="${not empty Followers}">
                            <li class="twPc-ArrangeSizeFit">
                                <span class="twPc-StatLabel twPc-block">Followers</span>
                                <span class="twPc-StatValue">${Followers}</span>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <span id="successMessage"><b>${messages.success}</b></span>
        </div>
    </div>
</div>
</body>
</html>
