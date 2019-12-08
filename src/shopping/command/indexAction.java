package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.command.CommandAction;
import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class indexAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<ProductDTO> prod_List = null;
		
		ProductDB pDB = ProductDB.getInstance();//DB연동
		
		prod_List = pDB.getEndsoon();
        request.setAttribute("prod_List", prod_List);
		return "/index.jsp";
	}

}
