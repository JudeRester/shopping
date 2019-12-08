package shopping.command;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bookshop.bean.MngrDataBean;
import bookshop.bean.MngrDBBean;

public class GameRegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//한글 인코딩
		System.out.println("bookregisterproaction넘어옴");
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/images"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		String name = "";
		int maxSize = 50*1024*1024;  //최대 업로될 파일크기 50Mb
		
		MultipartRequest imageUp = null;

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);  
        System.out.println("realFolder 위치 : "+realFolder);
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  System.out.println("<input type=\"file\">인 모든 파라미터를 얻어냄     "+files);
			 //파일 정보가 있다면
		     while(files.hasMoreElements()){
		       //input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
		       name = (String)files.nextElement();
		   
		       //서버에 저장된 파일 이름
		       filename = imageUp.getFilesystemName(name);
		     }
		     System.out.println("서버에 저장된 파일 이름 : "+filename);
		     System.out.println("input 태그 속성 파일 이름 속성값(파라미터) : "+name);
		  }catch(Exception e){
		     e.printStackTrace();
		  }
		
		//폼으로부터 넘어온 정보중 파일이 아닌 정보를 얻어냄
		MngrDataBean book = new MngrDataBean();
		String kind = imageUp.getParameter("kind");
		String title = imageUp.getParameter("title");
		String pro_desc = imageUp.getParameter("pro_desc");
		String min_sys = imageUp.getParameter("min_sys");
		String rec_sys = imageUp.getParameter("rec_sys");
		String price = imageUp.getParameter("price");
//		String count = imageUp.getParameter("count");
		String publishing_com = imageUp.getParameter("publishing_com");
		String begin_date = imageUp.getParameter("begin_date");
		String end_date = imageUp.getParameter("end_date");
		String discount_rate = imageUp.getParameter("discount_rate");
		String grade = imageUp.getParameter("grade");
		String title_image = filename;
		//책 등록일 계산
		String year = imageUp.getParameter("publishing_year");
		String month = 
			(imageUp.getParameter("publishing_month").length()==1)?
			 "0"+ imageUp.getParameter("publishing_month"):
			 imageUp.getParameter("publishing_month");
		String day =  (imageUp.getParameter("publishing_day").length()==1)?
			 "0"+ imageUp.getParameter("publishing_day"):
			  imageUp.getParameter("publishing_day");
		book.setKind(kind);
		book.setTitle(title);
		book.setPro_desc(pro_desc);
		book.setMin_sys(min_sys);
		book.setRec_sys(rec_sys);
		book.setPrice(Integer.parseInt(price));
//		book.setCount(Short.parseShort(count));
		book.setPublishing_com(publishing_com);
		book.setPublishing_date(year+month+day);
		book.setGrade(grade);
		book.setTitle_image(title_image);
        String date = begin_date;
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date u = f.parse(date);
        java.sql.Date sBegin_date = new java.sql.Date(u.getTime());
        System.out.println(sBegin_date);
		book.setBegin_date(sBegin_date);
		
		date = end_date;
        f = new SimpleDateFormat("yyyy-MM-dd");
        u = f.parse(date);
        java.sql.Date sEnd_date = new java.sql.Date(u.getTime());
        System.out.println(sEnd_date);
		book.setEnd_date(sEnd_date);
		
//		book.setDiscount_rate(Byte.parseByte(discount_rate));

		//DB연동 - 넘어온 정보를 테이블의 레코드로 추가
		MngrDBBean bookProcess = MngrDBBean.getInstance();
		bookProcess.insertBook(book);
		
		request.setAttribute("kind", kind);
		System.out.println(kind);
		System.out.println("DBinsert성공");
		return "/mngr/productProcess/gameRegisterPro.jsp";
	}
}
