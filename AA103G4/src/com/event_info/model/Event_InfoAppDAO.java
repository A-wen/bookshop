package com.event_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.book.model.BookVO;

public class Event_InfoAppDAO implements Event_InfoAppDAO_Interface{
	
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	//定義SQL指令
		private static final String INSERT_STMT = 
				"INSERT INTO EVENT_INFO VALUES(EVENT_INFO_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		private static final String UPDATE_STMT =
				"UPDATE EVENT_INFO SET E_NAME=?, E_STAT=?,E_INTRO=? ,E_DESC=?, E_DATE=?, E_LOC=?, E_ADDR=?, E_LIMIT=?, E_IMG=? WHERE E_NO = ? ";
		private static final String DELETE_STMT =
				"UPDATE EVENT_INFO SET E_STAT=5 WHERE E_NO = ?";
		private static final String SELECT_ALL_STMT =
			//	"SELECT E_NO,G_NO,E_NAME,E_STAT,E_INTRO,E_DATE,E_LOC,E_ADDR,E_LIMIT,E_IMG FROM EVENT_INFO ORDER BY E_NO";
		"SELECT * FROM EVENT_INFO ORDER BY E_NO";
		private static final String SELECT_BY_E_NO_STMT =
				"SELECT E_NO,G_NO,E_NAME,E_STAT,E_INTRO,E_DESC,E_DATE,E_LOC,E_ADDR,E_LIMIT,E_IMG FROM EVENT_INFO WHERE E_NO = ? AND E_STAT!=5";	
		
	@Override
	public void insert(Event_InfoVO event_InfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
//			pstmt.setInt(1, event_InfoVO.getG_No());
			pstmt.setString(2, event_InfoVO.getE_Name());
//			pstmt.setInt(3, event_InfoVO.getE_Stat());
			pstmt.setString(4, event_InfoVO.getE_Intro());
			pstmt.setString(5, event_InfoVO.getE_Desc());
			pstmt.setTimestamp(6, event_InfoVO.getE_Date());
			pstmt.setString(7, event_InfoVO.getE_Loc());
			pstmt.setString(8, event_InfoVO.getE_Addr());
			pstmt.setInt(9, event_InfoVO.getE_Limit());
			pstmt.setString(10, event_InfoVO.getE_Img()); //不上傳圖 給網址
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
		

	@Override
	public void update(Event_InfoVO event_InfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			// OK
			pstmt.setInt(1, event_InfoVO.getE_No());
//			pstmt.setInt(2, event_InfoVO.getG_No());
			pstmt.setString(3, event_InfoVO.getE_Name());
//			pstmt.setInt(4, event_InfoVO.getE_Stat());
			pstmt.setString(5, event_InfoVO.getE_Intro());
			pstmt.setString(6, event_InfoVO.getE_Desc());
			pstmt.setTimestamp(7, event_InfoVO.getE_Date());
			pstmt.setString(8, event_InfoVO.getE_Loc());
			pstmt.setString(9, event_InfoVO.getE_Addr());
			pstmt.setString(10, event_InfoVO.getE_Img()); //不上傳圖 給網址

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(Integer e_No) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, e_No);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


	@Override
	public Event_InfoVO findById(Integer e_No) {
		Event_InfoVO event_InfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_E_NO_STMT);

			pstmt.setInt(1, e_No);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				event_InfoVO = new Event_InfoVO();
				event_InfoVO.setE_No(rs.getInt("e_No"));
//				event_InfoVO.setG_No(rs.getInt("g_No"));
				event_InfoVO.setE_Name(rs.getString("e_Name"));
//				event_InfoVO.setE_Stat(rs.getInt("e_Stat"));
				event_InfoVO.setE_Intro(rs.getString("e_Intro"));
				event_InfoVO.setE_Desc(rs.getString("e_Desc"));
				event_InfoVO.setE_Date(rs.getTimestamp("e_Date"));
				event_InfoVO.setE_Loc(rs.getString("e_Loc"));
				event_InfoVO.setE_Addr(rs.getString("e_Addr"));
				event_InfoVO.setE_Limit(rs.getInt("e_Limit"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return event_InfoVO;
	
	}

	@Override
	public List<Event_InfoVO> getAll() {
		List<Event_InfoVO> list = new ArrayList<Event_InfoVO>();
		Event_InfoVO event_InfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event_InfoVO = new Event_InfoVO();
				event_InfoVO.setE_No(rs.getInt("e_No"));
//				event_InfoVO.setG_No(rs.getInt("g_No"));
				event_InfoVO.setE_Name(rs.getString("e_Name"));
//				event_InfoVO.setE_Stat(rs.getInt("e_Stat"));
				event_InfoVO.setE_Intro(rs.getString("e_Intro"));
				event_InfoVO.setE_Desc(rs.getString("e_Desc"));
				event_InfoVO.setE_Date(rs.getTimestamp("e_Date"));
				event_InfoVO.setE_Loc(rs.getString("e_Loc"));
				event_InfoVO.setE_Addr(rs.getString("e_Addr"));
				event_InfoVO.setE_Limit(rs.getInt("e_Limit"));
				list.add(event_InfoVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public byte[] getImage(Integer e_No) {
		String sql = "SELECT e_image FROM EVENT_INFO WHERE e_No = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] image = null;
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(SELECT_ALL_STMT);
			rs = ps.executeQuery();
			
			ps.setInt(1, e_No);
			rs = ps.executeQuery();
			if (rs.next()) {
				image = rs.getBytes(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return image;
	}

}
