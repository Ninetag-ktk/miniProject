<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet" href="resources/css/body.css">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/member.css">
<link rel="stylesheet" href="resources/css/board.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="resources/jsLib/jQuery.js"></script>
<script type="text/javascript" src="resources/jsLib/KooValidChecker.js"></script>
<script type="text/javascript" src="resources/js/go.js"></script>
<script type="text/javascript" src="resources/js/jQ.js"></script>
<script type="text/javascript" src="resources/js/ValidCheck.js"></script>
</head>
<body>
	<table id="headFrame">
		<tr>
			<th colspan="2"><a href="home">헤더</a></th>
		</tr>
		<tr id="menuFrame">
			<td colspan="2">
				<table class="menuBar">
					<tr>
						<td><a href="board.go"><img src="resources/img/board.png"></a></td>
						<td><a href="#"><img src="resources/img/game.png"></a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table id="mainFrame">
		<tr>
			<td rowspan="2" id="contentFrame"><jsp:include
					page="${content }" /></td>
			<td id="loginFrame"><jsp:include page="${login }" /></td>
		</tr>
			<tr>
				<td id="sideBar"><jsp:include page="sidebar/sidebar.jsp" /></td>
			</tr>
	</table>
</body>
</html>