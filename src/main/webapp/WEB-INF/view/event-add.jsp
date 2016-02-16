<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>Register user</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <form:form commandName="event">
        <table>
            <tr>
                <td>Event name</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Event price</td>
                <td><form:input path="price"/></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" class="btn btn-default">Add</button></td>
            </tr>
        </table>
    </form:form>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
