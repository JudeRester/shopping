<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="item" items="${prod_List}" step="1" varStatus="status">
<li>
<img src=${item.title_img } />
<div class="count" id=${item.pro_num }></div>
<script>
				var orgDate = "${item.strEnd_date}";
				var dateArray = orgDate.split(",");
				var date = new Date(dateArray[0],dateArray[1]-1,dateArray[2]);
				getTimer(date, "${item.pro_num}");
				</script>

</li>
</c:forEach>