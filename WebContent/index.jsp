<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/endsoon.js"></script>
<div id="mid">
	<h3>마감 임박</h3>
	<div id="endsoon">
		<ul>
			<li><img src="images/test.png" alt="" />
				<div class="count" id="a"></div></li>
			<li><img src="images/test2.png" alt="" />
				<div class="count" id="b"></div></li>
			<li><img src="images/test3.png" alt="" />
				<div class="count" id="c"></div></li>
			<li><img src="images/test.png" alt="" />
				<div class="count" id="d"></div></li>
			<li><img src="images/test2.png" alt="" />
				<div class="count" id="e"></div></li>
			<li><img src="images/test3.png" alt="" />
				<div class="count" id="f"></div></li>
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
		<a href="javascript:void(0)"><img src="images/test.png" alt="" /></a>
		<div>판매중인 상품</div>
	</article>
	<article class="arti">
		<div>판매예정 상품</div>
		<a href="javascript:void(0)"><img src="images/test.png" alt="" /></a>
		
	</article>

</section>
<%@ include file="./footer.jsp"%>