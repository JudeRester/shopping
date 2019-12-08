package bookshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import shopping.bean.ProdReqDTO;

public class MngrDBBean {
	//MngrDBBean �쟾�뿭 媛앹껜 �깮�꽦 <- �븳媛쒖쓽 媛앹껜留� �깮�꽦�빐�꽌 怨듭쑀
    private static MngrDBBean instance = new MngrDBBean();
    
    //MngrDBBean媛앹껜瑜� 由ы꽩�븯�뒗 硫붿냼�뱶
    public static MngrDBBean getInstance() {
        return instance;
    }
    
    private MngrDBBean() {}
    
    //而ㅻ꽖�뀡 ���뿉�꽌 而ㅻ꽖�뀡 媛앹껜瑜� �뼸�뼱�궡�뒗 硫붿냼�뱶
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/shopping");
        return ds.getConnection();
    }
    
    //愿�由ъ옄 �씤利� 硫붿냼�뱶
    public int userCheck(String id, String passwd){
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs= null;
		int x=-1;
        
//		SHA256 sha = SHA256.getInsatnce();
		try {
            conn = getConnection();
            
//            String orgPass = passwd;
//            String shaPass = sha.getSha256(orgPass.getBytes());
        	
            pstmt = conn.prepareStatement(
              "select passwd from admin where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

			if(rs.next()){//�빐�떦 �븘�씠�뵒媛� �엳�쑝硫� �닔�뻾
				String dbpasswd= rs.getString("passwd"); 
				if(passwd.equals(dbpasswd))	//BCrypt.checkpw(shaPass,dbpasswd)
					x= 1; //�씤利앹꽦怨�
				else
					x= 0; //鍮꾨�踰덊샇 ��由�
			}else//�빐�떦 �븘�씠�뵒 �뾾�쑝硫� �닔�뻾
				x= -1;//�븘�씠�뵒 �뾾�쓬
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		System.out.println("m로그인 성공");
		return x;
		
	}
    
    //梨� �벑濡� 硫붿냼�뱶
    public void insertBook(MngrDataBean book) 
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println("insertBook넘어옴");
        try {
            conn = getConnection();
            String sql = "insert into product(pro_num,title,pro_desc,";
            sql += "price,begin_date,end_date,title_img) values (";           
            
            if(book.getKind().equals("RTS")) {
            	sql	+= "concat(?,Lpad(nextval(rts_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("FPS")) {
            	sql	+= "concat(?,Lpad(nextval(fps_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("액션")) {
            	sql	+= "concat(?,Lpad(nextval(act_seq),'4','0')),?,?,?,?,?,?)";
            }else {
            	sql	+= "concat(?,Lpad(nextval(rpg_seq),'4','0')),?,?,?,?,?,?)";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getKind()+book.getPublishing_date()+book.getGrade());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getPro_desc());
            pstmt.setInt(4, book.getPrice());
            pstmt.setDate(5, book.getBegin_date());
            pstmt.setDate(6, book.getEnd_date());
            pstmt.setString(7, book.getTitle_image());
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
        System.out.println("inert성공");
    }
    public void insertMreq(ProdReqDTO prd, MngrDataBean book) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "insert into pro_req values (";           
            if(book.getKind().equals("RTS")) {
            	sql	+= "concat(?,Lpad(Lastval(rts_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("FPS")) {
            	sql	+= "concat(?,Lpad(Lastval(fps_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("액션")) {
            	sql	+= "concat(?,Lpad(Lastval(act_seq),'4','0')),?,?,?,?,?,?)";
            }else {
            	sql	+= "concat(?,Lpad(Lastval(rpg_seq),'4','0')),?,?,?,?,?,?)";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "m"+book.getKind()+book.getPublishing_date()+book.getGrade());
            pstmt.setString(2, prd.getOs());
            pstmt.setString(3, prd.getCpu());
            pstmt.setString(4, prd.getMem());
            pstmt.setString(5, prd.getVga());
            pstmt.setString(6, prd.getDirectx());
            pstmt.setString(7, prd.getDisk_storage());
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    public void insertHreq(ProdReqDTO prd, MngrDataBean book) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = getConnection();
    		String sql = "insert into pro_req values (";           
    		if(book.getKind().equals("RTS")) {
            	sql	+= "concat(?,Lpad(Lastval(rts_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("FPS")) {
            	sql	+= "concat(?,Lpad(Lastval(fps_seq),'4','0')),?,?,?,?,?,?)";
            }else if(book.getKind().equals("액션")) {
            	sql	+= "concat(?,Lpad(Lastval(act_seq),'4','0')),?,?,?,?,?,?)";
            }else {
            	sql	+= "concat(?,Lpad(Lastval(rpg_seq),'4','0')),?,?,?,?,?,?)";
            }
            pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, "h"+book.getKind()+book.getPublishing_date()+book.getGrade());
    		pstmt.setString(2, prd.getOs());
    		pstmt.setString(3, prd.getCpu());
    		pstmt.setString(4, prd.getMem());
    		pstmt.setString(5, prd.getVga());
    		pstmt.setString(6, prd.getDirectx());
    		pstmt.setString(7, prd.getDisk_storage());
    		pstmt.executeUpdate();
    		
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (pstmt != null) 
    			try { pstmt.close(); } catch(SQLException ex) {}
    		if (conn != null) 
    			try { conn.close(); } catch(SQLException ex) {}
    	}
    }
	public int registedBookconfirm(
			String kind, String bookName, String author) 
	throws Exception {
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs= null;
		int x=-1;
        
		try {
            conn = getConnection();
            
            String sql = "select book_name from book ";
            sql += " where kind = ? and book_name = ? and author = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kind);
            pstmt.setString(2, bookName);
            pstmt.setString(3, author);
            
            rs= pstmt.executeQuery();

			if(rs.next())
				x= 1; //�빐�떦 梨낆씠 �씠誘� �벑濡앸릺�뼱 �엳�쓬
			else
				x= -1;//�빐�떦 梨낆씠 �씠誘� �벑濡앸릺�뼱 �엳吏� �븡�쓬	
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) 
				try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
	}
    
	// �쟾泥대벑濡앸맂 梨낆쓽 �닔瑜� �뼸�뼱�궡�뒗 硫붿냼�뱶
	public int getBookCount()
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement("select count(*) from product");
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getInt(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
	
	// �빐�떦 遺꾨쪟�쓽 梨낆쓽 �닔瑜� �뼸�뼱�궡�뒗 硫붿냼�뱶
	public int getBookCount(String kind)
	throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    int x=0;
	  

	    try {
	        conn = getConnection();
	        String query = "select count(*) from book where kind=" + kind;
	        pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        if (rs.next()) 
	            x= rs.getInt(1);
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        if (rs != null) 
	           try { rs.close(); } catch(SQLException ex) {}
	        if (pstmt != null) 
	           try { pstmt.close(); } catch(SQLException ex) {}
	        if (conn != null) 
	           try { conn.close(); } catch(SQLException ex) {}
	    }
	    System.out.println("getBookcount성공");
		return x;
	}
	
	//梨낆쓽 �젣紐⑹쓣 �뼸�뼱�깂
	public String getBookTitle(int pro_num){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String x="";

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement("select book_title from book where pro_num = "+pro_num);
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getString(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return x;
    }
	// 遺꾨쪟蹂꾨삉�뒗 �쟾泥대벑濡앸맂 梨낆쓽 �젙蹂대�� �뼸�뼱�궡�뒗 硫붿냼�뱶
	public List<MngrDataBean> getBooks(String kind)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MngrDataBean> bookList=null;
        System.out.println(kind);
        try {
            conn = getConnection();
            
            String sql1 = "select * from product";
            String sql2 = "select * from product";
            sql2 += "where kind = ? order by price desc";
            
            if(kind.equals("all")||kind.equals("")){
            	 pstmt = conn.prepareStatement(sql1);
            }else{
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, kind);
            }
        	rs = pstmt.executeQuery();
            
            if (rs.next()) {
                bookList = new ArrayList<MngrDataBean>();
                do{
                	MngrDataBean book= new MngrDataBean();
                     
                     book.setPro_num(rs.getString("pro_num"));
                     book.setTitle(rs.getString("title"));
                     book.setPro_desc(rs.getString("pro_desc"));
                     book.setPrice(rs.getInt("price"));
                     book.setBegin_date(rs.getDate("begin_date"));
                     book.setEnd_date(rs.getDate("end_date"));
                     book.setTitle_image(rs.getString("title_img"));
                     
                     bookList.add(book);
			    }while(rs.next());
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
        System.out.println("getbookList성공");
		return bookList;
    }
	
	// �눥�븨紐� 硫붿씤�뿉 �몴�떆�븯湲� �쐞�빐�꽌 �궗�슜�븯�뒗 遺꾨쪟蹂� �떊媛꾩콉紐⑸줉�쓣 �뼸�뼱�궡�뒗 硫붿냼�뱶
	public MngrDataBean[] getBooks(String kind,int count)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MngrDataBean bookList[]=null;
        int i=0;
        
        try {
            conn = getConnection();
            
            String sql = "select * from book where kind = ? ";
            sql += "order by reg_date desc limit ?,?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kind);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, count);
        	rs = pstmt.executeQuery();

            if (rs.next()) {
                bookList = new MngrDataBean[count];
                do{
                	MngrDataBean book= new MngrDataBean();
                    book.setPro_num(rs.getString("pro_num"));
                    book.setTitle(rs.getString("title"));
                    book.setPro_desc(rs.getString("pro_desc"));
                    book.setPrice(rs.getInt("price"));
                    book.setBegin_date(rs.getDate("begin_date"));
                    book.setEnd_date(rs.getDate("end_date"));
                    book.setTitle_image(rs.getString("title_img"));
                     
                    bookList[i]=book;
                     
                    i++;
			    }while(rs.next());
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return bookList;
    }
	
	// bookId�뿉 �빐�떦�븯�뒗 梨낆쓽 �젙蹂대�� �뼸�뼱�궡�뒗 硫붿냼�뱶濡� 
    //�벑濡앸맂 梨낆쓣 �닔�젙�븯湲� �쐞�빐 �닔�젙�뤌�쑝濡� �씫�뼱�뱾湲곗씠湲� �쐞�븳 硫붿냼�뱶
	public MngrDataBean getBook(String proNum)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MngrDataBean book=null;
        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from book where pro_num = ?");
            pstmt.setString(1, proNum);
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new MngrDataBean();
                book.setPro_num(rs.getString("pro_num"));
                book.setTitle(rs.getString("title"));
                book.setPro_desc(rs.getString("pro_desc"));
                book.setPrice(rs.getInt("price"));
                book.setBegin_date(rs.getDate("begin_date"));
                book.setEnd_date(rs.getDate("end_date"));
                book.setTitle_image(rs.getString("title_img"));
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
		return book;
    }
    
    // �벑濡앸맂 梨낆쓽 �젙蹂대�� �닔�젙�떆 �궗�슜�븯�뒗 硫붿냼�뱶
    public void updateBook(MngrDataBean book, int bookId)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql;
        
        try {
            conn = getConnection();
            
            sql = "update book set kind=?,book_title=?,book_price=?";
            sql += ",book_count=?,author=?,publishing_com=?,publishing_date=?";
            sql += ",book_image=?,book_content=?,discount_rate=?";
            sql += " where pro_num=?";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, book.getKind()+book.getPublishing_date()+book.getGrade());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getPro_desc());
            pstmt.setInt(4, book.getPrice());
            pstmt.setDate(5, book.getBegin_date());
            pstmt.setDate(6, book.getEnd_date());
            pstmt.setString(7, book.getTitle_image());
			pstmt.setInt(11, bookId);
            
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    // bookId�뿉 �빐�떦�븯�뒗 梨낆쓽 �젙蹂대�� �궘�젣�떆 �궗�슜�븯�뒗 硫붿냼�뱶
    public void deleteBook(int bookId)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        try {
			conn = getConnection();

            pstmt = conn.prepareStatement(
            	"delete from product where pro_num=?");
            pstmt.setInt(1, bookId);
            
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) 
            	try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
}