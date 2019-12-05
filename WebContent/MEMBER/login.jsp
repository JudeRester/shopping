
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {
<%--alert("방문을 환영합니다!");--%>
	$("form").submit(function() {
			if ($("#id").val() == "") {
				alert("아이디를 입력해주세요");
				$("#id").focus();
				return false;
			} else if ($("#passwd").val() == "") {
				alert("비밀번호를 입력해주세요");
				$("#passwd").focus();
				return false;
			}
		});
	});
</script>

	
