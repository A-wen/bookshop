package com.s_gro_au.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class S_gro_auDAO implements S_gro_auDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO s_gro_au (au_no,au_lev,au_state) VALUES (s_gro_au_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT au_no,au_lev,au_state FROM s_gro_au order by au_no";
	private static final String GET_ONE_STMT = 
		"SELECT au_no,au_lev,au_state FROM s_gro_au where au_no = ?";//
	private static final String DELETE = 
		"DELETE FROM s_gro_au where au_no = ?";
	private static final String UPDATE = 
		"UPDATE s_gro_au set au_lev=?, au_state=? where au_no = ?";

	@Override
	public void insert(S_gro_auVO s_gro_auVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, s_gro_auVO.getAu_no());
//			pstmt.setString(2, s_gro_auVO.getAu_lev());
//			pstmt.setString(3, s_gro_auVO.getAu_State());
			pstmt.setString(1, s_gro_auVO.getAu_lev());
			pstmt.setString(2, s_gro_auVO.getAu_state());

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
	public void update(S_gro_auVO s_gro_auVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, s_gro_auVO.getAu_lev());
			pstmt.setString(2, s_gro_auVO.getAu_state());
			pstmt.setInt(3, s_gro_auVO.getAu_no());
			pstmt.executeUpdate();

			// Handle any driver errors

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
	public void delete(Integer au_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, au_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		
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
	public S_gro_auVO findByPrimaryKey(Integer au_no) {

		S_gro_auVO s_gro_auVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, au_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				s_gro_auVO = new S_gro_auVO();
				s_gro_auVO.setAu_no(rs.getInt("au_no"));
				s_gro_auVO.setAu_lev(rs.getString("au_lev"));
				s_gro_auVO.setAu_state(rs.getString("au_state"));
			}

			// Handle any driver errors

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
		return s_gro_auVO;
	}

	@Override
	public List<S_gro_auVO> getAll() {
		List<S_gro_auVO> list = new ArrayList<S_gro_auVO>();
		S_gro_auVO s_gro_auVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				s_gro_auVO = new S_gro_auVO();
				s_gro_auVO.setAu_no(rs.getInt("au_no"));
				s_gro_auVO.setAu_lev(rs.getString("au_lev"));
				s_gro_auVO.setAu_state(rs.getString("au_state"));
				list.add(s_gro_auVO); // Store the row in the list
			}

			// Handle any driver errors
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

}

