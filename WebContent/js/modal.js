/**
 * 
 */
$(document).ready(function() {
	$("#login").on("click", function() {
		$(".login-modal").css("display", "block");
	});
	$("#mmlogin").on("click", function() {
		$(".mlogin-modal").css("display", "block");
	});
	$(".cancel").on("click", function() {
		$(".login-modal").css("display", "none");
		$(".mlogin-modal").css("display", "none");
		$(".join-modal").css("display", "none");
	});
	$("#join").on("click", function() {
		$(".join-modal").css("display", "block");
	});

	$("#uLogin").click(function() {// [로그인]버튼 클릭
		var query = {
			id : $("#id").val(),
			passwd : $("#pass").val()
		};

		$.ajax({
			type : "POST",
			url : "/shopping/loginPro.do",
			data : query,
			success : function(data) {
				var str1 = '<p id="ck">';
//				alert(str1);
				var loc = data.indexOf(str1);
//				alert(loc);
				var len = str1.length;
//				alert(len);
				var check = data.substr(loc + len, 1);
//				alert(check);
				if (check == "1") {//
					$(location).attr('href', "/shopping/index.do");
//					window.location.href = "/shopping/index.do";
				} else if (check == "0") {
					alert("비밀번호 틀림");
					$("#pass").val("");
				} else {
					alert("아이디 틀림");
					$("#id").val("");
					$("#pass").val("");
				}
			}
		});
//		alert("회원");
	});
	
	$("#mLogin").click(function() {// [관리자로그인]버튼 클릭
		var query = {
			mid : $("#mmid").val(),
			mpasswd : $("#mpass").val()
		};

		$.ajax({
			type : "POST",
			url : "/shopping/mg/managerLoginPro.do",
			data : query,
			success : function(data) {
				var str1 = '<p id="ck">';
//				alert(str1);
				var loc = data.indexOf(str1);
//				alert(loc);
				var len = str1.length;
//				alert(len);
				var check = data.substr(loc + len, 1);
//				alert(check);
				if (check == "1") {//
					console.log("asdf");
					$(location).attr('href', "/shopping/mg/managerMain.do");
//					window.location.href = "/shopping/mg/managerMain.do";
				} else if (check == "0") {
					alert("비밀번호 틀림");
					$("#pass").val("");
				} else {
					alert("아이디 틀림");
					$("#mid").val("");
					$("#mpass").val("");
				}
			}
		});
//		alert("관리자");
	});

	$("#logout").click(function() {// [로그아웃]버튼 클릭
		$.ajax({
			type : "POST",
			url : "/shopping/logout.do",
			success : function(data) {
				$(location).attr('href', "/shopping/index.do");
//				window.location.href = "/shopping/index.do";
			}
		});
	});

	$("#checkId").click(function() {// [ID중복확인]버튼 클릭
		if ($("#id_join").val()) {
			var query = {
				id : $("#id_join").val()
			};

			$.ajax({
				type : "post",
				url : "/shopping/confirmId.do",
				data : query,
				success : function(data) {
					var str1 = '<p id="ck">';
					var loc = data.indexOf(str1);
					var len = str1.length;
					var check = data.substr(loc + len, 1);
					if (check == "1") {// 사용할 수 없는 아이디
						alert("사용할 수 없는 아이디");
						$("#id_join").val("");
					} else
						// 사용할 수 있는 아이디
						alert("사용할 수 있는 아이디");
				}
			});
		} else {// 아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
			alert("사용할 아이디를 입력");
			$("#id_join").focus();
		}
	});
	$("#process").click(function() {// [가입하기]버튼 클릭
		var query = {
			id : $("#id_join").val(),
			passwd : $("#pass_join").val(),
			name : $("#name").val(),
			birth : $("#birth").val(),
			address : $("#address").val(),
			phone : $("#phone").val(),
			email : $("#email").val()
		};

		$.ajax({
			type : "post",
			url : "/shopping/registerPro.do",
			data : query,
			success : function(data) {
				$(location).attr('href', "/shopping/index.do");
//				window.location.href = "/shopping/index.do";
			}
		});
	});
});
