package com.booktype.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.book.model.BookVO;

public class BooktypeDAO implements BooktypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO booktype VALUES (BOOKTYPE_SEQ.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM booktype order by type_no";
	private static final String GET_ONE_STMT = "SELECT * FROM booktype where type_no = ?";
	private static final String DELETE = "DELETE FROM booktype where type_no = ?";
	private static final String UPDATE = "UPDATE booktype set type_name=? where type_no = ?";
	private static final String GET_BOOK_BY_TYPE = "SELECT * FROM book where type_no = ? order by book_no";

	@Override
	public void insert(BooktypeVO booktypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, booktypeVO.getType_name());

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
	public void update(BooktypeVO booktypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, booktypeVO.getType_name());
			pstmt.setInt(2, booktypeVO.getType_no());
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
	public void delete(Integer type_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, type_no);
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
	public BooktypeVO findByPrimaryKey(Integer type_no) {

		BooktypeVO typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				typeVO = new BooktypeVO();
				typeVO.setType_no(rs.getInt("type_no"));
				typeVO.setType_name(rs.getString("type_name"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return typeVO;
	}

	@Override
	public List<BookVO> getBookBytype(Integer type_no) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOOK_BY_TYPE);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setBook_no(rs.getInt("book_no"));
				bookVO.setBook_name(rs.getString("book_name"));
				bookVO.setBook_price(rs.getInt("book_price"));
				bookVO.setType_no(rs.getInt("type_no"));
				bookVO.setComp_no(rs.getInt("comp_no"));
				bookVO.setBook_qty(rs.getInt("book_qty"));
				bookVO.setIsbn(rs.getString("isbn"));
				bookVO.setBook_author(rs.getString("book_author"));
				bookVO.setBook_pic(rs.getBytes("book_pic"));
				bookVO.setBook_desc(rs.getString("book_desc"));
				bookVO.setSaleable(rs.getInt("saleable"));
				list.add(bookVO);
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
	public List<BooktypeVO> getAll() {
		List<BooktypeVO> list = new ArrayList<BooktypeVO>();
		BooktypeVO booktypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				booktypeVO = new BooktypeVO();
				booktypeVO.setType_no(rs.getInt("type_no"));
				booktypeVO.setType_name(rs.getString("type_name"));

				list.add(booktypeVO);
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