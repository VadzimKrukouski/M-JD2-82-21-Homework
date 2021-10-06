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
<style>
.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 16px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}
.button1 {
    background-color: white;
    color: black;
    border: 2px solid #f44336;
    border-radius: 12px;
}

.button1:hover {
    background-color: #f44336;
    color: white;
}

.button1:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    border: 1px solid #ddd;
}

th, td {
    text-align: left;
    padding: 16px;
}

tr:nth-child(even) {
    background-color: #f2f2f2
}

input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 2px solid red;
    border-radius: 4px;
}
input[type=number] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 2px solid red;
    border-radius: 4px;
}

select {
    width: 100%;
    padding: 16px 20px;
    border: none;
    border-radius: 4px;
    background-color: #f1f1f1;
}
</style>
</head>
<body>
<h3> Все сотрудники </h3>
<table>
<tr>
   <th>ID</th>
   <th>Имя сотрудника</th>
</tr>
    <tr>
        <tbody>
        <c:forEach items="${requestScope.allEmployers}" var="employers">
            <tr>
                <td>${employers.id}</td>
                <td><a href="getEmployee?id=${employers.id}">${employers.name}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </tr>
</table>



<p><input type="button" class="button button1" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"/>></p>

<form action="search" method="GET">
Имя сотрудника: <input type="text" name="name"/>
<p>
Зарплата: От <input type="number" name="salary1" value="0"/>  До <input type="number" name="salary2" value="0"/>
<p>
<input type="submit" class="button button1" onclick="location.href='search?page=1';" value="Искать"/>
</p>
</form>

</body>
</html>