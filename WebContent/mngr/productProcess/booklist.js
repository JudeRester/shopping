$(document).ready(function(){
	
	$("#regist").click(function(){//[책등록]버튼 클릭
		$(location).attr('href', "/shopping/mg/bookRegisterForm.do");
//		window.location.href("/shopping/mg/bookRegisterForm.do");
	});
	
	$("#bookMain").click(function(){//[관리자 메인으로]버튼 클릭
		$(location).attr('href', "/shopping/mg/managerMain.do");
//		window.location.href("/shopping/mg/managerMain.do");
	});
});

//[수정]버튼을 클릭하면 자동실행
function edit(editBtn){
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/shopping/mg/bookUpdateForm.do?pro_num="+arr[0];
	query += "&book_kind="+arr[1];
	window.location.href(query);
}

//[삭제]버튼을 클릭하면 자동실행
function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "/shopping/mg/bookDeletePro.do?pro_num="+arr[0];
	query += "&book_kind="+arr[1];
	window.location.href(query);
}