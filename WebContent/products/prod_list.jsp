<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" type="text/css" href="/shopping/css/prod_list.css">
<script src="/shopping/js/prod_list.js"></script>
<section>
	<ul>
		<c:forEach var="prod" items="${prod_List}">
				<li><img src="${prod.title_img }" alt="" />
					<div class="count" id="${prod.pro_num }"></div></li>
				<script>
				var orgDate = "${prod.strEnd_date}";
				var dateArray = orgDate.split(",");
				var date = new Date(dateArray[0],dateArray[1]-1,dateArray[2]);
				getTimer(date, "${prod.pro_num}");
				</script>
			</c:forEach>
	</ul>
</section>
<%@ include file="../footer.jsp"%>