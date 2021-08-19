<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Главная страница голосования</title>
</head>
<body>
<form action="vote" method="POST">
<label for="artist">Группа</label>
<select name="artist">
<option value="Linkin Park">Linkin Park</option>
<option value="50 Cent">50 Cent</option>
<option value="Eminem">Eminem</option>
<option value="Иванушки">Иванушки</option>
</select><br/>
<label for="genre">Жанр</label><br/>
<input type="checkbox" name="genre" value="Рэп"> Рэп <br/>
<input type="checkbox" name="genre" value="Поп"> Поп <br/>
<input type="checkbox" name="genre" value="Рок"> Рок <br/>
<input type="checkbox" name="genre" value="Электро"> Электро <br/>
<input type="checkbox" name="genre" value="Классика"> Классика <br/>
<input type="checkbox" name="genre" value="Джаз"> Джаз <br/>
<input type="checkbox" name="genre" value="Металл"> Металл <br/>
<label for="about">О себе<label><br/>
<textarea name="about"></textarea>
<input type="submit" name="Отправить">
</form>
</body>
</html>