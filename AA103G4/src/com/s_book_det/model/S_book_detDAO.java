package com.s_book_det.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.s_book.model.S_bookVO;

import jdbc.util.jdbcUtil_CompositeQuery_S_book_det2;

public class S_book_detDAO implements S_book_detDAO_interface{
	
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
			
			private static final String INSERT_STMT = 
					"INSERT INTO s_book_det (s_book_no,book_no) VALUES (?, ?)";
			private static final String GET_ALL_STMT = 
					"SELECT s_book_no,book_no FROM s_book_det order by s_book_no";
			private static final String GET_ONE_STMT = 
					"SELECT s_book_no,book_no FROM s_book_det where s_book_no = ? and book_no = ?";
			private static final String DELETE = 
					"DELETE FROM s_book_det where s_book_no=? and book_no=?";
			
			@Override
			public void insert(S_book_detVO s_book_detVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setInt(1, s_book_detVO.getS_book_no());
					pstmt.setInt(2, s_book_detVO.getBook_no());
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
			public void delete(Integer s_book_no, Integer book_no) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setInt(1, s_book_no);
					pstmt.setInt(2, book_no);

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
			public S_book_detVO findByPrimaryKey(Integer s_book_no) {

				S_book_detVO s_book_detVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setInt(1, s_book_no);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						s_book_detVO = new S_book_detVO();
						s_book_detVO.setS_book_no(rs.getInt("s_book_no"));
						s_book_detVO.setBook_no(rs.getInt("book_no"));
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
				return s_book_detVO;
			}
			
			@Override
			public List<S_book_detVO> getAll() {
				List<S_book_detVO> list = new ArrayList<S_book_detVO>();
				S_book_detVO s_book_detVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						// empVO 也稱為 Domain objects
						s_book_detVO = new S_book_detVO();
						s_book_detVO.setS_book_no(rs.getInt("s_book_no"));
						s_book_detVO.setBook_no(rs.getInt("book_no"));
						list.add(s_book_detVO); // Store the row in the list
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
			
			@Override
			public List<S_book_detVO> getAll(Map<String, String[]> map) {
				List<S_book_detVO> list = new ArrayList<S_book_detVO>();
				S_book_detVO s_book_detVO = null;
			
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				try {
					
					con = ds.getConnection();
					String finalSQL = "select * from s_book_det "
				          + jdbcUtil_CompositeQuery_S_book_det2.get_WhereCondition(map)
				          + "order by s_book_no";
					pstmt = con.prepareStatement(finalSQL);
					System.out.println("●●finalSQL(by DAO) = "+finalSQL);
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						s_book_detVO = new S_book_detVO();
						s_book_detVO.setS_book_no(rs.getInt("s_book_no"));
						s_book_detVO.setBook_no(rs.getInt("book_no"));
						list.add(s_book_detVO); // Store the row in the List
					}
			
					// Handle any SQL errors
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
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
