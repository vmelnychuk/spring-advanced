<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link rel="icon" href="<spring:url value="/assets/img/favicon.ico"/>" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>Update user</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <form:form commandName="user">
        <table>
            <tr>
                <td>User name</td>
                <td><form:input path="name" value="${user.name}"/></td>
            </tr>
            <tr>
                <td>User email</td>
                <td><form:input path="email" value="${user.email}"/></td>
            </tr>
            <tr>
                <td>User password</td>
                <td><form:input path="password" value="${user.password}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-default">Update</button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
