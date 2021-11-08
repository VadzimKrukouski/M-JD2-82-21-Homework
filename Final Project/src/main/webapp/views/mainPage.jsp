<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Главная страница сайта</title>

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
<h2> Приветствуем на нашем сайте! Выберите действие: </h2>

<p><input type="button" class="button button1" onclick="location.href='generationDB';" value="Сгенерировать базу данных"></p>
<p><input type="button" class="button button1" onclick="location.href='employee';" value="Внести данные сотрудника"></p>
<p><input type="button" class="button button1" onclick="location.href='employee/0';" value="Получить данные сотрудника"></p>
<p><input type="button" class="button button1" onclick="location.href='department';" value="Внести в базу новый отдел"></p>
<p><input type="button" class="button button1" onclick="location.href='position';" value="Внести в базу новую должность"></p>
<p><input type="button" class="button button1" onclick="location.href='position/all?page=1';" value="Посмотреть все должности"></p>
<p><input type="button" class="button button1" onclick="location.href='department/all?page=1';" value="Посмотреть все отделы"></p>
<p><input type="button" class="button button1" onclick="location.href='employee/all?page=1';" value="Посмотреть всех сотрудников"></p>


</body>
</html>