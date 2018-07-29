<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update a Movie</title>
</head>
<body>
<h1>Update User</h1>
<form action="updatecreditcard" method="post">
    <p>
        <label for="creditcardnumber">Creditcardnumber</label>
        <input id="creditcardnumber" name="creditcardnumber" value="${fn:escapeXml(param.creditcardnumber)}">
    </p>
    <p>
        <label for="expiration">New Overview</label>
        <input id="expiration" name="expiration" value="">
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