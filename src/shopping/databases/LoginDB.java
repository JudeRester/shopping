package shopping.databases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import shopping.bean.LoginDTO;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class LoginDB {
	private static LoginDB instance = new LoginDB();

	public static LoginDB getInstance() {
		return instance;
	}

	private LoginDB() {
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/shopping");
		System.out.println("DB connected");
		return ds.getConnection();
	}

	public void insertMember(LoginDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			System.out.println(shaPass);
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
			System.out.println(bcPass);

			pstmt = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, member.getName());
			pstmt.setDate(4, (Date) member.getBirth());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	}
	
	 public int login(String id, String passwd){
			Connection conn = null;
	        PreparedStatement pstmt = null;
			ResultSet rs= null;
			int x=-1;
	        
			SHA256 sha = SHA256.getInsatnce();
			try {
	            conn = getConnection();
	            
	            String orgPass = passwd;
	            String shaPass = sha.getSha256(orgPass.getBytes());
	        	
	            pstmt = conn.prepareStatement(
	            	"select passwd from users where id = ?");
	            pstmt.setString(1, id);
	            rs= pstmt.executeQuery();

				if(rs.next()){
					String dbpasswd= rs.getString("passwd"); 
					if(BCrypt.checkpw(shaPass,dbpasswd))
						x= 1;
					else
						x= 0;
				}else
					x= -1;
				
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
		}
	 @SuppressWarnings("resource")
		public int deleteMember(String id, String passwd){
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs= null;
	        int x=-1;
	        
	        SHA256 sha = SHA256.getInsatnce();
	        try {
				conn = getConnection();
				
				String orgPass = passwd;
	            String shaPass = sha.getSha256(orgPass.getBytes());

	            pstmt = conn.prepareStatement(
	            	"select passwd from users where id = ?");
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	            
				if(rs.next()){
					String dbpasswd= rs.getString("passwd"); 
					if(BCrypt.checkpw(shaPass,dbpasswd)){
						pstmt = conn.prepareStatement(
	            	      "delete from users where id=?");
	                    pstmt.setString(1, id);
	                    pstmt.executeUpdate();
						x= 1;//�쉶�썝�깉�눜泥섎━ �꽦怨�
					}else
						x= 0;//�쉶�썝�깉�눜 泥섎━ �떎�뙣
				}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
	    }
}
