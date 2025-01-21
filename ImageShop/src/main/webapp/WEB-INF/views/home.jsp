<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 처리</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
	<h1>
		환영합니다.<br>
		<spring:message code="common.homeWelcome" />
	</h1>
	<P>The time on the server is ${serverTime}.</P>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
