<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="item" items="${prod_List}" step="1" varStatus="status">
	<li><a
		href="/shopping/products/prod_info.do?pro_num=${item.pro_num }"><img
			src=${item.title_img } /></a></li>
</c:forEach>