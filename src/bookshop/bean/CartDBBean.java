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

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();
	   
    public static CartDBBean getInstance() {
    	return instance;
    }
   
    private CartDBBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/shopping");
        return ds.getConnection();
    }
    
    //[�옣諛붽뎄�땲�뿉 �떞湲�]瑜� �겢由��븯硫� �닔�뻾�릺�뒗 寃껋쑝濡� cart �뀒�씠釉붿뿉 �깉濡쒖슫 �젅肄붾뱶瑜� 異붽�
    public void insertCart(CartDataBean cart) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        String sql="";
        
        try {
            conn = getConnection();
            sql = "insert into cart (book_id, buyer," +
            		"book_title,buy_price,buy_count,book_image) " +
            		"values (?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, cart.getBook_id());
            pstmt.setString(2, cart.getBuyer());
            pstmt.setString(3, cart.getBook_title());
            pstmt.setInt(4, cart.getBuy_price());
            pstmt.setByte(5, cart.getBuy_count());
            pstmt.setString(6, cart.getBook_image());
            
            pstmt.executeUpdate();
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
            if (pstmt != null) 
            	try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) 
            	try { conn.close(); } catch(SQLException ex) {}
        }
    }
    
    //id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶�쓽 �닔瑜� �뼸�뼱�궡�뒗 硫붿냼�뱶
    public int getListCount(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
            
            pstmt = conn.prepareStatement(
            		"select count(*) from cart where buyer=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
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
		return x;
    }

	 
     //id�뿉 �빐�떦�븯�뒗 �젅肄붾뱶�쓽 紐⑸줉�쓣 �뼸�뼱�궡�뒗 硫붿냼�뱶
     public List<CartDataBean> getCart(String id, int count) throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         CartDataBean cart=null;
         String sql = "";
         List<CartDataBean> lists = null;
         
         try {
        	 conn = getConnection();
        	 
        	 sql = "select * from cart where buyer = ?";
             pstmt = conn.prepareStatement(sql);
             
             pstmt.setString(1, id);
             rs = pstmt.executeQuery();
             
             lists = new ArrayList<CartDataBean>(count);

             while (rs.next()) {
            	 cart = new CartDataBean();
            	 
            	 cart.setCart_id(rs.getInt("cart_id"));
            	 cart.setBook_id(rs.getInt("book_id"));
            	 cart.setBook_title(rs.getString("book_title"));
            	 cart.setBuy_price(rs.getInt("buy_price"));
            	 cart.setBuy_count(rs.getByte("buy_count")); 
            	 cart.setBook_image(rs.getString("book_image"));
            	 
            	 lists.add(cart);
			 }
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (rs != null) 
            	 try { rs.close(); } catch(SQLException ex) {}
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
		 return lists;
     }
   
     //�옣諛붽뎄�땲�뿉�꽌 �닔�웾�쓣 �닔�젙�떆 �떎�뻾�릺�뒗 硫붿냼�뱶
     public void updateCount(int cart_id, byte count) throws Exception {
    	 Connection conn = null;
         PreparedStatement pstmt = null;
       
         try {
        	 conn = getConnection();
            
             pstmt = conn.prepareStatement(
               "update cart set buy_count=? where cart_id=?");
             pstmt.setByte(1, count);
             pstmt.setInt(2, cart_id);
                        
             pstmt.executeUpdate();
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
     }
   
     //�옣諛붽뎄�땲�뿉�꽌 cart_id�뿉���븳 �젅肄붾뱶瑜� �궘�젣�븯�뒗 硫붿냼�뱶
     public void deleteList(int cart_id) throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
               
         try {
			 conn = getConnection();

             pstmt = conn.prepareStatement(
           	   "delete from cart where cart_id=?");
             pstmt.setInt(1, cart_id);
             
             pstmt.executeUpdate();
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
     }
     
     //id�뿉 �빐�떦�븯�뒗 紐⑤뱺 �젅肄붾뱶瑜� �궘�젣�븯�뒗 硫붿냼�뱶濡� [�옣諛붽뎄�땲 鍮꾩슦湲�]�떒異붾�� �겢由��떆 �떎�뻾�맂�떎.
     public void deleteAll(String id) throws Exception {
         Connection conn = null;
         PreparedStatement pstmt = null;
                  
         try {
			 conn = getConnection();

             pstmt = conn.prepareStatement(
               "delete from cart where buyer=?");
             pstmt.setString(1, id);
             
             pstmt.executeUpdate();
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
             if (pstmt != null) 
            	 try { pstmt.close(); } catch(SQLException ex) {}
             if (conn != null) 
            	 try { conn.close(); } catch(SQLException ex) {}
         }
     }
}
