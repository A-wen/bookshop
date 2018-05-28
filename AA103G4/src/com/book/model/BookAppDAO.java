package com.book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BookAppDAO implements BookAppDAO_interface{
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		}catch(NamingException e){
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
		private static final String GET_BOOK_IMG ="SELECT BOOK_PIC FROM book where book_no = ?";
		private static final String GET_TOP_BOOK ="SELECT * FROM book WHERE type_no = ?";
		
		

	@Override
	public BookVO findByPrimaryKey(Integer book_no) {
		BookVO bookVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, book_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
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
		
		}catch (SQLException se) {
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
	return bookVO;
	}
	@Override
	public byte[] getBookImg(Integer book_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] photo = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOOK_IMG);

			pstmt.setInt(1, book_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				photo = rs.getBytes("book_pic");
			} 
		}catch (SQLException se) {
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
	return photo;
		
	}
	@Override
	public BookVO getOneBook(Integer book_no) {
		BookVO bookVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, book_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			
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
		}catch (SQLException se) {
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
	return bookVO;
		
	}	
	
	@Override
	public List<BookVO> getBooksByType(Integer type_no) {
			List<BookVO> bookList = new ArrayList<BookVO>();
		BookVO bookVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_BOOK);
			pstmt.setInt(1, type_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
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
				bookList.add(bookVO);
			}
	
		}catch (SQLException se) {
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
		return bookList;
	}
}
