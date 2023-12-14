<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/board.jsp</title>
</head>
<body>
	<table id="boardTbl">
		<tr>
			<td class="perPageFrame" colspan="3">
			<select id="perPage" name="p">
					<option value="1">1개씩 표시</option>
					<option value="3">3개씩 표시</option>
					<option value="5">5개씩 표시</option>
			</select></td>
		</tr>
		<tr id="postContainer">
			<td class="arrow"><c:if test="${pageNum != 1 }">
					<span onclick="changePage(${pageNum - 1 });">&lt;</span>
				</c:if></td>
			<td class="postFrame"><c:if test="${sessionScope.user != null }">
					<form action="post.write" name="postForm" method="post"
						onsubmit="return postCheck();">
						<input name="token" value="${token }" type="hidden">
						<table id="boardFrame"
							style="border: white solid 2px; color: white;">
							<tr>
								<td class="postImg" rowspan="2"><img
									src="resources/img/${sessionScope.user.m_pic }"></td>
								<td class="postBody" style="padding: auto;"><textarea
										id="writeTextarea" placeholder="게시글" rows="2" name="p_txt" maxlength="300" style="display: inline-table;"></textarea></td>
								<td id="postBtnFrame">
									<button id="postBtn">
										<img src="resources/img/edit.png">
									</button>
								</td>
							</tr>
						</table>
					</form>
				</c:if> <c:forEach var="post" items="${posts }">
					<table id="boardFrame"
						style="border: ${post.p_color} solid 2px; color: ${post.p_color};">
						<tr>
							<td class="postImg" rowspan="3"><img
								src="resources/img/${post.m_pic }"></td>
							<td class="postBody"><span class="writer">${post.m_name}(${post.p_writer_id})</span>
							<c:choose>
							<c:when test="${sessionScope.user.m_id == post.p_writer_id}">
									<span class="btnFrame"> 
										<a class="boardBtn editBtn">
											<img src="resources/img/edit.png">
										</a> 
										<a class="boardBtn cancleBtn"
										href="javascript:deletePost(${post.p_num });"> 
											<img src="resources/img/cancel.png">
										</a>
									</span>
							</c:when>
							<c:when test="${sessionScope.user.m_role == 1}">
									<span class="btnFrame"> 
										<a class="boardBtn cancleBtn"
										href="javascript:deletePost(${post.p_num });"> 
											<img src="resources/img/cancel.png">
										</a>
									</span>
							</c:when>
							
							</c:choose>
								</td>
						</tr>
						<tr>
							<td>
								<div class="postTxt">
									<form action="post.edit" id="editForm" method="post" onsubmit="return editPostCheck();">
									<input name="p_num" value="${post.p_num}" type="hidden">
									<input id="initialValue" value="${post.p_txt}" type="hidden">
									<textarea name="p_txt" id="postTextarea" rows="1" style="color: ${post.p_color}; border-color: ${post.p_color}, outline-color: ${post.p_color};" 
									disabled="disabled" maxlength="300">${post.p_txt}</textarea>
									</form>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="whenDate">
									<fmt:formatDate value="${post.p_when}" type="both"
										dateStyle="long" timeStyle="short" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table>
									<tr>
										<td colspan="3">
											<hr color="${post.p_color }">
										</td>
									</tr>
									<c:forEach var="comm" items="${post.p_comments}">
										<tr>
											<td style="height: 30px;" class="postBody">
												<div class="commentFrame">
													<span class="writer">${comm.m_name}(${comm.c_writer_id}): </span>
													<span class="commTxt"> <textarea id="commTextarea" disabled="disabled"
															rows="1" style="color: ${post.p_color};">${comm.c_txt}</textarea>
													</span>
													<c:if test="${sessionScope.user.m_id == comm.c_writer_id || sessionScope.user.m_role == 1}">
														<span class="btnFrame"> 
														<a class="boardBtn cancleBtn"
															href="javascript:deleteComment(${comm.c_num });"> <img
																src="resources/img/cancel.png">
														</a>
														</span>
													</c:if>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="whenDate commDate">
													(
													<fmt:formatDate value="${comm.c_when }" type="both"
														dateStyle="short" timeStyle="short" />
													)
												</div>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${sessionScope.user != null }">
										<tr>
											<td style="height: 30px;" class="postBody">
												<form action="comment.write" name="commForm" method="post"
													onsubmit="return commCheck();">
													<input name="token" value="${token }" type="hidden">
													<input name="c_p_num" value="${post.p_num }" type="hidden">
													<div class="commentFrame">
														<span class="writer">${sessionScope.user.m_name } :</span>
														<span class="commTxt"> <textarea id="commTextarea"
																name="c_txt" placeholder="댓글" rows="1"
																style="color: ${post.p_color}; border: ${post.p_color} solid 1px;" maxlength="200"></textarea>
														</span> <span>
															<button id="commBtn">
																<img src="resources/img/edit.png">
															</button>
														</span>
													</div>
												</form>
											</td>
										</tr>
									</c:if>
								</table>
							</td>
						</tr>
					</table>
				</c:forEach></td>
			<td class="arrow"><c:if test="${pageNum != allPageCount }">
					<span onclick="changePage(${pageNum + 1 });">&gt;</span>
				</c:if></td>
		</tr>
	</table>
	<div id="searchFrame">
		<form action="post.search" name="searchForm"
			onsubmit="return searchCheck();" method="post">
			<table>
				<tr>
					<td class="searchBar">
					<input id="searchInput" name="search" autocomplete="off" placeholder="검색어 입력">
					</td>
					<td class="searchTag">
						<button>
							<img src="resources/img/search.png">
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>