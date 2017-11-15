<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/html5shiv.min.js"></script>
    <script src="${contextPath}/resources/js/respond.min.js"></script>

</head>
<body>

<div class="container">

    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>Welcome '${pageContext.request.userPrincipal.name}' | <a
                    onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>
        </c:if>
    </div>

    <c:if test="${editActive}">
    <div class="page-header"><a href="${contextPath}/admin" title="Admin page">Admin page</a></div>
    </c:if>
    <div class="page-header"><a href="${contextPath}/autos" title="Autos page">Autos page</a></div>
    <div class="page-header"><a href="${contextPath}/journal" title="Journal page">Journal page</a></div>
    <div class="page-header"><a href="${contextPath}/personnel" title="Personnel page">Personnel page</a></div>
    <div class="page-header"><a href="${contextPath}/routes" title="Routes page">Routes page</a></div>

    <div class="page-header"><a href="${contextPath}/report" title="Routes page">Reports</a></div>

</div>

<!-- /container -->
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>