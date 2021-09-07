<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.08.2021
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h3>Заполните данные</h3>
<span style='color: green;'>${info}${id}</p>
<span style='color: red;'>${infoErr}</p>
<form action="addEmployee" method="POST">

Имя сотрудника: <input type="text" name="name">
<p>
Зарплата: <input type="number" name="salary" value="0">
<p>
     <br>
       <select size="5" name="position">
               <option disabled>Choose department</option>
                      <c:forEach items="${allPositions}" var="position">
                          <option value="${position.id}">${position.name}</option>
                      </c:forEach>
                  </select>


        <select size="5" name="department">
        <option disabled>Choose department</option>
               <c:forEach items="${allDepartments}" var="department">
                   <option value="${department.id}">${department.name}</option>
               </c:forEach>
           </select>
    <br>



<p>
 <input type="submit" value="Отправить данные">
 </p>
  <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</form>
</body>
</html>
