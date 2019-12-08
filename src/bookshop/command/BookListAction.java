package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.MngrDataBean;
import bookshop.bean.MngrDBBean;

public class BookListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<MngrDataBean> gameList = null;
		String kind = request.getParameter("kind");
		int count = 0;
		System.out.println("bookList의 kind변수 : "+kind);
		//DB연동 - 전체 상품의 수를 얻어냄
		MngrDBBean bookProcess = MngrDBBean.getInstance();
        count = bookProcess.getBookCount(); 
        System.out.println("전체 상품수 얻어냄 : "+count);
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 bookList에 저장
        	gameList = bookProcess.getBooks(kind);
        	//bookList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("bookList", gameList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("kind", kind);
        request.setAttribute("type", new Integer(0));
        System.out.println("뷰에서 사용할 속성 : "+new Integer(count)+"/"+kind+"/"+new Integer(0));
		return "/mngr/productProcess/bookList.jsp";
	}
}