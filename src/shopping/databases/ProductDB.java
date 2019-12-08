package shopping.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import shopping.bean.ProdReqDTO;
import shopping.bean.ProductDTO;

public class ProductDB {
	private static ProductDB instance = new ProductDB();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");

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
			String sql = "select * from (select row_number() over(order by end_date asc) as rownum,pro_num,title,pro_desc,price,begin_date,end_date,title_img from product where end_date>now() and begin_date<=now())c where rownum between 1 and 6";
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
					prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
					prod.setTitle_img(rs.getString("title_img"));
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

	public List<ProductDTO> getList(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> prod_List = null;
		try {
			conn = getConnection();

			String sql = "select * from (select row_number() over(order by end_date asc) as rownum,pro_num,title,pro_desc,price,begin_date,end_date,title_img from product where pro_num like ? and end_date > now() and begin_date<=now() order by end_date)c where rownum between 1 and 6";

			pstmt = conn.prepareStatement(sql);
			if ("all".contentEquals(category))
				category = "%";
			pstmt.setString(1, category + "%");
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
					prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
					prod.setTitle_img(rs.getString("title_img"));
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

	public List<ProductDTO> getMoreList(String category, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> prod_List = null;
		try {
			conn = getConnection();

			String sql = "select * from (select row_number() over(order by end_date asc) as rownum,pro_num,title,pro_desc,price,begin_date,end_date,title_img from product where pro_num like ? and end_date > now() and begin_date<=now() order by end_date)c where rownum between ? and ?";

			pstmt = conn.prepareStatement(sql);
			if ("all".contentEquals(category))
				category = "%";
			pstmt.setString(1, category + "%");
			pstmt.setInt(2, page * 3 + 1);
			pstmt.setInt(3, page * 3 + 3);
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
					prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
					prod.setTitle_img(rs.getString("title_img"));
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

	public List<ProductDTO> getprepareList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> prod_List = null;
		try {
			conn = getConnection();

			String sql = "select * from (select row_number() over(order by end_date asc) as rownum,pro_num,title,pro_desc,price,begin_date,end_date,title_img from product where end_date > now() and begin_date>now() order by end_date)c where rownum between 1 and 6";

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
					prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
					prod.setTitle_img(rs.getString("title_img"));
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

	public List<ProductDTO> getMorePrepareList(int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductDTO> prod_List = null;
		try {
			conn = getConnection();

			String sql = "select * from (select row_number() over(order by end_date asc) as rownum,pro_num,title,pro_desc,price,begin_date,end_date,title_img from product where end_date > now() and begin_date>now() order by end_date)c where rownum between ? and ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page * 3 + 1);
			pstmt.setInt(2, page * 3 + 3);
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
					prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
					prod.setTitle_img(rs.getString("title_img"));
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

	public ProductDTO getProInfo(String pro_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDTO prod = new ProductDTO();
		try {
			conn = getConnection();

			String sql = "select * from product where pro_num = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pro_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				prod.setPro_num(rs.getString("pro_num"));
				prod.setTitle(rs.getString("title"));
				prod.setPro_desc(rs.getString("pro_desc"));
				prod.setPrice(rs.getInt("price"));
				prod.setBegin_date(rs.getDate("begin_date"));
				prod.setEnd_date(rs.getDate("end_date"));
				prod.setStrEnd_date(sdf.format(rs.getDate("end_date")));
				prod.setTitle_img(rs.getString("title_img"));
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
		return prod;
	}

	public List<String> getScreenShots(String pro_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> images = new ArrayList<String>();
		try {
			conn = getConnection();

			String sql = "select loc from photos where pro_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro_num);
			rs = pstmt.executeQuery();

			while (rs.next())
				images.add(rs.getString("loc"));
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
		return images;
	}

	public ProdReqDTO getMreq(String pro_num) {
		ProdReqDTO prd = new ProdReqDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select * from pro_req where req_code = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "m"+pro_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				prd.setCpu(rs.getString("cpu"));
				prd.setDirectx(rs.getString("directx"));
				prd.setDisk_storage(rs.getString("disk_storage"));
				prd.setMem(rs.getString("mem"));
				prd.setOs(rs.getString("os"));
				prd.setVga(rs.getString("vga"));
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
		return prd;
	}
	public ProdReqDTO getHreq(String pro_num) {
		ProdReqDTO prd = new ProdReqDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select * from pro_req where req_code = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "h"+pro_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				prd.setCpu(rs.getString("cpu"));
				prd.setDirectx(rs.getString("directx"));
				prd.setDisk_storage(rs.getString("disk_storage"));
				prd.setMem(rs.getString("mem"));
				prd.setOs(rs.getString("os"));
				prd.setVga(rs.getString("vga"));
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
		return prd;
	}
}
