package shopping.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.bean.LoginDTO;
import shopping.databases.LoginDB;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		LoginDTO member = new LoginDTO();
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
        String date = request.getParameter("birth");
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date u = f.parse(date);
        java.sql.Date s = new java.sql.Date(u.getTime());
        System.out.println(s);
        member.setBirth(s);
		member.setAddress(request.getParameter("address"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
         
		//�쉶�썝媛��엯泥섎━
        LoginDB dbPro = LoginDB.getInstance();
        dbPro.insertMember(member);
		
		return "/member/registerPro.jsp";
	}

}
