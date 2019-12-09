<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="/shopping/css/cart.css">
<script src="/shopping/js/cart.js"></script>
<section>
	<h3>장바구니</h3>
	<hr />
	<c:forEach var="cart" items="${cart }">
		<table>
			<tr>
				<td rowspan="3"><input type="checkbox" id="${cart.pro_num }"/></td>
				<td rowspan="3" class="thumb"><img class="thumb"
					src="${cart.title_img }" alt="" /></td>
				<td>제목</td>
				<td>${cart.title }</td>
			</tr>
			<tr>
				<td>수량</td>
				<td><img src="/shopping/images/minus-circle.png" alt="" id="minus" />
				${cart.amount }<img src="/shopping/images/plus-circle.png" alt="" id="plus"/></td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${cart.amount *cart.price }</td>
			</tr>
		</table>
	</c:forEach>
	<hr />
</section>
<%@ include file="../footer.jsp"%>