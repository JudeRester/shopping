package shopping.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.databases.LoginDB;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		  
		//주어진 id의 중복여부를 체크해 값을 반환.
		LoginDB manager = LoginDB.getInstance();
		int check= manager.confirmId(id);
		request.setAttribute("check", check);
		return "/member/confirmId.jsp";
	}
}