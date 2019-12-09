package shopping.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import shopping.bean.CartDTO;

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
		System.out.println(id + pro_num);
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

	public List<CartDTO> getCart(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartDTO> cart = new ArrayList<CartDTO>();
		System.out.println(id);
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select p.title, c.pro_num, p.title_img, p.price, c.amount from product as p, cart as c WHERE c.pro_num=p.pro_num and id=?");
			pstmt.setString(1, id);
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO tmp = new CartDTO();
				tmp.setTitle(rs.getString(1));
				tmp.setPro_num(rs.getString(2));
				tmp.setTitle_img(rs.getString(3));
				tmp.setPrice(rs.getInt(4));
				tmp.setAmount(rs.getInt(5));
				cart.add(tmp);
			}
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
		return cart;
	}
}
