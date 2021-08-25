<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ваши сообщения</title>
</head>
<body>
<h3> Ваши сообщения </h3>
<span style='color: red;'>${infoErr}</p>

<c:forEach items="${requestScope.messages}"
               			var="message">
                   <tr>
                       <td>${message.from}</td>
                       <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${message.date}" /></td>
                       <td>${message.text}</td>
                   </tr>
</c:forEach>

<p><input type="button" onclick="location.href='profile';" value="Вернуться в профиль"</p>
</body>
</html>