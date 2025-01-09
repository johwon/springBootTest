<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
	<form action="/member/registerFileUp01" method="post"
		enctype="multipart/form-data">
		1. <input type="file" name="picture" multiple><br>
		<input type="submit">
	</form>
</body>
</html>