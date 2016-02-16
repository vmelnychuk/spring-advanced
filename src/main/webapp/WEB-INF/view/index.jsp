<%@ page language="java" contentType="text/html;UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
    <title>Cinema</title>
</head>
<body>
<div class="container">
    <h1>Cinema Main Page</h1>
    <h2>Auditoriums</h2>
    <ul>
        <li><a href="<spring:url value="/auditorium/list"/>">list of auditoriums</a></li>
        <li><a href="<spring:url value="/auditorium/add"/>">add auditorium</a></li>
        <li><a href="<spring:url value="/auditorium/export"/>" target="_blank">export to JSON</a></li>
        <li><a href="<spring:url value="/auditorium/import"/>">import from JSON</a></li>
    </ul>

    <h2>Users</h2>
    <ul>
        <li><a href="<spring:url value="/user/list"/>">list of users</a></li>
        <li><a href="<spring:url value="/user/add"/>">add user</a></li>
        <li><a href="<spring:url value="/user/export"/>" target="_blank">export to JSON</a></li>
        <li><a href="<spring:url value="/user/import"/>">import from JSON</a></li>
    </ul>

    <h2>Events</h2>
    <ul>
        <li><a href="<spring:url value="/event/list"/>">list of events</a></li>
        <li><a href="<spring:url value="/event/add"/>">add event</a></li>
        <li><a href="<spring:url value="/event/export"/>" target="_blank">export to JSON</a></li>
        <li><a href="<spring:url value="/event/import"/>">import from JSON</a></li>
    </ul>

</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>