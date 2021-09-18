<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h3>Добавьте должность/h3>
  <span style='color: green;'>${info}${id}</p>
  <span style='color: red;'>${infoErr}</p>

  <form id="2" action="addPositionMapper" method="POST">
		<p>Наименование должности: <input type="text" name="name">

		<p><input type="submit" value="Отправить данные">
		<p><input type="button" onclick="location.href='${pageContext.request.contextPath}/';" value="Вернуться на главную страницу"</p>
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