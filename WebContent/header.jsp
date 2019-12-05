<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
.nav1 {
	background-color: #333;
	height: 10%;
	overflow: hidden;
}

.nav1 a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.nav1 a:hover {
	background-color: #ddd;
	color: black;
}

.nav1 a.active {
	background-color: #4CAF50;
	color: white;
}
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 30%; /* Could be more or less, depending on screen size */
}
</style>

<script>
	/* $('a[href="#login"]').click(function(event) {
		event.preventDefault();

		$(this).modal({
			fadeDuration : 250
		});
	}); */
$(function(){
	$('#login').click(function() {
		console.log("asdf");
		$('#modal').show();
		
	});
});
	
	//팝업 Close 기능
	function close_pop(flag) {
		$('#modal').hide();
	};
</script>

</head>
<body>
	<div class="modal">
		<div class="modal-content">
			<hr />
			<h2>로그인</h2>
			<hr />
			<form action="/webedu/MEMBER/memLoginOK.jsp" method="post">

				아이디 : <input type="text" name="id" id='id' /><br> 비밀번호 : <input
					type="password" name="passwd" id='passwd' /><br> <input
					type="submit" value="로그인" /> <input type="button" value="회원가입"
					onClick="javascript:window.location='/webedu/MEMBER/memjoin.jsp'" />

			</form>
		</div>
	</div>
	<nav id="nav1">
		<table>
			<td><a href="#">홈</a></td>
			<td><a href="#" id="login">로그인</a></td>
			<td><a href="#" target="">회원가입</a></td>
			<td></td>
			<td></td>
			<td></td>
		</table>
	</nav>
</body>
</html>