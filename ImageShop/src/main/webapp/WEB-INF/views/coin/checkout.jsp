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
		<!-- 결제 UI -->
		<div id="payment-method"></div>
		<!-- 이용약관 UI -->
		<div id="agreement"></div>
		<!-- 결제하기 버튼 -->
		<button class="button" id="payment-button" style="margin-top: 30px">결제하기</button>
		<script>
      main();

      async function main() {
        const button = document.getElementById("payment-button");
     // 버튼이 정상적으로 로드되었는지 확인
        if (!button) {
          console.error("payment-button 요소를 찾을 수 없습니다.");
          return;
        }
        // ------  결제위젯 초기화 ------
        const clientKey = "test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm";
        const tossPayments = TossPayments(clientKey);
        // 회원 결제
        const customerKey = "G-Gt0xCvJVepMYVHNbZux";
        const widgets = tossPayments.widgets({
          customerKey,
        });
        // 비회원 결제
        // const widgets = tossPayments.widgets({ customerKey: TossPayments.ANONYMOUS });

        // ------ 주문의 결제 금액 설정 ------
        await widgets.setAmount({
          currency: "KRW",
          value: parseInt("${amount}"),
        });

        await Promise.all([
          // ------  결제 UI 렌더링 ------
          widgets.renderPaymentMethods({
            selector: "#payment-method",
            variantKey: "DEFAULT",
          }),
          // ------  이용약관 UI 렌더링 ------
          widgets.renderAgreement({ selector: "#agreement", variantKey: "AGREEMENT" }),
        ]);

        // ------  주문서의 결제 금액이 변경되었을 경우 결제 금액 업데이트 ------
       /*  coupon.addEventListener("change", async function () {
          if (coupon.checked) {
            await widgets.setAmount({
              currency: "KRW",
              value: 50000 - 5000,
            });

            return;
          }

          await widgets.setAmount({
            currency: "KRW",
            value: 50000,
          });
        }); */

        // ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
        button.addEventListener("click", async function () {
          await widgets.requestPayment({
            orderId: "lXQylekwmDEh0-o9ZRXhm",
            orderName: "토스 티셔츠 외 2건",
            successUrl: window.location.origin + "/coin/success",
            failUrl: window.location.origin + "/coin/fail",
            customerEmail: "customer123@gmail.com",
            customerName: "김토스",
            customerMobilePhone: "01012234123",
          });
        });
      }
    </script>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
</body>
</html>
