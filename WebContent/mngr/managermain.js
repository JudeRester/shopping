var status = true;

$(document).ready(function(){
	$("#registProduct").click(function(){//[상품등록]버튼 클릭
		$(location).attr('href', "/shopping/mg/gameRegisterForm.do");
//		window.location.href="/shopping/mg/bookRegisterForm.do";
	});
	
	$("#updateProduct").click(function(){//[상품수정/삭제]버튼 클릭
		$(location).attr('href', "/shopping/mg/gameList.do?kind=all");
//		window.location.href="/shopping/mg/bookList.do?book_kind=all";
	});
	
	$("#orderedProduct").click(function(){//[전체구매목록 확인]버튼 클릭
		$(location).attr('href', "/shopping/mg/orderList.do");
//		window.location.href="/shopping/mg/orderList.do";
	});
	
	$("#qna").click(function(){//[상품 QnA답변]버튼 클릭
		$(location).attr('href', "/shopping/mg/qnaList.do");
//		window.location.href="/shopping/mg/qnaList.do";
	});
});