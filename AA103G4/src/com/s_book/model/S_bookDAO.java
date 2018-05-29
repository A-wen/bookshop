package com.s_book.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.s_gro_info.model.S_gro_infoVO;

import jdbc.util.jdbcUtil_CompositeQuery_S_book2;

public class S_bookDAO implements S_bookDAO_interface{
	
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
				"INSERT INTO s_book (s_book_no,s_gro_no,cre_date,end_date) VALUES (S_BOOK_SEQ.NEXTVAL, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT s_book_no,s_gro_no,to_char(cre_date,'yyyy-mm-dd') cre_date,to_char(end_date,'yyyy-mm-dd') end_date FROM s_book order by s_book_no";
			private static final String GET_ONE_STMT = 
				"SELECT s_book_no,s_gro_no,to_char(cre_date,'yyyy-mm-dd') cre_date,to_char(end_date,'yyyy-mm-dd') end_date FROM s_book where s_book_no = ?";
			private static final String DELETE = 
				"DELETE FROM s_book where s_book_no = ?";
			private static final String UPDATE = 
				"UPDATE s_book set s_gro_no=?, cre_date=?, end_date=? where s_book_no = ?";

			@Override
			public void insert(S_bookVO s_bookVO) {
				Connection con = null;
				PreparedStatement pstmt = null;
				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setInt(1, s_bookVO.getS_gro_no());
					pstmt.setDate(2, s_bookVO.getCre_date());
					pstmt.setDate(3, s_bookVO.getEnd_date());
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
			public void update(S_bookVO s_bookVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);
				
					pstmt.setInt(1, s_bookVO.getS_gro_no());
					pstmt.setDate(2, s_bookVO.getCre_date());
					pstmt.setDate(3, s_bookVO.getEnd_date());
					pstmt.setInt(4, s_bookVO.getS_book_no());
					
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
			public void delete(Integer s_book_no) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setInt(1, s_book_no);

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
			public S_bookVO findByPrimaryKey(Integer s_book_no) {

				S_bookVO s_bookVO = null;
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
						s_bookVO = new S_bookVO();
						s_bookVO.setS_book_no(rs.getInt("s_book_no"));
						s_bookVO.setS_gro_no(rs.getInt("s_gro_no"));
						s_bookVO.setCre_date(rs.getDate("cre_date"));
						s_bookVO.setEnd_date(rs.getDate("end_date"));
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
				return s_bookVO;
			}

			@Override
			public List<S_bookVO> getAll() {
				List<S_bookVO> list = new ArrayList<S_bookVO>();
				S_bookVO s_bookVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ALL_STMT);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVO 也稱為 Domain objects
						s_bookVO = new S_bookVO();
						s_bookVO.setS_book_no(rs.getInt("s_book_no"));
						s_bookVO.setS_gro_no(rs.getInt("s_gro_no"));
						s_bookVO.setCre_date(rs.getDate("cre_date"));
						s_bookVO.setEnd_date(rs.getDate("end_date"));
						list.add(s_bookVO); // Store the row in the list
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
			public List<S_bookVO> getAll(Map<String, String[]> map) {
				List<S_bookVO> list = new ArrayList<S_bookVO>();
				S_bookVO s_bookVO = null;
			
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				try {
					
					con = ds.getConnection();
					String finalSQL = "select * from s_book "
				          + jdbcUtil_CompositeQuery_S_book2.get_WhereCondition(map)
				          + "order by s_gro_no";
					pstmt = con.prepareStatement(finalSQL);
					System.out.println("●●finalSQL(by DAO) = "+finalSQL);
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						s_bookVO = new S_bookVO();
						s_bookVO.setS_book_no(rs.getInt("s_book_no"));
						s_bookVO.setS_gro_no(rs.getInt("s_gro_no"));
						s_bookVO.setCre_date(rs.getDate("cre_date"));
						s_bookVO.setEnd_date(rs.getDate("end_date"));
						list.add(s_bookVO); // Store the row in the List
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
