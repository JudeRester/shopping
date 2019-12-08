package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class prodListAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		
		String category = request.getParameter("category");
		System.out.println(category);
		ProductDB pDB = ProductDB.getInstance();// DB연동
		prod_List = pDB.getList(category);
		request.setAttribute("prod_List", prod_List);
		return "/products/prod_list.jsp";
	}

}
