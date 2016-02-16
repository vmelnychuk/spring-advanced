<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html;UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%--<link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">--%>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="/assets/img/favicon.ico" type="image/x-icon">
    <title>Cinema</title>
</head>
<body>
<div class="container">
    <h1>Cinema Main Page</h1>
    <h2>Auditoriums</h2>
    <ul>
        <li><a href="/auditorium/list">list of auditoriums</a></li>
        <li><a href="/auditorium/add">add auditorium</a></li>
        <li><a href="/auditorium/export" target="_blank">export to JSON</a></li>
        <li><a href="/auditorium/import">import from JSON</a></li>
    </ul>

    <h2>Users</h2>
    <ul>
        <li><a href="/user/list">list of users</a></li>
        <li><a href="/user/add">add user</a></li>
        <li><a href="/user/export" target="_blank">export to JSON</a></li>
        <li><a href="/user/import">import from JSON</a></li>
    </ul>

    <h2>Events</h2>
    <ul>
        <li><a href="/event/list">list of events</a></li>
        <li><a href="/event/add">add event</a></li>
        <li><a href="/event/export" target="_blank">export to JSON</a></li>
        <li><a href="/event/import">import from JSON</a></li>
    </ul>

</div>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.js"></script>
</body>
</html>