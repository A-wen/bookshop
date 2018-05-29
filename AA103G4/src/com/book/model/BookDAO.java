package com.book.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.jdbcUtil_CompositeQuery_Books;

public class BookDAO implements BookDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO book VALUES (book_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?,default)";
	private static final String GET_ALL_STMT = "SELECT * FROM book order by book_no";
	private static final String GET_ONE_STMT = "SELECT * FROM book where book_no = ?";
	private static final String GET_BY_NAME = "SELECT * FROM book where book_name like ?";
	private static final String GET_BOOK_LIST = "SELECT * FROM book where book_no in (?)";
	private static final String DELETE = "DELETE FROM book where book_no = ?";
	private static final String UPDATE = "UPDATE book set book_name= ?,book_price= ?,type_no= ?,comp_no= ?,book_qty= ?,isbn= ?,book_author= ?,book_pic= ?,book_desc= ?,saleable=? where book_no = ?";
	private static final String UPDATENoPic = "UPDATE book set book_name= ?,book_price= ?,type_no= ?,comp_no= ?,book_qty= ?,isbn= ?,book_author= ?,book_desc= ?,saleable=? where book_no = ?";

	@Override
	public void insert(BookVO bookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, bookVO.getBook_name());
			pstmt.setInt(2, bookVO.getBook_price());
			pstmt.setInt(3, bookVO.getType_no());
			pstmt.setInt(4, bookVO.getComp_no());
			pstmt.setInt(5, bookVO.getBook_qty());
			pstmt.setString(6, bookVO.getIsbn());
			pstmt.setString(7, bookVO.getBook_author());
			pstmt.setBytes(8, bookVO.getBook_pic());
			pstmt.setString(9, bookVO.getBook_desc());

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
	public void update(BookVO bookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			// OK
			pstmt.setString(1, bookVO.getBook_name());
			pstmt.setInt(2, bookVO.getBook_price());
			pstmt.setInt(3, bookVO.getType_no());
			pstmt.setInt(4, bookVO.getComp_no());
			pstmt.setInt(5, bookVO.getBook_qty());
			pstmt.setString(6, bookVO.getIsbn());
			pstmt.setString(7, bookVO.getBook_author());
			pstmt.setBytes(8, bookVO.getBook_pic());
			pstmt.setString(9, bookVO.getBook_desc());
			pstmt.setInt(10, bookVO.getSaleable());
			pstmt.setInt(11, bookVO.getBook_no());

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
	public void updateNoPic(BookVO bookVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATENoPic);
			// OK
			pstmt.setString(1, bookVO.getBook_name());
			pstmt.setInt(2, bookVO.getBook_price());
			pstmt.setInt(3, bookVO.getType_no());
			pstmt.setInt(4, bookVO.getComp_no());
			pstmt.setInt(5, bookVO.getBook_qty());
			pstmt.setString(6, bookVO.getIsbn());
			pstmt.setString(7, bookVO.getBook_author());
			pstmt.setString(8, bookVO.getBook_desc());
			pstmt.setInt(9, bookVO.getSaleable());
			pstmt.setInt(10, bookVO.getBook_no());

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
	public void delete(Integer book_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, book_no);

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
	public BookVO findByPrimaryKey(Integer book_no) {

		BookVO bookVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, book_no);

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
		return bookVO;
	}

	@Override
	public List<BookVO> getByName(String name) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_NAME);
			String str = "%" + name + "%";

			pstmt.setString(1, str);

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
	public List<BookVO> getAll() {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
	public List<BookVO> getAll(Map<String, String[]> map) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from book" 
							  + jdbcUtil_CompositeQuery_Books.get_WhereCondition(map)
							  + "order by book_no";
			pstmt = con.prepareStatement(finalSQL);
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
	public List<BookVO> getBookList(List<Integer> book_no_list) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String str = book_no_list.toString();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOOK_LIST);
			pstmt.setString(1, str.substring(1, str.length() - 1));
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
	public List<BookVO> query(Map<String, String[]> map) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "SELECT * FROM (SELECT ROWNUM RN,BOOK.* FROM BOOK " 
							  + jdbcUtil_CompositeQuery_Books.get_WhereCondition(map);
			System.out.println(finalSQL);
			pstmt = con.prepareStatement(finalSQL);
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
}
