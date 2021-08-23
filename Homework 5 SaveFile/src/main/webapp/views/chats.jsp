<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>

<html>
<head>
    <title>Ваши сообщения</title>
</head>
<body>
<h3> Ваши сообщения </h3>
<% List<Message> message = (List<Message>) request.getAttribute("messages");
for (Message message : messages) {
            out.println(message);
        }
%>

<p><input type="button" onclick="location.href='profile';" value="Вернуться в профиль"</p>
</body>
</html>