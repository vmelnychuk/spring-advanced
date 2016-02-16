<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Event list</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>User list</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <table class="table table-hover">
        <tr>
            <th>id</th><th>name</th><th>price</th>
        </tr>
    <c:forEach items="${events}" var="event">
    <tr>
        <td>${event.id}</td><td>${event.name}</td><td>${event.price}</td>
    </tr>
    </c:forEach>
    </table>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
