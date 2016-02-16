<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Exception</title>
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="/assets/img/favicon.ico"/>" rel="icon" type="image/x-icon">
</head>
<body>
<div class="container">
    <h1>Exception</h1>
    <a class="btn btn-primary" href="<spring:url value="/"/>">Go Back</a>
    <br/>
    <a class="btn btn-primary" role="button" data-toggle="collapse" href="#stack" aria-expanded="false" aria-controls="stack">
    ${exception}
    </a>
    <div class="collapse" id="stack">
    <div class="well">
      ${stack}
     </div>
     </div>
</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</body>
</html>
