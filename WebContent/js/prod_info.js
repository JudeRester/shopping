/**
 * 
 */
$(document).ready(function() {
	$("#addCart").click(function() {// [로그인]버튼 클릭
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
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == "1") {//
					$(location).attr('href', "/shopping/index.do");
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
	});
});