package shopping.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.command.CommandAction;
import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class prodInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ProductDTO prod = null;
		String pro_num = request.getParameter("pro_num");
		System.out.println(pro_num);
		ProductDB pDB = ProductDB.getInstance();
		prod = pDB.getProInfo(pro_num);
		request.setAttribute("prod", prod);
		
		System.out.println(prod.getPrice());
		return "/products/prod_info.jsp";
	}

}
