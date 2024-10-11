<%@page import="logic.Model"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Главная страница</title>
<script>
    function sendData(event) {
        event.preventDefault(); 

        const id = document.querySelector('input[name="id"]').value;



        fetch('/labServletP1/get', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ id: id })
            })
        .then(response => response.text())
        .then(data => {
            document.getElementById('startPage').style.display = 'none';
            document.getElementById('responseMessage').innerHTML = data;
        })
        .catch(error => console.error('Error:', error));
    }
</script>
</head>
<body>
	<div id="startPage">
		<h1>Подробнее о пользователе</h1>
		Введи id пользователя(0 для всех) <br/> Доступно:
		<%
		Model model = Model.getInstance();
		out.print(model.getFromList().size());
		%>
		<form id="infoForm" onsubmit="sendData(event)">
			<label>ID: <input type="text" name="id"><br />
			</label>
			<button type="submit">Поиск</button>
		</form>
		<a href="addUser.html">Создать нового пользователя</a><br/>
		<a href="delUser.html">Удалить пользователя</a><br/>
		<a href="changeUser.html">Изменить пользователя</a>
	</div>

	<div id="responseMessage"></div>

</body>
</html>