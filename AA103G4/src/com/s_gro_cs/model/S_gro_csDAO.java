package com.s_gro_cs.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.s_gro_info.model.S_gro_infoVO;

public class S_gro_csDAO implements S_gro_csDAO_interface{
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
			"INSERT INTO S_gro_cs (cs_no,cs_name) VALUES (S_GRO_CS_SEQ.NEXTVAL, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT cs_no,cs_name FROM S_gro_cs order by cs_no";
		private static final String GET_ONE_STMT = 
			"SELECT cs_no,cs_name FROM S_gro_cs where cs_no = ?";
		private static final String GET_S_gro_infos_ByCs_no_STMT = 
			"SELECT s_gro_no,s_gro_name,s_con,mem_no,cs_no,to_char(cre_date,'yyyy-mm-dd') cre_date,s_gro_sta FROM s_gro_info where cs_no = ? order by s_gro_no";
//		private static final String DELETE = 
//			"DELETE FROM S_gro_cs where cs_no = ?";
		private static final String DELETE_S_GRO_INFOs = 
			"DELETE FROM s_gro_info where cs_no = ?";
		private static final String DELETE_S_GRO_CS = 
			"DELETE FROM S_gro_cs where cs_no = ?";		
		private static final String UPDATE = 
			"UPDATE S_gro_cs set cs_name=? where cs_no = ?";
		
		@Override
		public void insert(S_gro_csVO s_gro_csVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, s_gro_csVO.getCs_name());

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
		public void update(S_gro_csVO s_gro_csVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			
				pstmt.setString(1, s_gro_csVO.getCs_name());
				pstmt.setInt(2, s_gro_csVO.getCs_no());
				
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
		public void delete(Integer cs_no) {
			int updateCount_S_GRO_INFOs = 0;

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				
				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);

				// 先刪除讀書會
				pstmt = con.prepareStatement(DELETE_S_GRO_INFOs);
				pstmt.setInt(1, cs_no);
				updateCount_S_GRO_INFOs = pstmt.executeUpdate();
				// 再刪除部門
				pstmt = con.prepareStatement(DELETE_S_GRO_CS);
				pstmt.setInt(1, cs_no);
				pstmt.executeUpdate();
				
				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("刪除讀書會編號" + cs_no + "時,共有讀書會" + updateCount_S_GRO_INFOs
						+ "個同時被刪除");

				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
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
		
		@Override
		public S_gro_csVO findByPrimaryKey(Integer cs_no) {

			S_gro_csVO s_gro_csVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, cs_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					s_gro_csVO = new S_gro_csVO();
					s_gro_csVO.setCs_no(rs.getInt("cs_no"));
					s_gro_csVO.setCs_name(rs.getString("cs_name"));
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
			return s_gro_csVO;
		}
		
		@Override
		public List<S_gro_csVO> getAll() {
			List<S_gro_csVO> list = new ArrayList<S_gro_csVO>();
			S_gro_csVO s_gro_csVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					s_gro_csVO = new S_gro_csVO();
					s_gro_csVO.setCs_no(rs.getInt("cs_no"));
					s_gro_csVO.setCs_name(rs.getString("cs_name"));
					list.add(s_gro_csVO); // Store the row in the list
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
		public Set<S_gro_infoVO> getS_gro_infosByCs_no(Integer cs_no) {
			Set<S_gro_infoVO> set = new LinkedHashSet<S_gro_infoVO>();
			S_gro_infoVO s_gro_infoVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_S_gro_infos_ByCs_no_STMT);
				pstmt.setInt(1, cs_no);
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
					set.add(s_gro_infoVO); // Store the row in the vector
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
}
