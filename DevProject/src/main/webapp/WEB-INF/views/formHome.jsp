<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FORM HOME</h1>
	<form action="/board/register">
		<input type="submit" value="register (GET)">
	</form>
	<form action="/board/register" method="post">
		<input type="submit" value="register (POST)">
	</form>
	<form action="/board/modify">
		<input type="submit" value="modify (GET)">
	</form>

	<form action="/board/modify" method="post">
		<input type="submit" value="modify (POST)">
	</form>
	<form action="/board/remove" method="post">
		<input type="submit" value="remove (POST)">
	</form>

	<form action="/board/list">
		<input type="submit" value="list (GET)">
	</form>
</body>
</html>