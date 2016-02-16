<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Auditorium</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <h1>Add auditorium</h1>
    <a class="btn btn-primary" href="/">Go Back</a>
    <form:form commandName="auditorium">
        <form:errors path="*" cssClass="text-danger" element="div"/>
        <table>
            <tr>
                <td>Auditorium name</td>
                <td><form:input path="name"/></td>
                <td><form:errors path="name" cssClass="text-danger"/></td>
            </tr>
            <tr>
                <td>Number of seats</td>
                <td><form:input path="seats"/></td>
                <td><form:errors path="seats" cssClass="text-danger"/></td>
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
