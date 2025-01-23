<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&family=Kablammo&family=Nanum+Gothic&family=Oxanium:wght@200..800&display=swap');
</style>
<link rel="stylesheet" href="/css/header.css" />
<div class="header" align="right">
	<table>
		<tr>
			<!-- 로그인을 하지 않은 경우 -->
			<sec:authorize access="!isAuthenticated()">
				<!-- 로그인을 메뉴에 추가한다. -->
				<td >
					<a href="/auth/login"><spring:message code="header.login" /></a>
				</td>
				<td >
					<a href="/user/register"><spring:message code="header.joinMember" /></a>
				</td>
			</sec:authorize>
			
			<!-- 로그인을 거친 인증된 사용자인 경우-->
			<sec:authorize access="isAuthenticated()">
				<td>
					<sec:authentication property="principal.username" /> 님 
					<a href="/auth/logout"><spring:message	code="header.logout" /></a>
				</td>
			</sec:authorize>
		</tr>
	</table>
</div>
<hr>