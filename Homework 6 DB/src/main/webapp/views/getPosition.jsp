<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Данные должности</title>
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
</style>
</head>
<body>
<h3>Информация о должности</h3>
<p><span style='color: red;'/>${info}</p>
<p><span style='color: black;'/>${position.name}</p>

<table>
    <tr>
       <th>ID</th>
       <th>Имя сотрудника</th>
    </tr>
    <tr>
        <tbody>


<c:forEach items="${requestScope.employersByPosition}" var="employers">
                   <tr>
                       <td>${employers.id}</td>
                       <td>${employers.name}</td>
                   </tr>
</c:forEach>
        </tbody>
    </tr>
</table>

<c:if test="${requestScope.page != 1}">
   <span><a href="position?id=${position.id}&page=1">В начало</a></span>
</c:if>
<c:if test="${page>5}">
   <c:forEach begin="${page-5}" end="${page-1}" var="i">
      <c:if test="${i<=page}">
        <span><a href="position?id=${position.id}&page=${i}">${i}</a></span>
      </c:if>
   </c:forEach>
</c:if>

<c:forEach begin="${page}" end="${page+5}" var="i">
    <c:if test="${i<=pageCount}">
        <span><a href="position?id=${position.id}&page=${i}">${i}</a></span>
    </c:if>
</c:forEach>

<c:if test="${requestScope.page != pageCount}">
   <span><a href="position?id=${position.id}&page=${pageCount}">В конец</a></span>
</c:if>

<p><input type="button" class="button button1" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"></p>

</body>
</html>