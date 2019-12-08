/**
 * 
 */
$(document).ready(function() {
	$("#addCart").click(function() {// [로그인]버튼 클릭
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
});