<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.09.2021
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Все сотрудники</title>
</head>
<body>
<h3> Все сотрудники </h3>
<table border="1">
    <tr>
        <tbody>


        <c:forEach items="${requestScope.allEmployers}" var="employers">
            <tr>
                <td>${employers.id}</td>
                <td>${employers.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </tr>
</table>

<c:if test="${requestScope.page != 1}">
<span><a href="allEmployeeLimit?page=1">В начало</span>
</c:if>

<c:forEach begin="${page-5}" end="${page-1}" var="i">
    <c:if test="${i<=page}">
        <span><a href="allEmployeeLimit?page=${i}">${i}</span>
    </c:if>
</c:forEach>

<c:forEach begin="${page}" end="${page+5}" var="i">
    <c:if test="${i<=pageCount}">
        <span><a href="allEmployeeLimit?page=${i}">${i}</span>
    </c:if>
</c:forEach>

<c:if test="${requestScope.page != pageCount}">
<span><a href="allEmployeeLimit?page=${pageCount}">В конец</span>
</c:if>

<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>