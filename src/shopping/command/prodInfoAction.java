package shopping.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.ProdReqDTO;
import shopping.bean.ProductDTO;
import shopping.databases.ProductDB;

public class prodInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ProductDTO prod = null;
		String pro_num = request.getParameter("pro_num");
		ProdReqDTO mprd = null;
		ProdReqDTO hprd = null;
		List<String> images = null;
		ProductDB pDB = ProductDB.getInstance();
		prod = pDB.getProInfo(pro_num);
		images = pDB.getScreenShots(pro_num);
		mprd = pDB.getMreq(pro_num);
		hprd = pDB.getHreq(pro_num);
		
		request.setAttribute("prod", prod);
		request.setAttribute("images", images);
		request.setAttribute("mprd", mprd);
		request.setAttribute("hprd", hprd);
		return "/products/prod_info.jsp";
	}

}
