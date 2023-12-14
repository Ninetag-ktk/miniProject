$(function() {
	adminCheck();
	autoResizeTextarea();
	changePerPage();
	chkPerPage();
	editPost();
	idDupChk();
	phoneNumFocus();
	searchAddress();
	searchBarDisplay();
});

function adminCheck() {
	$(".roleJoin").change(function() {
		var role = $(this).val();
		if (role === "1") {
			$("#adminCodeInput").css("display", "block");
			$("#adminCodeInput").css("margin", "auto");
			$("#adminCodeInput").css("margin-top", "10px");
			$("#adminCodeInput").css("width", "50%");
		} else {
			$("#adminCodeInput").css("display", "none");
		}
	});
}

function autoResizeTextarea() {
	$('textarea').on('keyup', function(e) {
		$(this).css('height', 'auto');
		$(this).height(this.scrollHeight - 6);
	});
	$('textarea').keyup();
}

function changePerPage() {
	$("#perPage").change(function() {
		var perPage = $(this).val();
		location.href = "board.go?p=" + perPage;
	});
}

function chkPerPage() {
	var url = new URLSearchParams(window.location.search);
	var p = url.get("p");
	if (p != null) {
		$("#perPage").val(p);
	} else {
		$("#perPage").val(3);
	}
}

function editPost() {
	var editMode = false;
	$(".editBtn").click(function() {
		editMode = !editMode;
		var thisTextarea = $(this).closest("table").find("#postTextarea");
		var editForm = $(this).closest("table").find("#editForm");
		if (editMode) {
			$(this).children("img").attr("src", "resources/img/editMode.png");
			$(this).children("img").css("background-color", "white");
			thisTextarea.prop("disabled", false);
			thisTextarea.css("border", "solid 1px");
			thisTextarea.focus();
		} else {
			editForm.submit();
			$(this).children("img").attr("src", "resources/img/edit.png");
			$(this).children("img").css("background-color", "transparent");
			thisTextarea.prop("disabled", true);
			thisTextarea.css("border", "none")
		}
	});
}

function idDupChk() {
	$("#id_Input").keyup(function() {
		var m_id = $(this).val();
		$.getJSON("member.id.check?m_id=" + m_id, function(memberJSON) {
			if (memberJSON.length > 0) {
				$("#id_Input").css("color", "red");
				$("#id_Input").css("text-decoration", "line-through");
				$("#id_Input").css("font-style", "italic");
			} else {
				$("#id_Input").css("color", "white");
				$("#id_Input").css("text-decoration", "none");
				$("#id_Input").css("font-style", "normal");
			}
		});
	});
}

// 전화번호 입력하면 알아서 넘어가도록 하기
function phoneNumFocus() {
	$(".num1").on("input", function() {
		if (this.value.length === 3) {
			$(".num2").focus();
		}
	});
	$(".num2").on("input", function() {
		if (this.value.length === 4) {
			$(".num3").focus();
		}
	});
	$(".num3").on("input", function() {
		if (this.value.length === 4) {
			$(".num3").blur();
		}
	});
}

// Daum의 우편번호 검색 시스템
function searchAddress() {
	$('.address').focus(function() {
		$('.postcode').blur();
		$('.roadAddr').blur();
		new daum.Postcode({
			oncomplete : function(data) {
				$('.postcode').val(data.zonecode);
				$('.roadAddr').val(data.roadAddress);
			}
		}).open();
		$('.detailAddr').focus();
	});
	$('.addressTxt').click(function(e) {
		$('.postcode').blur();
		$('.roadAddr').blur();
		new daum.Postcode({
			oncomplete : function(data) {
				$('.postcode').val(data.zonecode);
				$('.roadAddr').val(data.roadAddress);
			}
		}).open();
		$('.detailAddr').focus();
	});
}

function searchBarDisplay() {
	$('#searchFrame').mouseenter(function() {
		$('.searchBar').css("width", "300px");
		setTimeout(function() {
			$('.searchBar').children('input').css("opacity", "1");
		}, 200);
	});
	$('#searchFrame').mouseleave(function() {
		if ($('.searchBar').children('input').is(":focus")) {
			return;
		} else {
			$('.searchBar').children('input').css("opacity", "0");
			setTimeout(function() {
				$('.searchBar').css("width", "0");
			}, 200);
		}
	});
	$('.searchBar').children('input').focusout(function() {
		$('.searchBar').children('input').css("opacity", "0");
		setTimeout(function() {
			$('.searchBar').css("width", "0");
		}, 150);
	});
}