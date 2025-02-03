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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/coin.css" />
<script src=""></script>
<title>Image Shop</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />
	<main align="center">
		<h2>
			<spring:message code="coin.header.chargeCoin" />
		</h2>

		<form:form modelAttribute="chargeCoin" action="charge">
			<table class="coin_table">
				<tr>
					<td><spring:message code="coin.amount" /></td>
					<td><form:input path="amount" /></td>
					<td class="hidden"><font color="red"><form:errors path="amount" /></font></td>
				</tr>
			</table>
		</form:form>

		<div>
			<button type="button" id="btnCharge">
				<spring:message code="action.charge" />
			</button>
			<button type="button" id="btnList">
				<spring:message code="action.list" />
			</button>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
		$(document).ready(function() {
			var formObj = $("#chargeCoin");

			$("#btnCharge").on("click", function() {
				var amount = $("input[name='amount']").val();  // amount 값을 가져옴
				if (amount) {
					// amount 값을 쿼리 파라미터로 추가해서 checkout 페이지로 리디렉션
					window.location.href = "checkout?amount=" + amount;
				} else {
					alert("금액을 입력해 주세요.");
				}
			});
			
			$("#btnList").on("click", function() {
				self.location = "list";
			});
		});
	</script>
</body>
</html>
