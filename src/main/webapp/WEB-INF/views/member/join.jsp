<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/join.jsp</title>
</head>
<body>
<div id="memberTbl">
	<h1>회원가입</h1>
	<form action="member.join" method="post" enctype="multipart/form-data" name="joinForm" onsubmit="return joinCheck();">
		<table>
			<tr>
				<td class="tag">ID</td>
				<td><input id="id_Input" name="m_id" autocomplete="off" placeholder="ID"
					autofocus="autofocus" maxlength="10"></td>
			</tr>
			<tr>
				<td class="tag">PW</td>
				<td><input name="m_pw" autocomplete="off" placeholder="PW"
					maxlength="24" type="password"></td>
			</tr>
			<tr>
				<td class="tag">PW Check</td>
				<td><input name="m_pwChk" autocomplete="off"
					placeholder="PW Check" maxlength="24" type="password"></td>
			</tr>
			<tr>
				<td class="tag">이름</td>
				<td><input name="m_name" autocomplete="off" placeholder="NAME"
					maxlength="10"></td>
			</tr>
			<tr>
				<td class="tag">전화번호</td>
				<td>
					<input class="phoneNum num1" name="phone1" type="number" autocomplete="off" placeholder="010" maxlength="3">
					-
					<input class="phoneNum num2" name="phone2" type="number" autocomplete="off" placeholder="0000" maxlength="4">
					-
					<input class="phoneNum num3" name="phone3" type="number" autocomplete="off" placeholder="0000" maxlength="4">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="addressForm">
					<input class="postcode address" name="addr3" readonly="readonly"><span class="addressTxt">우편번호</span>
					<br>
					<input class="roadAddr address" name="addr1" readonly="readonly">
					<br>
					<input class="detailAddr" name="addr2" autocomplete="off" placeholder="상세주소">
				</td>
			</tr>
			<tr>
				<td class="tag">사진</td>
				<td><input name="m_pic" type="file"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="roleJoin normalUser" name="m_role" type="radio" value="" checked="checked">일반 사용자
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="roleJoin adminUser" name="m_role" type="radio" value="1">관리자
					<input id="adminCodeInput" name="adminCode" placeholder="관리자 코드를 입력해주세요" style="display: none">
				</td>
			</tr>
			<tr>
				<td colspan="2"><button>가입</button></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>