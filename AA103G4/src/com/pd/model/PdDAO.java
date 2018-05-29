package com.pd.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class PdDAO implements PdDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO promotions_detail VALUES (promotions_detail_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT pd_no,pd_name,pd_desc,to_char(startdate,'yyyy-mm-dd') startdate,to_char(enddate,'yyyy-mm-dd') enddate,discount FROM promotions_detail order by pd_no";
	private static final String GET_ONE_STMT = "SELECT pd_no,pd_name,pd_desc,to_char(startdate,'yyyy-mm-dd') startdate,to_char(enddate,'yyyy-mm-dd') enddate,discount FROM promotions_detail where pd_no = ?";
	private static final String DELETE = "DELETE FROM promotions_detail where pd_no = ?";
	private static final String UPDATE = "UPDATE promotions_detail set pd_name=?, pd_desc=?, startdate=?, enddate=?, discount=? where pd_no = ?";

	@Override
	public void insert(PdVO pdVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pdVO.getPd_name());
			pstmt.setString(2, pdVO.getPd_desc());
			pstmt.setDate(3, pdVO.getStartdate());
			pstmt.setDate(4, pdVO.getEnddate());
			pstmt.setDouble(5, pdVO.getDiscount());

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
	public void update(PdVO pdVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pdVO.getPd_name());
			pstmt.setString(2, pdVO.getPd_desc());
			pstmt.setDate(3, pdVO.getStartdate());
			pstmt.setDate(4, pdVO.getEnddate());
			pstmt.setDouble(5, pdVO.getDiscount());
			pstmt.setInt(6, pdVO.getPd_no());
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
	public void delete(Integer pd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pd_no);

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
	public PdVO findByPrimaryKey(Integer pd_no) {

		PdVO pdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				pdVO = new PdVO();
				pdVO.setPd_no(rs.getInt("pd_no"));
				pdVO.setPd_name(rs.getString("pd_name"));
				pdVO.setPd_desc(rs.getString("pd_desc"));//
				pdVO.setStartdate(rs.getDate("startdate"));
				pdVO.setEnddate(rs.getDate("enddate"));
				pdVO.setDiscount(rs.getDouble("discount"));
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
		return pdVO;
	}

	@Override
	public List<PdVO> getAll() {
		List<PdVO> list = new ArrayList<PdVO>();
		PdVO pdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				pdVO = new PdVO();
				pdVO.setPd_no(rs.getInt("pd_no"));
				pdVO.setPd_name(rs.getString("pd_name"));
				pdVO.setPd_desc(rs.getString("pd_desc"));
				pdVO.setStartdate(rs.getDate("startdate"));
				pdVO.setEnddate(rs.getDate("enddate"));
				pdVO.setDiscount(rs.getDouble("discount"));
				list.add(pdVO); 
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
}
