<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/endsoon.js"></script>
<script src="js/count.js"></script>
<div id="mid">
	<h3>마감 임박</h3>
	<div id="endsoon">		<ul>
			<c:forEach var="prod" items="${prod_List}">
				<li><a href="/shopping/products/prod_info.do?pro_num=${prod.pro_num }"><img src="${prod.title_img }" alt="" /></a>
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
			</c:forEach>
		</ul>
	</div>
	<div id="arrows">
		<a href="javascript:void(0)"><img src="images/left-arrow.png"
			alt="" id="left_arrow" /></a> <a href="javascript:void(0)"><img
			src="images/right-arrow.png" alt="" id="right_arrow" /></a>
	</div>
</div>
<section>
	<article class="arti">
		<a href="/shopping/products/prod_list.do?category=all"><img
			id="curr_sale" src="" alt="" /></a>
		<div>판매중인 상품</div>
	</article>
	<article class="arti">
		<div>판매예정 상품</div>
		<a href="javascript:void(0)"><img src="images/test.png" alt="" /></a>

	</article>

</section>
<%@ include file="./footer.jsp"%>