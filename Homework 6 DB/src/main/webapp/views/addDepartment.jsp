<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.08.2021
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавление отдела</title>
</head>
<body>
<h3>Добавьте отдел</h3>
<span style='color: green;'>${info}${id}</p>
<span style='color: red;'>${infoErr}</p>
<form action="addDepartment" method="POST">
<table>
<tbody>

<tr>
<td>Наименование отдела</td>
<td>
<input type="text" name="name">
</td>
</tr>
<tr>
<td>Наименование родительского отдела</td>
<td>
<input type="text" name="name">
</td>
</tr>

</tbody>
</table>
<p>
 <input type="submit" value="Отправить данные">
 </p>
  <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</form>
</body>
</html>