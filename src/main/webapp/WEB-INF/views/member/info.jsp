<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/info.jsp</title>
</head>
<body>
<div id="memberTbl">
	<form action="member.update" enctype="multipart/form-data"
		method="post" name="updateForm" onsubmit="return updateCheck();">
		<table>
			<tr>
			<td class="tag">ID</td>
				<td><input name="m_id" readonly="readonly"
					value="${sessionScope.user.m_id }">
					<input name="m_role" value="${sessionScope.user.m_role }" type="hidden">
				</td>
			</tr>
			<tr>
			<td class="tag">PW</td>
				<td><input name="m_pw"
					value="${sessionScope.user.m_pw }" maxlength="24"
					type="password" placeholder="PW"></td>
			</tr>
			<tr>
			<td class="tag">PW Check</td>
				<td><input name="m_pwChk"
					value="" maxlength="24"
					type="password" placeholder="PW Check"></td>
			</tr>
			<tr>
			<td class="tag">이름</td>
				<td><input name="m_name"
					value="${sessionScope.user.m_name }" maxlength="10"
					autocomplete="off" placeholder="NAME"></td>
			</tr>
			<tr>
			<td class="tag">전화번호</td>
				<td>
					<input class="phoneNum num1" name="phone1" type="number" autocomplete="off" placeholder="010" maxlength="3" value="${phone[0] }">
					-
					<input class="phoneNum num2" name="phone2" type="number" autocomplete="off" placeholder="0000" maxlength="4" value="${phone[1] }">
					-
					<input class="phoneNum num3" name="phone3" type="number" autocomplete="off" placeholder="0000" maxlength="4" value="${phone[2] }">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="addressForm">
					<input class="postcode address" name="addr3" readonly="readonly" value="${addr[2] }"><span class="addressTxt">우편번호</span>
					<br>
					<input class="roadAddr address" name="addr1" readonly="readonly" value="${addr[0] }">
					<br>
					<input name="addr2" autocomplete="off" placeholder="상세주소" value="${addr[1] }">
				</td>
			</tr>
			<tr>
				<td colspan="2">사진<br> <img
					src="resources/img/${sessionScope.user.m_pic }">
					<p>
						<input name="m_pic" type="file"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button>정보수정</button>
					<button type="button" onclick="withdrawGo()">회원탈퇴</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>