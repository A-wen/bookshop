package com.cou.model;

import java.sql.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CouponDAO implements CouponDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT="INSERT INTO Coupon(cou_no,COU_START,COU_END,COU_DIS,COU_EXP,MEM_NO,cou_name)VALUES(COUPON_seq.NEXTVAL,?,?,?,?,?,?)";
	
	private static final String DELETE="DELETE from Coupon where cou_no=?";
	
	private static final String UPDATE="UPDATE Coupon set COU_START=?,COU_END=?,COU_DIS=?,COU_EXP=?,MEM_NO=?,cou_name=?   where cou_no=?";
	
	private static final String GET_ALL_STMT="SELECT cou_no,to_char(COU_START,'yyyy-mm-dd') COU_START,to_char(COU_end,'yyyy-mm-dd') COU_END,COU_DIS,COU_EXP,MEM_NO,cou_name FROM coupon order by cou_no";

	private static final String GET_ONE_STMT="SELECT cou_no,to_char(COU_START,'yyyy-mm-dd') COU_START,to_char(COU_end,'yyyy-mm-dd') COU_END,COU_DIS,COU_EXP,MEM_NO,cou_name FROM coupon where cou_no=?";

	

	@Override
	public void insert(CouponVO couVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setDate(1,couVO.getCoustart());
			pstmt.setDate(2,couVO.getCouend());
			pstmt.setDouble(3,couVO.getCoudis());
			pstmt.setString(4,couVO.getCouexp());
			pstmt.setInt(5,couVO.getMemno());
			pstmt.setString(6,couVO.getCouname());
			pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(CouponVO couVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setDate(1,couVO.getCoustart());
			pstmt.setDate(2,couVO.getCouend());
			pstmt.setDouble(3,couVO.getCoudis());
			pstmt.setString(4,couVO.getCouexp());
			pstmt.setInt(5,couVO.getMemno());
			pstmt.setString(6,couVO.getCouname());
			pstmt.setInt(7,couVO.getCouno());
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer cou_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, cou_no);

			pstmt.executeUpdate();

	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public CouponVO findByPK(Integer cou_no) {
		
		CouponVO couVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cou_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				 couVO = new  CouponVO();
				 couVO.setCouno(rs.getInt("cou_no"));
				 couVO.setCoustart(rs.getDate("cou_start"));
				 couVO.setCouend(rs.getDate("cou_end"));
				 couVO.setCoudis(rs.getDouble("cou_dis"));
				 couVO.setCouexp(rs.getString("cou_exp"));
				 couVO.setMemno(rs.getInt("mem_no"));
				 couVO.setCouname(rs.getString("cou_name"));
				
			}

	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return couVO;
	}

	@Override
	public List<CouponVO> getAll() {
		
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				couVO = new  CouponVO();
				 couVO.setCouno(rs.getInt("cou_no"));
				 couVO.setCoustart(rs.getDate("cou_start"));
				 couVO.setCouend(rs.getDate("cou_end"));
				 couVO.setCoudis(rs.getDouble("cou_dis"));
				 couVO.setCouexp(rs.getString("cou_exp"));
				 couVO.setMemno(rs.getInt("mem_no"));
				 couVO.setCouname(rs.getString("cou_name"));
				list.add(couVO); // Store the row in the list
			}


			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	
	public static void main(String[] args) {

		CouponDAO dao = new CouponDAO();

		// 嚙編嚙磕
//		CouponVO couVO = new CouponVO();
//		couVO.setCoustart(java.sql.Date.valueOf("2016-10-10"));
//		couVO.setCouend(java.sql.Date.valueOf("2016-12-10"));
//		couVO.setCoudis(new Double(30));
//		couVO.setCouexp("嚙踝蕭嚙踝蕭嚙踝蕭 嚙磕嚙緯嚙踝蕭");
//		couVO.setMemno(101);
//		couVO.setCouname("嚙稽嚙踝蕭嚙踝蕭");
//		dao.insert(couVO);
		//嚙論改蕭
//		couVO.setCouno(2032);
//		couVO.setCoustart(java.sql.Date.valueOf("2016-10-10"));
//	    couVO.setCouend(java.sql.Date.valueOf("2016-12-10"));
//		couVO.setCoudis(new Double(70));
//		couVO.setCouexp("嚙踝蕭嚙踝蕭h");
//		couVO.setMemno(101);
//		couVO.setCouname("嚙稽嚙踝蕭嚙踝蕭");
//		dao.update(couVO);
		//嚙磋嚙踝蕭
//		dao.delete(2032);
		
		// 嚙範嚙踝蕭
		CouponVO couVO = dao.findByPK(2024);
		System.out.print(couVO.getCouno() + ",");
		System.out.print(couVO.getCoustart()+",");
		System.out.print(couVO.getCouend()+",");
		System.out.print(couVO.getCoudis()+",");
		System.out.print(couVO.getCouexp()+",");
		System.out.print(couVO.getMemno()+",");
		System.out.print(couVO.getCouname()+",");
		System.out.println("=====================");
		//嚙範嚙踝蕭
		List<CouponVO> list = dao.getAll();
		for (CouponVO aEmp : list) {
		
			System.out.print(aEmp.getCouno() + ",");
			System.out.print(aEmp.getCoustart()+",");
			System.out.print(aEmp.getCouend()+",");
			System.out.print(aEmp.getCoudis()+",");
			System.out.print(aEmp.getCouexp()+",");
			System.out.print(aEmp.getMemno()+",");
			System.out.print(aEmp.getCouname()+",");
			System.out.println("=====================");
		}
	}
	

}
