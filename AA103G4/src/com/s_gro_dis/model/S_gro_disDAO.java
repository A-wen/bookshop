package com.s_gro_dis.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.s_book.model.S_bookVO;
import com.s_gro_info.model.S_gro_infoVO;

import jdbc.util.jdbcUtil_CompositeQuery_S_gro_dis2;

public class S_gro_disDAO implements S_gro_disDAO_interface{
	
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
					"INSERT INTO s_gro_dis (dis_ar_no,s_gro_no,mem_no,title,dis_con,ht_date) VALUES (S_GRO_DIS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
				private static final String GET_ALL_STMT = 
					"SELECT dis_ar_no,s_gro_no,mem_no,title,dis_con,to_char(ht_date,'yyyy-mm-dd') ht_date FROM s_gro_dis order by dis_ar_no";
				private static final String GET_ONE_STMT = 
					"SELECT dis_ar_no,s_gro_no,mem_no,title,dis_con,to_char(ht_date,'yyyy-mm-dd') ht_date FROM s_gro_dis where dis_ar_no = ?";
				private static final String DELETE = 
					"DELETE FROM s_gro_dis where dis_ar_no = ?";
				private static final String UPDATE = 
					"UPDATE s_gro_dis set s_gro_no=?, mem_no=?, title=?, dis_con=?, ht_date=? where dis_ar_no = ?";				
				private static final String GET_ALL_CLUB = 
					"SELECT dis_ar_no,s_gro_no,mem_no,title,dis_con,to_char(ht_date,'yyyy-mm-dd') ht_date FROM s_gro_dis where s_gro_no=? order by ht_date";
				
				public void insert(S_gro_disVO s_gro_disVO) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(INSERT_STMT);

						pstmt.setInt(1, s_gro_disVO.getS_gro_no());
						pstmt.setInt(2, s_gro_disVO.getMem_no());
						pstmt.setString(3, s_gro_disVO.getTitle());
						pstmt.setString(4, s_gro_disVO.getDis_con());
						pstmt.setDate(5, s_gro_disVO.getHt_date());
									
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
				
				public void update(S_gro_disVO s_gro_disVO) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(UPDATE);
						
						pstmt.setInt(1, s_gro_disVO.getS_gro_no());
						pstmt.setInt(2, s_gro_disVO.getMem_no());
						pstmt.setString(3, s_gro_disVO.getTitle());
						pstmt.setString(4, s_gro_disVO.getDis_con());
						pstmt.setDate(5, s_gro_disVO.getHt_date());
						pstmt.setInt(6, s_gro_disVO.getDis_ar_no());

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
				
				public void delete(Integer dis_ar_no) {

					Connection con = null;
					PreparedStatement pstmt = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(DELETE);

						pstmt.setInt(1, dis_ar_no);

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
				
				public S_gro_disVO findByPrimaryKey(Integer dis_ar_no) {

					S_gro_disVO s_gro_disVO = null;
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_ONE_STMT);

						pstmt.setInt(1, dis_ar_no);

						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVo �]�٬� Domain objects
							s_gro_disVO = new S_gro_disVO();
							s_gro_disVO.setDis_ar_no(rs.getInt("dis_ar_no"));
							s_gro_disVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_disVO.setMem_no(rs.getInt("mem_no"));
							s_gro_disVO.setTitle(rs.getString("title"));
							s_gro_disVO.setDis_con(rs.getString("dis_con"));
							s_gro_disVO.setHt_date(rs.getDate("ht_date"));				
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
					return s_gro_disVO;
				}
				
				public List<S_gro_disVO> getAll() {
					List<S_gro_disVO> list = new ArrayList<S_gro_disVO>();
					S_gro_disVO s_gro_disVO = null;

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_ALL_STMT);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVO �]�٬� Domain objects
							s_gro_disVO = new S_gro_disVO();
							s_gro_disVO.setDis_ar_no(rs.getInt("dis_ar_no"));
							s_gro_disVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_disVO.setMem_no(rs.getInt("mem_no"));
							s_gro_disVO.setTitle(rs.getString("title"));
							s_gro_disVO.setDis_con(rs.getString("dis_con"));
							s_gro_disVO.setHt_date(rs.getDate("ht_date"));
							list.add(s_gro_disVO); // Store the row in the list
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
				public List<S_gro_disVO> getAll(Map<String, String[]> map) {
					List<S_gro_disVO> list = new ArrayList<S_gro_disVO>();
					S_gro_disVO s_gro_disVO = null;
				
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
				
					try {
						
						con = ds.getConnection();
						String finalSQL = "select * from s_gro_dis "
					          + jdbcUtil_CompositeQuery_S_gro_dis2.get_WhereCondition(map)
					          + "order by dis_ar_no";
						pstmt = con.prepareStatement(finalSQL);
						System.out.println("����finalSQL(by DAO) = "+finalSQL);
						rs = pstmt.executeQuery();
						
						
						while (rs.next()) {
							s_gro_disVO = new S_gro_disVO();
							System.out.println(finalSQL);
							s_gro_disVO.setDis_ar_no(rs.getInt("dis_ar_no"));
							s_gro_disVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_disVO.setMem_no(rs.getInt("mem_no"));
							s_gro_disVO.setTitle(rs.getString("title"));
							s_gro_disVO.setDis_con(rs.getString("dis_con"));
							s_gro_disVO.setHt_date(rs.getDate("ht_date"));
							list.add(s_gro_disVO); // Store the row in the List
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
				
				@Override
				public List<S_gro_disVO> getClub(Integer s_gro_no) {
					List<S_gro_disVO> list = new ArrayList<S_gro_disVO>();
					S_gro_disVO s_gro_disVO = null;

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;

					try {

						con = ds.getConnection();
						pstmt = con.prepareStatement(GET_ALL_CLUB);
						pstmt.setInt(1,s_gro_no);
						rs = pstmt.executeQuery();

						while (rs.next()) {
							// empVO �]�٬� Domain objects						
																												
							s_gro_disVO = new S_gro_disVO();
							s_gro_disVO.setDis_ar_no(rs.getInt("dis_ar_no"));
							s_gro_disVO.setS_gro_no(rs.getInt("s_gro_no"));
							s_gro_disVO.setMem_no(rs.getInt("mem_no"));
							s_gro_disVO.setTitle(rs.getString("title"));
							s_gro_disVO.setDis_con(rs.getString("dis_con"));
							s_gro_disVO.setHt_date(rs.getDate("ht_date"));
							list.add(s_gro_disVO); // Store the row in the list
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
