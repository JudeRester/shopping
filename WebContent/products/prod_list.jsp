<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/shopping/css/prod_list.css">
<script src="/shopping/js/prod_list.js"></script>
<section>
	<h3>
		<c:choose>
			<c:when test="${param.category=='RTS' }">
				RTS
			</c:when>
			<c:when test="${param.category=='ACT' }">
				액션
			</c:when>
			<c:when test="${param.category=='FPS' }">
				FPS
			</c:when>
			<c:when test="${param.category=='RPG' }">
				RPG
			</c:when>
		</c:choose>
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
						<div class="count" id="${prod.pro_num }"></div></li>
					<script>
						var orgDate = "${prod.strEnd_date}";
						var dateArray = orgDate.split(",");
						var date = new Date(dateArray[0], dateArray[1] - 1,
								dateArray[2]);
						getTimer(date, "${prod.pro_num}");
					</script>
				</c:forEach>
			</c:when>
		</c:choose>

	</ul>
</section>
<%@ include file="../footer.jsp"%>