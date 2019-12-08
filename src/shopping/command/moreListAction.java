package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.command.CommandAction;
import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class moreListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		
		String category = request.getParameter("category");
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("category : "+category);
		ProductDB pDB = ProductDB.getInstance();
		prod_List = pDB.getMoreList(category, page);
		request.setAttribute("prod_List", prod_List);
		
		return "/products/prod_listPro.jsp";
	}

}
