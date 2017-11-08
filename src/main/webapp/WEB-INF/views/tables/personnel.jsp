<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Personnel</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/html5shiv.min.js"></script>
    <script src="${contextPath}/resources/js/respond.min.js"></script>

</head>

<body>
<div class="container">

    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>User Page '${pageContext.request.userPrincipal.name}' | <a
                    onclick="document.forms['logoutForm'].submit()">Logout</a> |
                <a href="<c:url value='/welcome'/>">Home</a>
            </h2>
        </c:if>
    </div>

    <c:url var="addAction" value="/personnel/add"/>

    <form:form action="${addAction}" method="POST" modelAttribute="editForm" class="form-signin">
        <div class="page-header">
            <c:if test="${empty editForm.id}"><h2 class="form-signin-heading">Add</h2></c:if>
            <c:if test="${!empty editForm.id}"><h2 class="form-signin-heading">Edit</h2></c:if>
        </div>
        <c:if test="${!empty editForm.id}">
            <spring:bind path="id">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="id" readonly="true" size="8" class="form-control"/>
                    <form:hidden path="id"/>
                    <form:errors path="id"/>
                </div>
            </spring:bind>
        </c:if>

        <spring:bind path="first_name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="first_name" class="form-control" placeholder="First Name"/>
                <form:errors path="first_name"/>
            </div>
        </spring:bind>

        <spring:bind path="last_name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="last_name" class="form-control" placeholder="Last Name"/>
                <form:errors path="last_name"/>
            </div>
        </spring:bind>

        <spring:bind path="father_name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="father_name" class="form-control" placeholder="Father Name"/>
                <form:errors path="father_name"/>
            </div>
        </spring:bind>

        <c:if test="${empty editForm.id}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
        </c:if>
        <c:if test="${!empty editForm.id}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Edit</button>
            <br/>
            <div><a href="${contextPath}/personnel" title="add">add</a></div>
        </c:if>

    </form:form>

    <div class="page-header"><h1> Personnel </h1></div>
    <div class="panel">
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="70">id</th>
                <th width="120">first_name</th>
                <th width="120">last_name</th>
                <th width="120">father_name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="editForm">
                <tr>
                    <td>${editForm.id}</td>
                    <td>${editForm.first_name}</td>
                    <td>${editForm.last_name}</td>
                    <td>${editForm.father_name}</td>
                    <td><a href="<c:url value='/personnel/edit/${editForm.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/personnel/remove/${editForm.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- /container -->
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>