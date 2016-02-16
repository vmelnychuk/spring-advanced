<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auditorium</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
</head>
<body>
<div class="container">
<h1>Auditoriums</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <table class="table table-hover">
        <tr>
            <th>id</th><th>name</th><th>seats</th>
        </tr>
        <c:forEach items="${auditoriums}" var="auditorium">
            <tr>
                <td>${auditorium.id}</td><td>${auditorium.name}</td><td>${auditorium.seats}</td>
            </tr>
        </c:forEach>
    </table>

</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
