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
    <title>Регистрация</title>
</head>
<body>
<h3>Заполните данные для регистрации на нашем сайте</h3>
<span style='color: red;'>${info}</p>
<form action="signUp" method="POST">
<table>
<tbody>
<tr>
<td>Login:</td>
<td>
<input type="text" name="login">
</td>
</tr>
<tr>
<td>Password:</td>
<td>
<input type="password" name="password">
</td>
</tr>
<tr>
<td>FIO:</td>
<td>
<input type="text" name="fio">
</td>
</tr>
<tr>
<td>Birthday:</td>
<td>
<input type="data" name="birthday">
</td>
</tr>
</tbody>
</table>
<p>
 <input type="submit" value="Зарегистрироваться">
 </p>
</form>
</body>
</html>
