package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class morePrepareListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		int page = Integer.parseInt(request.getParameter("page"));
		ProductDB pDB = ProductDB.getInstance();
		prod_List = pDB.getMorePrepareList(page);
		request.setAttribute("prod_List", prod_List);

		return "/products/prepare_prod_listPro.jsp";
	}

}
