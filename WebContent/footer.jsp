<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer>
	<a href="javascript:void(0)" id="mmlogin"><img
		src="/shopping/images/mini_Logo.png" alt="" /></a> © 2019 Legendary
	Games. All rights reserved. All trademarks are property of their
	respective owners in the KR and other countries.
</footer>

<div class="mlogin-modal">
	<div class="mlogin-modal-content">
		<form action="" method="post">
			<p>관리자 로그인</p>
			<table>
				<tr>
					<td><label for="mmid">아이디</label></td>
					<td><input type="text" id="mmid" name="mmid" /></td>
				</tr>
				<tr>
					<td><label for="mpass">비밀번호</label></td>
					<td><input type="password" id="mpass" name="mpass" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="로그인" id="mLogin" /> <input
						type="button" value="취소" class="cancel" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>