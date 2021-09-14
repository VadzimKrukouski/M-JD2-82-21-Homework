<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Данные сотрудника</title>
</head>
<body>
<h3>Введите id сотрудника:</h3>
<span style='color: red;'>${info}</p>
<span style='color: black;'>${employee}</p>

<form action="getEmployeeMapper" method="POST">
<table>
<tbody>

<tr>
<td>id:</td>
<td>
<input type="text" name="id">
</td>
</tr>


</tbody>
</table>
<p>
 <input type="submit" value="Получить сотрудника">
 </p>
   <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</form>
</body>
</html>