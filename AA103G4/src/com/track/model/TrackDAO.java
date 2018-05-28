package com.track.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TrackDAO implements TrackDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO track VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM track order by mem_no";
	private static final String GET_ONE_STMT = "SELECT * FROM track where mem_no = ? and book_no = ?";
	private static final String GET_BY_MEM_STMT = "SELECT * FROM track where mem_no = ?";
	private static final String GET_BY_BOOK_STMT = "SELECT * FROM track where book_no = ? ";
	private static final String DELETE = "DELETE FROM track where mem_no = ? and book_no = ?";

	@Override
	public void insert(TrackVO trackVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, trackVO.getMem_no());
			pstmt.setInt(2, trackVO.getBook_no());

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
	public TrackVO findByPrimaryKey(Integer mem_no, Integer book_no) {
		TrackVO trackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, book_no);

			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trackVO = new TrackVO();
				trackVO.setMem_no(rs.getInt("mem_no"));
				trackVO.setBook_no(rs.getInt("book_no"));
			}
			
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
		return trackVO;
	}
	
	@Override
	public void delete(Integer mem_no, Integer book_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, book_no);

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
	public List<TrackVO> getByMember(Integer mem_no) {
		List<TrackVO> list = new ArrayList<TrackVO>();
		TrackVO trackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEM_STMT);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				trackVO = new TrackVO();
				trackVO.setMem_no(rs.getInt("mem_no"));
				trackVO.setBook_no(rs.getInt("book_no"));
				list.add(trackVO); 
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
	public List<TrackVO> getByBook(Integer book_no) {
		List<TrackVO> list = new ArrayList<TrackVO>();
		TrackVO trackVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_BOOK_STMT);

			pstmt.setInt(1, book_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				trackVO = new TrackVO();
				trackVO.setMem_no(rs.getInt("mem_no"));
				trackVO.setBook_no(rs.getInt("book_no"));
				list.add(trackVO); 
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
	public List<TrackVO> getAll() {
		List<TrackVO> list = new ArrayList<TrackVO>();
		TrackVO trackVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trackVO = new TrackVO();
				trackVO.setMem_no(rs.getInt("mem_no"));
				trackVO.setBook_no(rs.getInt("book_no"));
				list.add(trackVO); 
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