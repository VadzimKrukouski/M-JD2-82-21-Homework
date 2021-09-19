<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Главная страница сайта</title>
</head>
<body>
<h2> Приветствуем на нашем сайте! Выберите действие: </h2>

<p><input type="button" onclick="location.href='generationDB';" value="Сгенерировать базу данных"</p>
<p><input type="button" onclick="location.href='addEmployeeMapper';" value="Внести данные сотрудника"</p>
<p><input type="button" onclick="location.href='getEmployee';" value="Получить данные сотрудника"</p>
<p><input type="button" onclick="location.href='addDepartmentMapper';" value="Внести в базу новый отдел"</p>
<p><input type="button" onclick="location.href='addPositionMapper';" value="Внести в базу новую должность"</p>
<p><input type="button" onclick="location.href='allPositions';" value="Посмотреть все должности"</p>
<p><input type="button" onclick="location.href='allDepartments';" value="Посмотреть все отделы"</p>
<p><input type="button" onclick="location.href='allEmployeeLimit?page=1';" value="Посмотреть всех сотрудников"</p>


</body>
</html>