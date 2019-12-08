package shopping.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDB {
	private static CartDB instance = new CartDB();

	public static CartDB getInstance() {
		return instance;
	}

	private CartDB() {
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/shopping");
		System.out.println("DB connected");
		return ds.getConnection();
	}

	public void insertCart(String id, String pro_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(id+pro_num);
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"INSERT into cart VALUES(?,?,1) on DUPLICATE KEY UPDATE amount = (SELECT amount FROM cart AS a WHERE id=? AND pro_num=?)+1");
			pstmt.setString(1, id);
			pstmt.setString(2, pro_num);
			pstmt.setString(3, id);
			pstmt.setString(4, pro_num);
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}
}
