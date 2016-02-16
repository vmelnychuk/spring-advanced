<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auditorium</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
<h1>Auditoriums</h1>
    <a class="btn btn-primary" href="/">Go Back</a>
    <table class="table">
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
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.js"></script>
</body>
</html>
