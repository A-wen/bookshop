package com.s_gro_mem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class S_gro_memDAO implements S_gro_memDAO_interface{
	
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
				//加入讀書會成員權限預設au_no=2
				private static final String INSERT_STMT = 
					"INSERT INTO s_gro_mem (s_gro_no,mem_no,au_no) VALUES (?, ?, 2)";
				//建立讀書會順便成為社團一員
				private static final String INSERT2_STMT = 
					"INSERT INTO s_gro_mem (s_gro_no,mem_no,au_no) VALUES ( ?, ?, 1)";
				private static final String GET_ALL_STMT = 
					"SELECT s_gro_no,mem_no,au_no FROM s_gro_mem order by s_gro_no";
				private static final String GET_ONE_STMT = 
					"SELECT s_gro_no,mem_no,au_no FROM s_gro_mem where s_gro_no = ? and mem_no = ?";
				//踢掉讀書會成員或是(不同意成員加入讀書會)
				private static final String DELETE = 
					"DELETE FROM s_gro_mem where s_gro_no = ? and mem_no=?";
				private static final String UPDATE = 
					"UPDATE s_gro_mem set au_no=? where s_gro_no = ? and mem_no=?";
				//已加入的會員
				private static final String GET_MEM_IN = 
					"SELECT  mem_no FROM s_gro_mem where au_no=2 and s_gro_no = ?";
				//待加入的會員
				private static final String GET_MEM_YET = 
					"SELECT  mem_no FROM s_gro_mem where s_gro_no = ?";
				//xxx會員加入的社團
				private static final String GET_MEM_JOIN = 
					"SELECT * FROM s_gro_mem where mem_no = ?";
				private static final String GET_A_STMT = 
					"SELECT * FROM s_gro_mem  where s_gro_no=? and mem_no = ?";
				
				@Override
				public void insert(S_gro_memVO s_gro_memVO) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(INSERT_STMT);
//						System.out.println("22222222");
						pstmt.setInt(1, s_gro_memVO.getS_gro_no());
						pstmt.setInt(2, s_gro_memVO.getMem_no());
//						pstmt.setInt(3, s_gro_memVO.getAu_no());
//						System.out.println("33333333");

						pstmt.executeUpdate();
//						System.out.println("s_gro_no");
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
				public void update(S_gro_memVO s_gro_memVO) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(UPDATE);

						pstmt.setInt(1, s_gro_memVO.getAu_no());
						pstmt.setInt(2, s_gro_memVO.getS_gro_no());
						pstmt.setInt(3, s_gro_memVO.getMem_no());				

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
				public void delete(Integer s_gro_no, Integer mem_no) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(DELETE);

						pstmt.setInt(1, s_gro_no);
						pstmt.setInt(2, mem_no);
						
						pstmt.executeUpdate();
//						System.out.println("33333333333");
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
				public S_gro_memVO findByPrimaryKey(Integer s_gro_no) {

					S_gro_memVO s_gro_memVO = null;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_ONE_STMT);

						pstmt.setInt(1, s_gro_no);

						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVo 也稱為 Domain objects
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							s_gro_memVO.setAu_no(rs.getInt("au_no"));
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
					return s_gro_memVO;
				}
				
				@Override
				public List<S_gro_memVO> getAll() {
					List<S_gro_memVO> list = new ArrayList<S_gro_memVO>();
					S_gro_memVO s_gro_memVO = null;

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_ALL_STMT);
						rs = pstmt.executeQuery();
						
						while (rs.next()) {
							// empVO 也稱為 Domain objects
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							s_gro_memVO.setAu_no(rs.getInt("au_no"));
							list.add(s_gro_memVO); // Store the row in the list
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
				
				public List<S_gro_memVO> findMemJoin(Integer mem_no){
					List<S_gro_memVO> list = new ArrayList<S_gro_memVO>();
					S_gro_memVO s_gro_memVO = null;
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try{
						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_MEM_JOIN );
						pstmt.setInt(1,mem_no);
						rs = pstmt.executeQuery();
						
						while(rs.next()){
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							s_gro_memVO.setAu_no(rs.getInt("au_no"));
							list.add(s_gro_memVO);
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
						return list;		
				}
				
				public S_gro_memVO findaguy(Integer s_gro_no, Integer mem_no){
					
					S_gro_memVO s_gro_memVO = null;
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try{
						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_A_STMT);
						pstmt.setInt(1, s_gro_no);
						pstmt.setInt(2, mem_no);
						rs = pstmt.executeQuery();
						System.out.println("22222222222");
						while(rs.next()){
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							s_gro_memVO.setAu_no(rs.getInt("au_no"));
						}
//						System.out.println("s_gro_no="+s_gro_memVO.getS_gro_no());
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
						return s_gro_memVO;	
				}
				
				public List<S_gro_memVO> findMemYet(Integer s_gro_no){
					List<S_gro_memVO> list = new ArrayList<S_gro_memVO>();
					S_gro_memVO s_gro_memVO = null;
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					try{
						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_MEM_YET);
						pstmt.setInt(1, s_gro_no);
						rs = pstmt.executeQuery();
						
						while(rs.next()){
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							list.add(s_gro_memVO);
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
						return list;
				}
				
				public List<S_gro_memVO> findMemIn(Integer s_gro_no){
					List<S_gro_memVO> list = new ArrayList<S_gro_memVO>();
					S_gro_memVO s_gro_memVO = null;
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
						
					try{
						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_MEM_IN);
						pstmt.setInt(1, s_gro_no);
						
						rs = pstmt.executeQuery();
						
						while(rs.next()){
							s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setMem_no(rs.getInt("mem_no"));
							s_gro_memVO.setAu_no(rs.getInt("au_no"));
							list.add(s_gro_memVO);
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
						return list;		
				}
				
				@Override
				public void insert2 (S_gro_memVO s_gro_memVO , Connection con) {

					PreparedStatement pstmt = null;

					try {
						
						pstmt = con.prepareStatement(INSERT2_STMT);

						pstmt.setInt(1, s_gro_memVO.getS_gro_no());
						pstmt.setInt(2, s_gro_memVO.getMem_no());
//						pstmt.setInt(3, s_gro_memVO.getAu_no());
						System.out.println("11111111");
						System.out.println("22222222");

						pstmt.executeUpdate();

						// Handle any SQL errors
					} catch (SQLException se) {
						if (con != null) {
							try {
								// 3●設定於當有exception發生時之catch區塊內
								System.err.print("Transaction is being ");
								System.err.println("rolled back-由-emp");
								con.rollback();
							} catch (SQLException excep) {
								throw new RuntimeException("rollback error occured. "
										+ excep.getMessage());
							}
						}
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
					}
				}
}
