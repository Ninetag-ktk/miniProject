// js/ValidCheck.js

function loginCheck() {
	var idInput = document.loginForm.m_id;
	var pwInput = document.loginForm.m_pw;

	if (isEmpty(idInput)) {
		return false;
	} else if (isEmpty(pwInput)) {
		alert('비밀번호를 입력해주세요');
		return false;
	}
	return true;
}

function joinCheck() {
	var idInput = document.joinForm.m_id;
	var pwInput = document.joinForm.m_pw;
	var pwChkInput = document.joinForm.m_pwChk;
	var nameInput = document.joinForm.m_name;

	var phone1Input = document.joinForm.phone1;
	var phone2Input = document.joinForm.phone2;
	var phone3Input = document.joinForm.phone3;

	var addr3Input = document.joinForm.addr3;
	var addr1Input = document.joinForm.addr1;
	var addr2Input = document.joinForm.addr2;

	var picInput = document.joinForm.m_pic;

	var roleInput = document.joinForm.m_role;

	if (isEmpty(idInput)) {
		alert("ID를 입력해주세요");
		idInput.value = "";
		idInput.focus();
		return false;
	} else if (containsAnother(idInput)) {
		alert("ID에는 숫자, 영어 대소문자, 특수문자(@._)만  사용할 수 있습니다")
		idInput.value = "";
		idInput.focus();
		return false;
	} else if ($("#id_Input").css("font-style") === "italic") {
		alert("이미 사용되고 있는 ID입니다");
		idInput.focus();
		return false;
	} else if (isEmpty(pwInput)) {
		alert("PW를 입력해주세요");
		pwInput.value = "";
		pwInput.focus();
		return false;
	} else if (containsAnother(pwInput)) {
		alert("PW를 다시 입력해주세요");
		pwInput.value = "";
		pwInput.focus();
		return false;
	} else if (notEqualPw(pwInput, pwChkInput)) {
		alert("PW가 다릅니다");
		pwInput.value = "";
		pwChkInput.value = "";
		pwInput.focus();
		return false;
	} else if (isEmpty(nameInput)) {
		alert("이름을 입력해주세요");
		nameInput.value = "";
		nameInput.focus();
		return false;
	} else if (isEmpty(phone1Input) || isEmpty(phone2Input)
			|| isEmpty(phone3Input)) {
		alert("전화번호를 입력해주세요");
		phone1Input.focus();
		return false;
	} else if (isNumlengthCheck(phone1Input, 3)
			|| isNumlengthCheck(phone2Input, 4)
			|| isNumlengthCheck(phone3Input, 4)) {
		alert("전화번호 오류");
		phone1Input.value = "";
		phone2Input.value = "";
		phone3Input.value = "";
		phone1Input.focus();
		return false;
	} else if (isEmpty(addr3Input) || isEmpty(addr1Input)
			|| isEmpty(addr2Input)) {
		alert("주소를 입력해주세요");
		addr2Input.value = "";
		addr2Input.focus();
		return false;
	} else if (isEmpty(picInput)) {
		alert("프로필 사진을 선택해주세요");
		picInput.focus();
		return false;
	} else if (isNotType(picInput, "png") && isNotType(picInput, "PNG")
			&& isNotType(picInput, "jpg") && isNotType(picInput, "JPG")
			&& isNotType(picInput, "jpeg") && isNotType(picInput, "JPEG")
			&& isNotType(picInput, "gif") && isNotType(picInput, "GIF")
			&& isNotType(picInput, "bmp") && isNotType(picInput, "BMP")) {
		alert("프로필 사진은 png, jpg, jpeg, gif, bmp 형식만 지원합니다");
		picInput.value = "";
		picInput.focus();
		return false;
	}
	if (roleInput.value === "1") {
		var adminCode = document.joinForm.adminCode
		if (adminCode.value !== "20231215") {
			$(".adminUser").prop("checked", false);
			$(".normalUser").prop("checked", true);
			$("#adminCodeInput").css("display", "none");
			alert("올바른 관리자 코드가 아닙니다");
			return false
		}
	}
	return true;
}

function updateCheck() {
	var idInput = document.updateForm.m_id;
	var pwInput = document.updateForm.m_pw;
	var pwChkInput = document.updateForm.m_pwChk;
	var nameInput = document.updateForm.m_name;

	var phone1Input = document.updateForm.phone1;
	var phone2Input = document.updateForm.phone2;
	var phone3Input = document.updateForm.phone3;

	var addr3Input = document.updateForm.addr3;
	var addr1Input = document.updateForm.addr1;
	var addr2Input = document.updateForm.addr2;

	var picInput = document.updateForm.m_pic;

	if (isEmpty(idInput)) {
		alert("ID를 입력해주세요");
		idInput.value = "";
		idInput.focus();
		return false;
	} else if (containsAnother(idInput)) {
		alert("ID에는 숫자, 영어 대소문자, 특수문자(@._)만  사용할 수 있습니다")
		idInput.value = "";
		idInput.focus();
		return false;
	} else if ($("#inInput").css("text-decoration") == "line-through") {
		alert("이미 사용되고 있는 ID입니다");
		idInput.focus();
		return false;
	} else if (isEmpty(pwInput)) {
		alert("PW를 입력해주세요");
		pwInput.value = "";
		pwInput.focus();
		return false;
	} else if (containsAnother(pwInput)) {
		alert("PW를 다시 입력해주세요");
		pwInput.value = "";
		pwInput.focus();
		return false;
	} else if (isEmpty(pwChkInput)) {
		alert("PW Check를 입력해주세요")
		pwChkInput.focus();
		return false;
	} else if (notEqualPw(pwInput, pwChkInput)) {
		alert("PW가 다릅니다");
		pwChkInput.value = "";
		pwChkInput.focus();
		return false;
	} else if (isEmpty(nameInput)) {
		alert("이름을 입력해주세요");
		nameInput.value = "";
		nameInput.focus();
		return false;
	} else if (isEmpty(phone1Input) || isEmpty(phone2Input)
			|| isEmpty(phone3Input)) {
		alert("전화번호를 입력해주세요");
		phone1Input.focus();
		return false;
	} else if (isNumlengthCheck(phone1Input, 3)
			|| isNumlengthCheck(phone2Input, 4)
			|| isNumlengthCheck(phone3Input, 4)) {
		alert("전화번호 오류");
		phone1Input.value = "";
		phone2Input.value = "";
		phone3Input.value = "";
		phone1Input.focus();
		return false;
	} else if (isEmpty(addr3Input) || isEmpty(addr1Input)
			|| isEmpty(addr2Input)) {
		alert("주소를 입력해주세요");
		addr2Input.value = "";
		addr2Input.focus();
		return false;
	} else if (isEmpty(picInput)) {
		alert("프로필 사진을 선택해주세요");
		picInput.focus();
		return false;
	} else if (isNotType(picInput, "png") && isNotType(picInput, "PNG")
			&& isNotType(picInput, "jpg") && isNotType(picInput, "JPG")
			&& isNotType(picInput, "jpeg") && isNotType(picInput, "JPEG")
			&& isNotType(picInput, "gif") && isNotType(picInput, "GIF")
			&& isNotType(picInput, "bmp") && isNotType(picInput, "BMP")) {
		alert("프로필 사진은 png, jpg, jpeg, gif, bmp 형식만 지원합니다");
		picInput.value = "";
		picInput.focus();
		return false;
	}
	return true;
}

function postCheck() {
	var postInput = document.postForm.p_txt;
	if (isEmpty(postInput)) {
		return false;
	}
	return true;
}

function editPostCheck() {
	var initial = $("#initialValue").val();
	var current = $("#postTextarea").val();
	var chk = (initial != current);
	return initial != current;
}

function commCheck() {
	var commInput = document.querySelector("#commTextarea");
	if (isEmpty(commentInput)) {
		return false;
	}
	return true;
}

function searchCheck() {
	var searchInput = document.searchForm.search;
	if (isEmpty(searchInput)) {
		return false;
	}
	return true;
}
