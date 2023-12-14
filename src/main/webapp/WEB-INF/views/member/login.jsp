<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/login.jsp</title>
</head>
<body>
	<form action="member.login" method="post" name="loginForm"
		onsubmit="return loginCheck();">
		<table>
			<tr>
				<td><input name="m_id" placeholder="ID"
					value="${cookie.lastLoginId.value }" autocomplete="off"></td>
			</tr>
			<tr>
				<td><input name="m_pw" placeholder="PW" type="password" autocomplete="off" maxlength="24"></td>
			</tr>
			<tr>
				<td>
					<button>로그인</button>
					<button type="button" onclick="signupGo();">회원가입</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>