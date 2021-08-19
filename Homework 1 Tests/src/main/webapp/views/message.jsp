<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Отправка сообщения</title>
</head>
<body>
<h3>Отправьте сообщение</h3>
<span style='color: green;'>${infoOk}</p>
<span style='color: red;'>${infoErr}</p>
<form action="message" method="POST">
<table>
<tbody>
<tr>
<td>Кому</td>
<td><input type="text" name="recipient" value=""</td>
</tr>
<tr>
<td>Текст</td>
<td><input type="text" name="text" value=""</td>
</tr>
</tbody>
</table>
<p><input type="submit" value="Отправить"</p>
<p><input type="button" onclick="location.href='profile';" value="Вернуться в профиль"</p>
</form>
</body>
</html>