<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ include file="../header.jsp"%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />

<link rel="stylesheet" type="text/css"
	href="/shopping/css/prod_info.css">
<script src="/shopping/js/prod_info.js"></script>
<div class="visual_img">
	<div id="screenShots">
		<ul>
			<c:forEach var="images" items="${images}">
				<li><img src="${images }.jpg" alt="" /></li>

			</c:forEach>
		</ul>
	</div>
	<div id="arrows">
		<a href="javascript:void(0)"><img
			src="/shopping/images/left-arrow.png" alt="" id="left_arrow" /></a> <a
			href="javascript:void(0)"><img
			src="/shopping/images/right-arrow.png" alt="" id="right_arrow" /></a>
	</div>
</div>
<section>
	<article class="arti">
		제목 : ${prod.title} <br /> 가격 : \ ${prod.price }<br /> 게임 정보 :
		${prod.pro_desc }<br />
		<c:choose>
			<c:when test="${empty sessionScope.id }">

				<input type="button" id="addCart" value="장바구니 넣기"
					disabled="disabled" />
			</c:when>
			<c:when test="${!empty sessionScope.id }">
				<input type="text" id="userid" value="${sessionScope.id }"
					hidden="hidden" />
				<input type="text" id="pro_num" value="${param.pro_num }"
					hidden="hidden" />
				<input type="button" id="addCart" value="장바구니 넣기" />

			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${prod.begin_date>today }">
				<script>
					$("#addCart").attr('disabled','disabled');
				</script>
			</c:when>
			<c:when test="${prod.end_date<today }">
				<script>
					$("#addCart").attr('disabled','disabled');
				</script>
			</c:when>
		</c:choose>
	</article>
	<!-- 	<article class="arti_2">
		권장사양
	</article> -->
	<article class="arti_2">최소사양</article>
	<article class="arti_2">권장사양</article>

</section>

<%@ include file="../footer.jsp"%>