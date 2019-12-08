package shopping.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.databases.CartDB;

public class CartAddAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pro_num = request.getParameter("pro_num");
		String id  = request.getParameter("id");
		CartDB cDB = CartDB.getInstance();
		System.out.println(id+pro_num);
		cDB.insertCart(id, pro_num);
		
		return "/products/prod_info.jsp";
	}

}
