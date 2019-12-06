package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.LogonDBBean;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd  = request.getParameter("passwd");
		System.out.println(id);
		System.out.println(passwd);
		//사용자가 입력한 id, passwd를 가지고 인증 체크 후 값 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check= manager.userCheck(id,passwd);
		System.out.println(check);
		request.setAttribute("id", id);
		request.setAttribute("check", new Integer(check));
		return "/member/loginPro.jsp";
	}
}