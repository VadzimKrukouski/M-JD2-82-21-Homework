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
    <title>Данные сотрудника</title>
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
    </style>
</head>

<body>
<h3>Данные сотрудника</h3>
<p><span style='color: red;'/>${info}</p>
<p>Имя: ${employee.name}</p>
<p>Зарплата: ${employee.salary}</p>
<p>Отдел: ${employee.department}</p>
<p>Должность: ${employee.position}</p>

<p><input type="button" class="button button1" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"></p>


</body>
</html>
