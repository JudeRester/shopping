<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/shopping/css/prod_list.css">
<script src="/shopping/js/prepare_prod_list.js"></script>
<section>
	<h3>
판매 예정 상품
	</h3>
	<ul>
		<c:choose>
			<c:when test="${empty prod_List }">
				<h3>상품이 존재하지 않습니다.</h3>
			</c:when>
			<c:when test="${!empty prod_List }">
				<c:forEach var="prod" items="${prod_List}">
					<li><a
						href="/shopping/products/prod_info.do?pro_num=${prod.pro_num }"><img
							src="${prod.title_img }" alt="" /></a>
						</li>
				</c:forEach>
			</c:when>
		</c:choose>

	</ul>
</section>
<%@ include file="../footer.jsp"%>