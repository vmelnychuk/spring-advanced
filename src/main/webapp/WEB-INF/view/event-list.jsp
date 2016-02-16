<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User list</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <h1>User list</h1>
    <a class="btn btn-primary" href="/">Go Back</a>
    <table class="table">
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
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.js"></script>
</body>
</html>
