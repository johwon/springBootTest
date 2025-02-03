<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://js.tosspayments.com/v2/standard"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/coin.css" />
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		 <h2> 결제 실패 </h2>
    <p id="code"></p>
    <p id="message"></p>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
  const urlParams = new URLSearchParams(window.location.search);

  const codeElement = document.getElementById("code");
  const messageElement = document.getElementById("message");

  codeElement.textContent = "에러코드: " + urlParams.get("code");
  messageElement.textContent = "실패 사유: " + urlParams.get("message");
</script>
</body>
</html>
