<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 처리</title>
<link rel="stylesheet" href="/css/auth.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="auth.header.logout" />
		</h2>
		<form action="/auth/logout" method="post">
			<sec:csrfInput />
			<button>
				<spring:message code="action.logout" />
			</button>
		</form>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
