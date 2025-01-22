<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/user.css" />
<script src=""></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<div> 
<h2><spring:message code="common.cannotSetupAdmin" /></h2> 
<a href="/"><spring:message code="action.home" /></a> 
</div> 
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
