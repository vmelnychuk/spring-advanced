<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User list</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link rel="icon" href="<spring:url value="/assets/img/favicon.ico"/>" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>User list</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <table class="table table-hover">
        <tr>
            <th>id</th><th>email</th><th>name</th><th>password</th>
        </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td><td>${user.email}</td><td>${user.name}</td><td>${user.password}</td>
    </tr>
    </c:forEach>
    </table>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
