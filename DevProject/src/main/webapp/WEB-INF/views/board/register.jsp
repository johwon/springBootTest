<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>게시글 등록</h3>
	<form method="post" action="/board/post">
		<button type="submit" name="register">등록</button>
	</form>
	<a href="/board/get?list">목록</a>
</body>
</html>