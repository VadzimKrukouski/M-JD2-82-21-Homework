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


        <c:forEach items="${requestScope.allEmployers}"
                   var="employers">
            <tr>
                <td>${employers.id}</td>
                <td>${employers.name}</td>
            </tr>
        </c:forEach>

        <span><a href="allEmployeeLimit?page=1">1 </span>
        <span><a href="allEmployeeLimit?page=2">2 </span>
        <span><a href="allEmployeeLimit?page=3">3 </span>
        <span><a href="allEmployeeLimit?page=${pageCount}">${pageCount} </span>



        </tbody>
    </tr>
</table>

<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>