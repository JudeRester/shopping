/**
 * 
 */
$(document).ready(function() {
	$("#addCart").click(function() {
		var query = {
			id : $("#userid").val(),
			pro_num : $("#pro_num").val()
		};

		$.ajax({
			type : "POST",
			url : "/shopping/products/cartAddAction.do",
			data : query,
			success : function(data) {
				alert("상품을 장바구니에 담았습니다.");
			}
		});
	});
	
	var $panel = $("#screenShots").find("ul");
	var itemWidth = $panel.children().outerWidth();
	var itemLength = $panel.children().length;

	var rollingId;
	auto();

	function start() {
		$panel.css("width", itemWidth * itemLength);
		$panel.animate({
			"left" : -itemWidth + "ps"
		}, function() {
			// 첫번째 아이템을 마지막에 추가
			$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
			// 첫번째 아이템 삭제
			$(this).find("li:first").remove();
			// 좌측 패널 수치 초기화
			$(this).css("left", 0);
		});
	}
	function auto() {
		rollingId = setInterval(function() {
			start();
		}, 3000);
	}
	// 마우스 오버시 멈춤
	$panel.mouseover(function() {
		clearInterval(rollingId);
	});
	// 마우스 아웃시 다시 재생
	$panel.mouseout(function() {
		auto();
	});
	// 이전 이벤트
	function prev(e) {
		$panel.css("left", -itemWidth);
		$panel.prepend("<li>" + $panel.find("li:last").html() + "</li>");
		$panel.animate({
			"left" : "0px"
		}, function() {
			$(this).find("li:last").remove();
		})
	}

	// 이전 화살표 클릭시 이벤트 처리
	$("#left_arrow").on("click", prev);
	$("#left_arrow").mouseover(function(e) {
		clearInterval(rollingId);
	});
	$("#left_arrow").mouseout(auto);

	// 다음 이벤트
	function next(e) {
		$panel.animate({
			"left" : -itemWidth + "px"
		}, function() {
			$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
			$(this).find("li:first").remove();
			$(this).css("left", 0);
		});
	}

	// 다음 화살표 클릭시 이벤트 처리
	$("#right_arrow").on("click", next);
	$("#right_arrow").mouseover(function(e) {
		clearInterval(rollingId);
	});
	$("#right_arrow").mouseout(auto);
});