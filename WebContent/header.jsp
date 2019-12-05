<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
<link rel="stylesheet" type="text/css" href="css/loginmodal.css">
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script src="js/count.js"></script>
<script src="js/endsoon.js"></script>
<script src="js/modal.js"></script>
</head>
<body>
	<div id="logo">
		<div id="memfuncs">
			<ul>
				<li><a href="javascript:void(0)" id="login">로그인</a></li>
				<li><a href="javascript:void(0)">회원가입</a></li>
			</ul>
		</div>
		<a href="javascript:void(0)"><img src="images/main_Logo.png"
			alt="" /></a>

	</div>
	<div id="menu">
		<ul>
			<li><a href="#">상품</a>
				<ul>
					<li><a href="#">전체 목록</a></li>
					<li><a href="#">카테고리별</a></li>
					<li><a href="#">판매 예정</a></li>
				</ul></li>
			<li><a href="#">소식</a>
				<ul>
					<li><a href="#">공지사항</a></li>
					<li><a href="#">이벤트</a></li>
				</ul></li>
			<li><a href="#">&nbsp;</a></li>
			<li><a href="#">고객센터</a></li>
			<li><a href="#">장바구니</a></li>
		</ul>
	</div>

	<div class="modal">
		<div class="modal-content">
			<form action="" method="post">
				<p>로그인</p>
				<table>
					<tr>
						<td><label for="id">아이디</label></td>
						<td><input type="text" id="id" name="id" /></td>
					</tr>
					<tr>
						<td><label for="pass">비밀번호</label></td>
						<td><input type="password" id="pass" name="pass" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="로그인" /> <input type="button" value="취소" id="cancel" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>