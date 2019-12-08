/**
 * 
 */
$(document).ready(function(){
	$("#login").on("click",function(){
		$(".login-modal").css("display","block");
	});
	$(".cancel").on("click",function(){
		$(".login-modal").css("display","none");
		$(".join-modal").css("display","none");
	});
	$("#join").on("click",function(){
		$(".join-modal").css("display","block");
	});
	
	$("#uLogin").click(function(){//[로그인]버튼 클릭
		alert("asdfasdfasdfsdfasdfa");
		  var query = {id : $("#id").val(), 
				       passwd:$("#pass").val()};
		  alert("1234123412341234");
		  $.ajax({
		     type: "POST",
		     url: "/shopping/loginPro.do",
		     data: query,
		     err: function(err){
		    	 alert("err");
		     },
		     success: function(data){
		    	 var str1 = '<p id="ck">';
		    	 alert(str1);
		    	 var loc = data.indexOf(str1);
		    	 alert(loc);
		    	 var len = str1.length;
		    	 alert(len);
		    	 var check = data.substr(loc+len,1);
		    	 alert(check);
		    	 if(check == "1"){//
		    		 alert("check들어옴");
		    		window.location.href("/shopping/index.do");
		    	 }else if(check == "0"){
		    	  	alert("비밀번호 틀림");
		    	  	$("#pass").val("");
		    	 }else{
		    	    alert("아이디 틀림");
		    	    $("#id").val("");
		    	    $("#pass").val("");
		        }
		 	}
		  });
		  alert("asdf");
	});
});
