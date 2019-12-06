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

public class QnaDBBean {
	
    private static QnaDBBean instance = new QnaDBBean();
	
    //.jsp�럹�씠吏��뿉�꽌 DB�뿰�룞鍮덉씤 BoardDBBean�겢�옒�뒪�쓽 硫붿냼�뱶�뿉 �젒洹쇱떆 �븘�슂
    public static QnaDBBean getInstance() {
        return instance;
    }
    
    private QnaDBBean(){}
    
    //而ㅻ꽖�뀡��濡쒕��꽣 Connection媛앹껜瑜� �뼸�뼱�깂
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/shopping");
      return ds.getConnection();
    }
    
    //qna�뀒�씠釉붿뿉 湲��쓣 異붽� - �궗�슜�옄媛� �옉�꽦�븯�뒗 湲�
    @SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article){
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
        String sql="";
        int group_id = 1;        
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement("select max(qna_id) from qna");
            rs = pstmt.executeQuery();
            
            if (rs.next()) 
                x= rs.getInt(1);
            
            if(x > 0)
               group_id = rs.getInt(1)+1;
                   	
            // 荑쇰━瑜� �옉�꽦 :board�뀒�씠釉붿뿉 �깉濡쒖슫 �젅肄붾뱶 異붽�
            sql = "insert into qna(book_id,book_title,qna_writer,qna_content,";
		    sql += "group_id,qora,reply,reg_date) values(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article.getBook_id());
            pstmt.setString(2, article.getBook_title());
            pstmt.setString(3, article.getQna_writer());
            pstmt.setString(4, article.getQna_content());
            pstmt.setInt(5, group_id);
            pstmt.setInt(6, article.getQora());
            pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
            pstmt.executeUpdate();
            
            x = 1; //�젅肄붾뱶 異붽� �꽦怨�
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
        return x;
    }
    
    //qna�뀒�씠釉붿뿉 湲��쓣 異붽� - 愿�由ъ옄媛� �옉�꽦�븳 �떟蹂�
    @SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article, int qna_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
        String sql="";
        
        try {
            conn = getConnection();
            
            // 荑쇰━瑜� �옉�꽦 :board�뀒�씠釉붿뿉 �깉濡쒖슫 �젅肄붾뱶 異붽�
            sql = "insert into qna(book_id,book_title,qna_writer,qna_content,";
		    sql += "group_id,qora,reply,reg_date) values(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article.getBook_id());
            pstmt.setString(2, article.getBook_title());
            pstmt.setString(3, article.getQna_writer());
            pstmt.setString(4, article.getQna_content());
            pstmt.setInt(5, article.getGroup_id());
            pstmt.setInt(6, article.getQora());
            pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
            pstmt.executeUpdate();
            
            sql="update qna set reply=? where qna_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
		    pstmt.setInt(2, qna_id);
            pstmt.executeUpdate();
            
            x = 1; //�젅肄붾뱶 異붽� �꽦怨�
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
        return x;
    }
    
    //qna�뀒�씠釉붿뿉 ���옣�맂 �쟾泥닿��쓽 �닔瑜� �뼸�뼱�깂
	public int getArticleCount(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement("select count(*) from qna");
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getInt(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return x;
    }
	
	//�듅�젙 梨낆뿉 ���빐 �옉�꽦�븳 qna湲��쓽 �닔瑜� �뼸�뼱�깂
	public int getArticleCount(int book_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement("select count(*) from qna where book_id = "+book_id);
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getInt(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return x;
    }
	
		
    //吏��젙�븳 �닔�뿉 �빐�떦�븯�뒗 qna湲��쓽 �닔瑜� �뼸�뼱�깂
	public List<QnaDataBean> getArticles(int count){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<QnaDataBean> articleList=null;//湲�紐⑸줉�쓣 ���옣�븯�뒗 媛앹껜
        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            	"select * from qna order by group_id desc, qora asc");
            
            rs = pstmt.executeQuery();

            if (rs.next()) {//ResultSet�씠 �젅肄붾뱶瑜� 媛�吏�
                articleList = new ArrayList<QnaDataBean>(count);
                do{
                  QnaDataBean article= new QnaDataBean();
				  article.setQna_id(rs.getInt("qna_id")); 
				  article.setBook_id(rs.getInt("book_id"));
				  article.setBook_title(rs.getString("book_title"));
                  article.setQna_writer(rs.getString("qna_writer"));
                  article.setQna_content(rs.getString("qna_content"));
                  article.setGroup_id(rs.getInt("group_id"));
                  article.setQora(rs.getByte("qora"));
                  article.setReply(rs.getByte("reply"));
			      article.setReg_date(rs.getTimestamp("reg_date"));

				  //List媛앹껜�뿉 �뜲�씠�꽣���옣鍮덉씤 BoardDataBean媛앹껜瑜� ���옣
                  articleList.add(article);
			    }while(rs.next());
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return articleList;//List媛앹껜�쓽 �젅�띁�윴�뒪瑜� 由ы꽩
    }
	
	//�듅�젙 梨낆뿉 ���빐 �옉�꽦�븳 qna湲��쓣 吏��젙�븳 �닔 留뚰겮 �뼸�뼱�깂
	public List<QnaDataBean> getArticles(int count, int book_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<QnaDataBean> articleList=null;//湲�紐⑸줉�쓣 ���옣�븯�뒗 媛앹껜
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            	"select * from qna where book_id="+book_id+" order by group_id desc, qora asc");
            
            rs = pstmt.executeQuery();

            if (rs.next()) {//ResultSet�씠 �젅肄붾뱶瑜� 媛�吏�
                articleList = new ArrayList<QnaDataBean>(count);
                do{
                  QnaDataBean article= new QnaDataBean();
				  article.setQna_id(rs.getInt("qna_id")); 
				  article.setBook_id(rs.getInt("book_id"));
				  article.setBook_title(rs.getString("book_title"));
                  article.setQna_writer(rs.getString("qna_writer"));
                  article.setQna_content(rs.getString("qna_content"));
                  article.setGroup_id(rs.getInt("group_id"));
                  article.setQora(rs.getByte("qora"));
                  article.setReply(rs.getByte("reply"));
			      article.setReg_date(rs.getTimestamp("reg_date"));

				  //List媛앹껜�뿉 �뜲�씠�꽣���옣鍮덉씤 BoardDataBean媛앹껜瑜� ���옣
                  articleList.add(article);
			    }while(rs.next());
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return articleList;//List媛앹껜�쓽 �젅�띁�윴�뒪瑜� 由ы꽩
    }
	
    //QnA湲� �닔�젙�뤌�뿉�꽌 �궗�슜�븷 湲��쓽 �궡�슜
    public QnaDataBean updateGetArticle(int qna_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QnaDataBean article=null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
            	"select * from qna where qna_id = ?");
            pstmt.setInt(1, qna_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new QnaDataBean();
                article.setQna_id(rs.getInt("qna_id")); 
				article.setBook_id(rs.getInt("book_id"));
				article.setBook_title(rs.getString("book_title"));
                article.setQna_writer(rs.getString("qna_writer"));
                article.setQna_content(rs.getString("qna_content"));
                article.setGroup_id(rs.getInt("group_id"));
                article.setQora(rs.getByte("qora"));
                article.setReply(rs.getByte("reply"));
			    article.setReg_date(rs.getTimestamp("reg_date"));     
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{rs.close();}catch(SQLException ex){}
            if (pstmt != null) try{pstmt.close();}catch(SQLException ex){}
            if (conn != null) try{conn.close();}catch(SQLException ex){}
        }
		return article;
    }
    
    //QnA湲� �닔�젙 �닔�젙泥섎━�뿉�꽌 �궗�슜
	public int updateArticle(QnaDataBean article){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
		int x=-1;
        try {
            conn = getConnection();
            
            String sql="update qna set qna_content=? where qna_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getQna_content());
			pstmt.setInt(2, article.getQna_id());
            pstmt.executeUpdate();
			x= 1;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return x;
    }
    
    //QnA湲� �닔�젙�궘�젣泥섎━�떆 �궗�슜
	public int deleteArticle(int qna_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
        try {
			conn = getConnection();

            pstmt = conn.prepareStatement(
            	      "delete from qna where qna_id=?");
            pstmt.setInt(1, qna_id);
            pstmt.executeUpdate();
			x= 1; //湲��궘�젣 �꽦怨�
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return x;
    }
}