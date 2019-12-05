<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/header.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  
  <script>
  	$(document).ready(function(){
  		var $panel = $("#endsoon").find("ul");
  		var itemWidth = $panel.children().outerWidth();
  		var itemLength = $panel.children().length;
  		
  		var rollingId;
  		auto();
  		
  		function start(){
  			$panel.css("width",itemWidth * itemLength);
  			$panel.animate({"left":-itemWidth + "ps"}, function(){
  				//첫번째 아이템을 마지막에 추가
  				$(this).append("<li>"+$(this).find("li:first").html()+"</li>");
  				//첫번째 아이템 삭제
  				$(this).find("li:first").remove();
  				//좌측 패널 수치 초기화
  				$(this).css("left",0);
  			});
  		}
  		function auto(){
  			rollingId=setInterval(function(){
  				start();
  			},2000);
  		}
  	});
  </script>
</head>
<body>
	<div id="logo"> 
	<a href=""><img src="images/main_Logo.png" alt="" /></a>
	</div>
		<div id="menu">
			<ul>
				<li><a href="#">상품</a>
					<ul>
						<li><a href="#">전체 목록</a></li>
						<li><a href="#">카테고리별</a></li>
						<li><a href="#">판매 예정</a></li>
					</ul></li>
				<li><a href="#">소식</a>
					<ul>
						<li><a href="#">공지사항</a></li>
						<li><a href="#">이벤트</a></li>
					</ul></li>
				<li><a href="#">&nbsp;</a></li>
				<li><a href="#">고객센터</a></li>
				<li><a href="#">장바구니</a></li>
			</ul>
		</div>
	
	<div id="mid">
		<h3>마감 임박</h3>
		<div id="endsoon">
			<ul>
				<li><img src="images/test.png" alt="" /></li>
				<li><img src="images/test2.png" alt="" /></li>
				<li><img src="images/test3.png" alt="" /></li>
				<li><img src="images/test.png" alt="" /></li>
				<li><img src="images/test2.png" alt="" /></li>
				<li><img src="images/test3.png" alt="" /></li>
			</ul>
		</div>
		<div id="arrows">
			<a href="javascript:void(0)"><img src="images/left-arrow.png" alt="" id="left_arrow" /></a>
			<a href="javascript:void(0)"><img src="images/right-arrow.png" alt="" id="right_arrow" /></a>
		</div>
	</div>

</body>
</html>