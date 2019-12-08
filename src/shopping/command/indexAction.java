package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.command.CommandAction;
import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class indexAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		
		ProductDB pDB = ProductDB.getInstance();//DB연동
		
		//카테고리별 최신의 상품 3개씩 얻어내서 List에 저장
		prod_List = pDB.getEndsoon();
		
        //해당 페이지로 보낼 내용 설정
        request.setAttribute("prod_List", prod_List);
        //사용자 화면을 의미하는 값을 설정
		return "/index.jsp";
	}

}
