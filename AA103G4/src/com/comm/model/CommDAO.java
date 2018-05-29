package com.comm.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommDAO implements CommDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO comm VALUES (COMM_SEQ.NEXTVAL, ?, ?, ?, ?, default)";
	private static final String GET_ALL_STMT = "SELECT * FROM comm order by comm_no";
	private static final String GET_ONE_STMT = "SELECT * FROM comm where comm_no = ?";
	private static final String GET_BY_BOOK = "SELECT * FROM comm where book_no = ?";
	private static final String GET_BY_MEMBER = "SELECT * FROM comm where mem_no = ?";
	private static final String DELETE = "DELETE FROM comm where comm_no = ?";
	private static final String UPDATE = "UPDATE comm set book_no=?, mem_no=?, comm_desc=?, comm_level=?, comm_date=? where comm_no = ?";

	@Override
	public void insert(CommVO commVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, commVO.getBook_no());
			pstmt.setInt(2, commVO.getMem_no());
			pstmt.setString(3, commVO.getComm_desc());
			pstmt.setInt(4, commVO.getComm_level());

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
	public void update(CommVO commVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, commVO.getBook_no());
			pstmt.setInt(2, commVO.getMem_no());
			pstmt.setString(3, commVO.getComm_desc());
			pstmt.setInt(4, commVO.getComm_level());
			pstmt.setDate(5, commVO.getComm_date());
			pstmt.setInt(6, commVO.getComm_no());

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
	public void delete(Integer comm_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, comm_no);

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
	public CommVO findByPrimaryKey(Integer comm_no) {

		CommVO commVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, comm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commVO = new CommVO();
				commVO.setComm_no(rs.getInt("comm_no"));
				commVO.setBook_no(rs.getInt("book_no"));
				commVO.setMem_no(rs.getInt("mem_no"));
				commVO.setComm_desc(rs.getString("comm_desc"));
				commVO.setComm_level(rs.getInt("comm_level"));
				commVO.setComm_date(rs.getDate("comm_date"));
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
		return commVO;
	}

	@Override
	public List<CommVO> getByBook(Integer book_no) {
		List<CommVO> list = new ArrayList<CommVO>();
		CommVO commVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BOOK);

			pstmt.setInt(1, book_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commVO = new CommVO();
				commVO.setComm_no(rs.getInt("comm_no"));
				commVO.setBook_no(rs.getInt("book_no"));
				commVO.setMem_no(rs.getInt("mem_no"));
				commVO.setComm_desc(rs.getString("comm_desc"));
				commVO.setComm_level(rs.getInt("comm_level"));
				commVO.setComm_date(rs.getDate("comm_date"));
				list.add(commVO); // Store the row in the list
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
	public List<CommVO> getByMember(Integer mem_no) {
		List<CommVO> list = new ArrayList<CommVO>();
		CommVO commVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMBER);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commVO = new CommVO();
				commVO.setComm_no(rs.getInt("comm_no"));
				commVO.setBook_no(rs.getInt("book_no"));
				commVO.setMem_no(rs.getInt("mem_no"));
				commVO.setComm_desc(rs.getString("comm_desc"));
				commVO.setComm_level(rs.getInt("comm_level"));
				commVO.setComm_date(rs.getDate("comm_date"));
				list.add(commVO);
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
	public List<CommVO> getAll() {
		List<CommVO> list = new ArrayList<CommVO>();
		CommVO commVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				commVO = new CommVO();
				commVO.setComm_no(rs.getInt("comm_no"));
				commVO.setBook_no(rs.getInt("book_no"));
				commVO.setMem_no(rs.getInt("mem_no"));
				commVO.setComm_desc(rs.getString("comm_desc"));
				commVO.setComm_level(rs.getInt("comm_level"));
				commVO.setComm_date(rs.getDate("comm_date"));
				list.add(commVO);
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