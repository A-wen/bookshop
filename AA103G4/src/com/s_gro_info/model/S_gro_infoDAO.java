package com.s_gro_info.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import jdbc.util.jdbcUtil_CompositeQuery_S_gro_info2;
import util.HibernateUtil;

import com.s_gro_mem.model.*;

public class S_gro_infoDAO implements S_gro_infoDAO_interface{
	
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
						"INSERT INTO s_gro_info (s_gro_no,s_gro_name,s_con,mem_no,cs_no,cre_date,s_gro_sta) VALUES (S_GRO_INFO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
					private static final String GET_ALL_STMT = 
						"SELECT s_gro_no,s_gro_name,s_con,mem_no,cs_no,to_char(cre_date,'yyyy-mm-dd') cre_date,s_gro_sta FROM s_gro_info order by s_gro_no";
					private static final String GET_ONE_STMT = 
						"SELECT s_gro_no,s_gro_name,s_con,mem_no,cs_no,to_char(cre_date,'yyyy-mm-dd') cre_date,s_gro_sta FROM s_gro_info where s_gro_no = ?";
					private static final String GET_ONE_BY_ID = 
						"SELECT s_gro_no,s_gro_name,s_con,mem_no,cs_no,to_char(cre_date,'yyyy-mm-dd') cre_date,s_gro_sta FROM s_gro_info where s_gro_no = ?";
					//讀書會成員檢舉讀書會
//					private static final String GET_info_STMT =
//						"SELECT s_gro_no, s_gro_name, s_gro_name from s_gro_info where mem_no = ?";
					//尚未加入的讀書會
					private static final String GET_CLUB = 
						"SELECT DISTINCT s_gro_no,s_gro_name,s_con,mem_no,cs_no,to_char(cre_date,'yyyy-mm-dd') cre_date,s_gro_sta "
						+ "FROM s_gro_info  where s_gro_no not in (select s_gro_no from s_gro_mem where mem_no = ?)";
					private static final String DELETE = 
						"DELETE FROM s_gro_info where s_gro_no = ?";
					private static final String UPDATE = 
						"UPDATE s_gro_info set s_gro_name=?, s_con=?, mem_no=?, cs_no=?, s_gro_sta=? where s_gro_no = ?";
					private static final String GET_S_gro_mem_ByS_gro_no_STMT = 
						"SELECT s_gro_no,mem_no,au_no FROM s_gro_mem where s_gro_no = ? order by mem_no";
					
//					public void insert(S_gro_infoVO s_gro_infoVO) {
//
//						Connection con = null;
//						PreparedStatement pstmt = null;
//
//						try {
//
//							con = ds.getConnection();
//							pstmt = con.prepareStatement(INSERT_STMT);
//
//							pstmt.setString(1, s_gro_infoVO.getS_gro_name());
//							pstmt.setString(2, s_gro_infoVO.getS_con());
//							pstmt.setInt(3, s_gro_infoVO.getMem_no());
//							pstmt.setInt(4, s_gro_infoVO.getCs_no());
//							pstmt.setDate(5, s_gro_infoVO.getCre_date());
//							pstmt.setString(6, s_gro_infoVO.getS_gro_sta());
//							System.out.println("44444444444");
//							pstmt.executeUpdate();
//							System.out.println("55555555555");
//							// Handle any SQL errors
//						} catch (SQLException se) {
//							throw new RuntimeException("A database error occured. "
//									+ se.getMessage());
//							// Clean up JDBC resources
//						} finally {
//							if (pstmt != null) {
//								try {
//									pstmt.close();
//								} catch (SQLException se) {
//									se.printStackTrace(System.err);
//								}
//							}
//							if (con != null) {
//								try {
//									con.close();
//								} catch (Exception e) {
//									e.printStackTrace(System.err);
//								}
//							}
//						}
//					}
					
					public void update(S_gro_infoVO s_gro_infoVO) {

						Connection con = null;
						PreparedStatement pstmt = null;

						try {

							con = ds.getConnection();
							pstmt = con.prepareStatement(UPDATE);

							pstmt.setString(1, s_gro_infoVO.getS_gro_name());
							pstmt.setString(2, s_gro_infoVO.getS_con());
							pstmt.setInt(3, s_gro_infoVO.getMem_no());
							pstmt.setInt(4, s_gro_infoVO.getCs_no());
							pstmt.setString(5, s_gro_infoVO.getS_gro_sta());
							pstmt.setInt(6, s_gro_infoVO.getS_gro_no());

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
					
					public void delete(Integer s_gro_no) {

						Connection con = null;
						PreparedStatement pstmt = null;

						try {

							con = ds.getConnection();
							pstmt = con.prepareStatement(DELETE);

							pstmt.setInt(1, s_gro_no);

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
					
					public S_gro_infoVO findByPrimaryKey(Integer s_gro_no) {

						S_gro_infoVO s_gro_infoVO = null;
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
								s_gro_infoVO = new S_gro_infoVO();
								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
								s_gro_infoVO.setS_con(rs.getString("s_con"));
								s_gro_infoVO.setMem_no(rs.getInt("mem_no"));
								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
								s_gro_infoVO.setCre_date(rs.getDate("cre_date"));
								s_gro_infoVO.setS_gro_sta(rs.getString("s_gro_sta"));
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
						return s_gro_infoVO;
					}
					
					public S_gro_infoVO findByID(Integer s_gro_no){
						S_gro_infoVO s_gro_infoVO = null;
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try{
							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_ONE_BY_ID);
							
							pstmt.setInt(1, s_gro_no);
							
							rs = pstmt.executeQuery();
							
							while(rs.next()){
								s_gro_infoVO = new S_gro_infoVO();
								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
								s_gro_infoVO.setS_con(rs.getString("s_con"));
								s_gro_infoVO.setMem_no(rs.getInt("mem_no"));
								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
								s_gro_infoVO.setCre_date(rs.getDate("cre_date"));
								s_gro_infoVO.setS_gro_sta(rs.getString("s_gro_sta"));
							}
							
						}catch(SQLException se){
							throw new RuntimeException("A database error occured. "
									+ se.getMessage());
						}finally{
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
						return s_gro_infoVO;
					}
					
					public List<S_gro_infoVO> getAll() {
						List<S_gro_infoVO> list = new ArrayList<S_gro_infoVO>();
						S_gro_infoVO s_gro_infoVO = null;

						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;

						try {

							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_ALL_STMT);
							rs = pstmt.executeQuery();

							while (rs.next()) {
								// empVO 也稱為 Domain objects
								s_gro_infoVO = new S_gro_infoVO();
								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
								s_gro_infoVO.setS_con(rs.getString("s_con"));
								s_gro_infoVO.setMem_no(rs.getInt("mem_no"));
								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
								s_gro_infoVO.setCre_date(rs.getDate("cre_date"));
								s_gro_infoVO.setS_gro_sta(rs.getString("s_gro_sta"));
								list.add(s_gro_infoVO); // Store the row in the list
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
					public List<S_gro_infoVO> getAll(Map<String, String[]> map) {
						List<S_gro_infoVO> list = new ArrayList<S_gro_infoVO>();
						S_gro_infoVO s_gro_infoVO = null;
					
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
					
						try {
							
							con = ds.getConnection();
							String finalSQL = "select * from s_gro_info "
						          + jdbcUtil_CompositeQuery_S_gro_info2.get_WhereCondition(map)
						          + "order by s_gro_no";
							pstmt = con.prepareStatement(finalSQL);
							System.out.println("●●finalSQL(by DAO) = "+finalSQL);
							rs = pstmt.executeQuery();
					
							while (rs.next()) {
								s_gro_infoVO = new S_gro_infoVO();
								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
								s_gro_infoVO.setS_con(rs.getString("s_con"));
								s_gro_infoVO.setMem_no(rs.getInt("mem_no"));
								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
								s_gro_infoVO.setCre_date(rs.getDate("cre_date"));
								s_gro_infoVO.setS_gro_sta(rs.getString("s_gro_sta"));
								list.add(s_gro_infoVO); // Store the row in the List
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
					//讀書會成員檢舉讀書會
//					@Override
//					public S_gro_infoVO findinfo(Integer mem_no) {
//
//						S_gro_infoVO s_gro_infoVO = null;
//						Connection con = null;
//						PreparedStatement pstmt = null;
//						ResultSet rs = null;
//
//						try {
//
//							con = ds.getConnection();
//							pstmt = con.prepareStatement(GET_info_STMT);
//
//							
//							pstmt.setInt(1, mem_no);
//
//							rs = pstmt.executeQuery();
//
//							while (rs.next()) {
//								// empVo 也稱為 Domain objects
//								s_gro_infoVO = new S_gro_infoVO();
//								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
//								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
//								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
//							}
//
//							// Handle any driver errors
//						} catch (SQLException se) {
//							throw new RuntimeException("A database error occured. "
//									+ se.getMessage());
//							// Clean up JDBC resources
//						} finally {
//							if (rs != null) {
//								try {
//									rs.close();
//								} catch (SQLException se) {
//									se.printStackTrace(System.err);
//								}
//							}
//							if (pstmt != null) {
//								try {
//									pstmt.close();
//								} catch (SQLException se) {
//									se.printStackTrace(System.err);
//								}
//							}
//							if (con != null) {
//								try {
//									con.close();
//								} catch (Exception e) {
//									e.printStackTrace(System.err);
//								}
//							}
//						}
//						return s_gro_infoVO;
//					}
					
					@Override
					public void insert(S_gro_infoVO s_gro_infoVO , Integer mem_no) {

						Connection con = null;
						PreparedStatement pstmt = null;
					
						try {
							
							con = ds.getConnection();
														
							// 1●設定於 pstm.executeUpdate()之前
				    		con.setAutoCommit(false);
							
				    		// 先新增部門
							String cols[] = {"S_GRO_NO"};
							pstmt = con.prepareStatement(INSERT_STMT , cols);			
							pstmt.setString(1, s_gro_infoVO.getS_gro_name());
							pstmt.setString(2, s_gro_infoVO.getS_con());
							pstmt.setInt(3, s_gro_infoVO.getMem_no());
							pstmt.setInt(4, s_gro_infoVO.getCs_no());
							pstmt.setDate(5, s_gro_infoVO.getCre_date());
							pstmt.setString(6, s_gro_infoVO.getS_gro_sta());

							pstmt.executeUpdate();
							//掘取對應的自增主鍵值
							String next_s_gro_no = null;
							ResultSet rs = pstmt.getGeneratedKeys();
							if (rs.next()) {
								next_s_gro_no = rs.getString(1);
								System.out.println("自增主鍵值= " + next_s_gro_no +"(剛新增成功的讀書會編號)");
							} else {
								System.out.println("未取得自增主鍵值");
							}
							rs.close();
							
							Integer s_gro_no = new Integer(next_s_gro_no);
							
							S_gro_memVO s_gro_memVO = new S_gro_memVO();
							s_gro_memVO.setS_gro_no(s_gro_no);
							s_gro_memVO.setMem_no(mem_no);
							
							// 再同時新增成員
							S_gro_memDAO dao = new S_gro_memDAO();
							
							dao.insert2(s_gro_memVO, con);
							

							// 2●設定於 pstm.executeUpdate()之後
							con.commit();
							con.setAutoCommit(true);
							
							// Handle any driver errors
						} catch (SQLException se) {
							if (con != null) {
								try {
									// 3●設定於當有exception發生時之catch區塊內
									System.err.print("Transaction is being ");
									System.err.println("rolled back-由-dept");
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
							if (con != null) {
								try {
									con.close();
								} catch (Exception e) {
									e.printStackTrace(System.err);
								}
							}
						}
					}
					
					public List<S_gro_infoVO> findClub(Integer mem_no) {
						List<S_gro_infoVO> list = new ArrayList<S_gro_infoVO>();
						S_gro_infoVO s_gro_infoVO = null;

						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try {

							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_CLUB);
							pstmt.setInt(1, mem_no);
							rs = pstmt.executeQuery();
							
							while (rs.next()) {
								// empVO 也稱為 Domain objects
								s_gro_infoVO = new S_gro_infoVO();
								s_gro_infoVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_infoVO.setS_gro_name(rs.getString("s_gro_name"));
								s_gro_infoVO.setS_con(rs.getString("s_con"));
								s_gro_infoVO.setMem_no(rs.getInt("mem_no"));
								s_gro_infoVO.setCs_no(rs.getInt("cs_no"));
								s_gro_infoVO.setCre_date(rs.getDate("cre_date"));
								s_gro_infoVO.setS_gro_sta(rs.getString("s_gro_sta"));
								list.add(s_gro_infoVO); // Store the row in the list
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
					public Set<S_gro_memVO> getS_gro_memByS_gro_no(Integer s_gro_no) {
						Set<S_gro_memVO> set = new LinkedHashSet<S_gro_memVO>();
						S_gro_memVO s_gro_memVO = null;
					
						Connection con = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
					
						try {
					
							con = ds.getConnection();
							pstmt = con.prepareStatement(GET_S_gro_mem_ByS_gro_no_STMT);
							pstmt.setInt(1, s_gro_no);
							rs = pstmt.executeQuery();
					
							while (rs.next()) {
								s_gro_memVO = new S_gro_memVO();
								s_gro_memVO.setS_gro_no(rs.getInt("s_gro_no"));
								s_gro_memVO.setMem_no(rs.getInt("mem_no"));
								s_gro_memVO.setAu_no(rs.getInt("au_no"));
								set.add(s_gro_memVO); // Store the row in the vector
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
						return set;
					}

	public List<S_gro_infoVO> getByMenager(Integer mem_no){
		List<S_gro_infoVO> groupList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM S_gro_infoVO WHERE MEM_NO=?");
		query.setParameter(0, mem_no);
		groupList = query.list();
		return groupList;
	}
						
}
