package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.CartDTO;
import shopping.databases.CartDB;

public class cartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id  = (String)request.getSession().getAttribute("id");
		CartDB cDB = CartDB.getInstance();
		List<CartDTO> cart = cDB.getCart(id);
		request.setAttribute("cart", cart);
		return "/member/cart.jsp";
	}

}
