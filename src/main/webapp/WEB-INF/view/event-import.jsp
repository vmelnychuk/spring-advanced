<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Event Import</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>Import auditorium data</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <form:form commandName="user" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Event file</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" class="btn btn-default">Import</button></td>
            </tr>
        </table>
    </form:form>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
