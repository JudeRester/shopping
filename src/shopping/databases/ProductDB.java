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

import shopping.bean.ProductDTO;

public class ProductDB {
	private static ProductDB instance = new ProductDB();

	public static ProductDB getInstance() {
		return instance;
	}

	private ProductDB() {

	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/shopping");
		return ds.getConnection();
	}

	public List<ProductDTO> getEndsoon() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> prod_List = null;

		try {
			conn = getConnection();

			String sql = "select * from (select row_number() over() as rownum,pro_num,title,pro_desc,price,begin_date,end_date from product order by end_date)c where rownum between 1 and 6";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				prod_List = new ArrayList<ProductDTO>();
				do {
					ProductDTO prod = new ProductDTO();
					prod.setPro_num(rs.getString("pro_num"));
					prod.setTitle(rs.getString("title"));
					prod.setPro_desc(rs.getString("pro_desc"));
					prod.setPrice(rs.getInt("price"));
					prod.setBegin_date(rs.getDate("begin_date"));
					prod.setEnd_date(rs.getDate("end_date"));
					prod_List.add(prod);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
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
		return prod_List;
	}
}
