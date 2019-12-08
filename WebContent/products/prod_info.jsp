<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/shopping/css/prod_info.css">
<div class="visual_img">
<div id="screenShots">
		<ul>
			<%-- <c:forEach var="prod" items="${prod_List}">
				<li><img src="${prod.title_img }" alt="" />
					<div class="count" id="${prod.pro_num }"></div></li>
				<script>
					var orgDate = "${prod.strEnd_date}";
					var dateArray = orgDate.split(",");
					var date = new Date(dateArray[0], dateArray[1] - 1,
							dateArray[2]);
					getTimer(date, "${prod.pro_num}");
					img = "${prod.title_img}";
					$(document).ready(function() {
						$("#curr_sale").attr("src", img);
					});
				</script>
			</c:forEach> --%>
		</ul>
	</div>
	<div id="arrows">
		<a href="javascript:void(0)"><img src="/shopping/images/left-arrow.png"
			alt="" id="left_arrow" /></a> <a href="javascript:void(0)"><img
			src="/shopping/images/right-arrow.png" alt="" id="right_arrow" /></a>
	</div>
</div>
<section>
	<article class="arti">
		제목 : ${prod.title} <br />
		가격 : \ ${prod.price }<br/>
		게임 정보 : ${prod.pro_desc }<br/>
		<input type="button" id="addCart" value="장바구니 넣기" />
	</article>
<!-- 	<article class="arti_2">
		권장사양
	</article> -->
	<article class="arti_2">
		최소사양
	</article>
	<article class="arti_2">
		권장사양
	</article>

</section>
<%@ include file="../footer.jsp"%>