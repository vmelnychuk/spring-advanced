<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Auditorium Import</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <h1>Import auditorium data</h1>
    <a class="btn btn-primary" href="/">Go Back</a>
    <form:form commandName="user" enctype="multipart/form-data">
        <table>
            <tr>
                <td>User file</td>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" class="btn btn-default">Import</button></td>
            </tr>
        </table>
    </form:form>
</div>
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.js"></script>
</body>
</html>
