<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Все сотрудники</title>
</head>
<body>
<h3> Все сотрудники </h3>

<c:forEach items="${requestScope.allEmployers}"
               			var="employers">
                   <tr>
                       <td>${employers.id}</td>
                       <td><a href="getEmployee?id=${employers.id}">${employers.name}</a></td>
                   </tr>
</c:forEach>

   <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>