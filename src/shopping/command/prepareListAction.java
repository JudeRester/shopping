package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class prepareListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		
		ProductDB pDB = ProductDB.getInstance();// DB연동
		prod_List = pDB.getprepareList();
		request.setAttribute("prod_List", prod_List);
		return "/products/prepare_prod_list.jsp";
	}

}
