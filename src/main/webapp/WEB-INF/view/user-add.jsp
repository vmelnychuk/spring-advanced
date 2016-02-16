<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>User</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <h1>Register user</h1>
    <a class="btn btn-primary" href="/">Go Back</a>
    <form:form commandName="user">
        <table>
            <tr>
                <td>User name</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>User email</td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td>User password</td>
                <td><form:input path="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" class="btn btn-default">Add</button></td>
            </tr>
        </table>
    </form:form>
</div>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.js"></script>
</body>
</html>
