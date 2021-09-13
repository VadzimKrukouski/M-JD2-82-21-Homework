<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Данные должности</title>
</head>
<body>
<h3>Информация о должности</h3>
<span style='color: red;'>${info}</p>
<span style='color: black;'>${position}</p>

<table border="1">
    <tr>
        <tbody>


<c:forEach items="${requestScope.employersByPosition}"
               			var="employers">
                   <tr>
                       <td>${employers.id}</td>
                       <td>${employers.name}</td>
                   </tr>
</c:forEach>
        </tbody>
    </tr>
</table>


   <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>