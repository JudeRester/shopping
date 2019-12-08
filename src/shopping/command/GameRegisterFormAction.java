package shopping.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameRegisterFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setAttribute("type", new Integer(0));
		System.out.println("bookregisterfromacion들어옴");
		return "/mngr/productProcess/bookRegisterForm.jsp";
	}
}