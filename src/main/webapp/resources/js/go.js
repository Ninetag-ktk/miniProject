// js/go.js

function changePage(pageNum) {
	var url = new URLSearchParams(window.location.search);
	var p = url.get("p");
	if (p == null) {
		location.href = "board.page?pageNum=" + pageNum;
	} else {
		location.href = "board.page?p=" + p + "&pageNum=" + pageNum;
	}
}

function deleteComment(c_num) {
	if (confirm("정말 삭제하시겠습니까?")) {		
		location.href = "comment.delete?c_num=" + c_num;
	}
}

function deletePost(p_num) {
	if (confirm("정말 삭제하시겠습니까?")) {
		location.href = "post.delete?p_num=" + p_num;
	}
}

function infoGo() {
	location.href = "member.info";
}

function logoutGo() {
	if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "member.logout";
	}
}

function signupGo() {
	location.href = "member.signup";
}

function withdrawGo() {
	var pwInput = document.updateForm.m_pw;
	var pwChkInput = document.updateForm.m_pwChk;
	
	if (isEmpty(pwChkInput)) {
		alert("탈퇴하시려면 PW Check를 입력해주세요")
		return;
	} else if (notEqualPw(pwInput, pwChkInput)) {
		alert("PW가 다릅니다");
		pwChkInput.value = "";
		pwChkInput.focus();
		return;
	}
	
	if (!confirm("정말 탈퇴하시겠습니까?")) {
		return;
	}
	location.href = "member.withdraw";
}