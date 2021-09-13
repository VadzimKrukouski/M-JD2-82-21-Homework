<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Все отделы</title>
</head>
<body>
<h3> Все отделы </h3>

<table border="1">
    <tr>
        <tbody>
<c:forEach items="${requestScope.allDepartments}"
               			var="department">
                   <tr>
                       <td>${department.id}</td>
                       <td><a href="getDepartment?id=${department.id}">${department.name}</a></td>
                   </tr>
</c:forEach>
        </tbody>
    </tr>
</table>

   <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>