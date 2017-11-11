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

    <title>Journal</title>

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

    <c:url var="addAction" value="/journal/add"/>

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

        <spring:bind path="time_out_str">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="datetime-local" step="1" path="time_out_str" class="form-control" placeholder="Time out"/>
                <form:errors path="time_out"/>
            </div>
        </spring:bind>

        <spring:bind path="time_in_str">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="datetime-local" step="1" path="time_in_str" class="form-control" placeholder="Time in"/>
                <form:errors path="time_in"/>
            </div>
        </spring:bind>

        <spring:bind path="auto_str">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="auto_str" cssClass="form-control">
                    <c:forEach var='autoList' items='${autoList}' >
                        <form:option value="${autoList}" />
                    </c:forEach>
                </form:select>
                <form:errors path="auto_str"/>
            </div>
        </spring:bind>

        <spring:bind path="route_str">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="route_str" cssClass="form-control">
                    <c:forEach var='routeList' items='${routeList}' >
                        <form:option value="${routeList}" />
                    </c:forEach>
                </form:select>
                <form:errors path="route_str"/>
            </div>
        </spring:bind>

        <c:if test="${empty editForm.id}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
        </c:if>
        <c:if test="${!empty editForm.id}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Edit</button>
            <br/>
            <div><a href="${contextPath}/journal" title="add">add</a></div>
        </c:if>

    </form:form>

    <div class="page-header"><h1> Journal </h1></div>
    <div class="panel">
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="70">id</th>
                <th width="120">time_out</th>
                <th width="120">time_in</th>
                <th width="120">auto_id</th>
                <th width="120">route_id</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemList}" var="editForm">
                <tr>
                    <td>${editForm.id}</td>
                    <td>${editForm.time_out}</td>
                    <td>${editForm.time_in}</td>
                    <td>[${editForm.auto_id.id}] ${editForm.auto_id.num}</td>
                    <td>[${editForm.route_id.id}] ${editForm.route_id.name}</td>
                    <td><a href="<c:url value='/journal/edit/${editForm.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/journal/remove/${editForm.id}'/>">Delete</a></td>
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