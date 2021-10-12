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
<title>Добавление нового сотрудника</title>
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

input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 2px solid red;
    border-radius: 4px;
}
input[type=number] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: 2px solid red;
    border-radius: 4px;
}

select {
    width: 100%;
    padding: 16px 20px;
    border: none;
    border-radius: 4px;
    background-color: #f1f1f1;
}
</style>
</head>
<body>
<h3>Заполните данные сотрудника</h3>
  <span style='color: green;'>${info}${id}</p>
  <span style='color: red;'>${infoErr}</p>

  <form id="2" action="employee" method="POST">
		<p>Имя сотрудника:</p>
		<input type="text" name="name">
		<p>Зарплата: </p>
		<input type="number" name="salary">
		<p>
		  <br>
              <select name="position.id">
                 <option disabled>Выберите должность</option>
                         <c:forEach items="${allPositions}" var="position">
                            <option value="${position.id}">${position.name}</option>
                         </c:forEach>
              </select>


              <select name="department.id">
                   <option disabled>Выберите отдел</option>
                          <c:forEach items="${allDepartments}" var="department">
                              <option value="${department.id}">${department.name}</option>
                          </c:forEach>
              </select>
               <br>

		<p><input type="submit" class="button button1" value="Отправить данные">
		<p><input type="button" class="button button1" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
  </form>
		<script type="text/javascript">
			document.getElementById('2').addEventListener('submit', submitForm);

			function submitForm(event) {
			    // Отменяем стандартное поведение браузера с отправкой формы
			    event.preventDefault();

			    // event.target — это HTML-элемент form
			    let formData = new FormData(event.target);

			    // Собираем данные формы в объект
			    let obj = {};
			    formData.forEach(
			    	(value, key) => {
						if(key.includes('.')){
							var partName = key.split(".");
			    			obj[partName[0]] = {};
			    			obj[partName[0]][partName[1]] = value;
			    		} else {
							obj[key] = value;
			    		}

			    	}
			    );

			    // Собираем запрос к серверу
			    let request = new Request(event.target.action, {
			        method: 'POST',
			        body: JSON.stringify(obj),
			        headers: {
			            'Content-Type': 'application/json',
			        },
			    });

			    // Отправляем (асинхронно!)
			    fetch(request).then(
			        function(response) {
			            // Запрос успешно выполнен
			            console.log(response);
			            // return response.json() и так далее см. документацию
			        },
			        function(error) {
			            // Запрос не получилось отправить
			            console.error(error);
			        }
			    );

			    // Код после fetch выполнится ПЕРЕД получением ответа
			    // на запрос, потому что запрос выполняется асинхронно,
			    // отдельно от основного кода
			    console.log('Запрос отправляется');
			}
		</script>
	</body>
</html>