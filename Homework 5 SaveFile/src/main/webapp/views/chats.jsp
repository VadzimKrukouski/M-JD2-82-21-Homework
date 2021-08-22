<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>

<html>
<head>
    <title>Ваши сообщения</title>
</head>
<body>
<h3> Ваши сообщения </h3>
<% List<String> message = (List<String>) request.getAttribute("messages");
for (String m : message) {
            out.println(m);
        }
%>

<p><input type="button" onclick="location.href='profile';" value="Вернуться в профиль"</p>
</body>
</html>