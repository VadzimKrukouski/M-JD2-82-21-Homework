<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Все должности</title>
</head>
<body>
<h3> Все должности </h3>

<table border="1">
    <tr>
        <tbody>
<c:forEach items="${requestScope.allPositions}" var="position">
                   <tr>
                       <td>${position.id}</td>
                       <td><a href="getPosition?id=${position.id}&page=1">${position.name}</a></td>
                   </tr>
</c:forEach>
        </tbody>
    </tr>
</table>

<c:if test="${requestScope.page != 1}">
   <span><a href="allPositions?page=1">В начало</span>
</c:if>
<c:if test="${page>5}">
   <c:forEach begin="${page-5}" end="${page-1}" var="i">
      <c:if test="${i<=page}">
        <span><a href="allPositions?page=${i}">${i}</span>
      </c:if>
   </c:forEach>
</c:if>

<c:forEach begin="${page}" end="${page+5}" var="i">
    <c:if test="${i<=pageCount}">
        <span><a href="allPositions?page=${i}">${i}</span>
    </c:if>
</c:forEach>

<c:if test="${requestScope.page != pageCount}">
   <span><a href="allPositions?page=${pageCount}">В конец</span>
</c:if>

   <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
</body>
</html>