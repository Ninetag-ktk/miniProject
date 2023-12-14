<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/user.jsp</title>
</head>
<body>
	<table id="userInfo">
		<tr>
			<td class="imgFrame"><img
				src="resources/img/${sessionScope.user.m_pic }"></td>
		</tr>
		<tr>
			<td><c:if test="${sessionScope.user.m_role == 1}">
					<div class="adminChk">관리자</div>
				</c:if> ${sessionScope.user.m_name}</td>
		</tr>
		<tr>
			<td>
				<button onclick="infoGo();">내정보</button>
				<button onclick="logoutGo();">로그아웃</button>
			</td>
		</tr>
	</table>
</body>
</html>