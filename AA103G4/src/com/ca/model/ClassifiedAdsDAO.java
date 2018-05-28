package com.ca.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class ClassifiedAdsDAO implements ClassifiedAdsDAO_interface{
	
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
	
	private static final String INSERT= "INSERT INTO ClassifiedAds(ca_no,type_no,book_no,ca_start,ca_end,ca_th,ca_name)VALUES(CLASSIFIEDADS_seq.NEXTVAL,?,?,?,?,?,?,?)";
	
	private static final String DELETE="DELETE from ClassifiedAds where ca_no=?";
	
	private static final String UPDATE="UPDATE ClassifiedAds set type_no=?,book_no=?,ca_start=?,ca_end=?,ca_th=?,ca_name=? where ca_no=?  ";
	
	private static final String GET_ALL_STMT="SELECT ca_no,type_no,book_no,ca_start,ca_end,ca_th,ca_name FROM ClassifiedAds order by ca_no";
	
	private static final String GET_ONE_STMT="SELECT ca_no,type_no,book_no,ca_start,ca_end,ca_th,ca_name FROM ClassifiedAds where ca_no = ?";

	@Override
	public void insert(ClassifiedAdsVO caVO) {
		Connection con= null;
		PreparedStatement pstmt=null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			
			pstmt.setInt(1,caVO.getTypeno());
			pstmt.setInt(2,caVO.getBookno());		
			pstmt.setDate(3,caVO.getCastart());
			pstmt.setDate(4,caVO.getCaend());
			pstmt.setInt(5,caVO.getCath());
			pstmt.setString(6,caVO.getCaname());
							
							
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
	public void update(ClassifiedAdsVO caVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1,caVO.getTypeno());
			pstmt.setInt(2,caVO.getBookno());			
			pstmt.setDate(3,caVO.getCastart());
			pstmt.setDate(4,caVO.getCaend());
			pstmt.setInt(5,caVO.getCath());
			pstmt.setString(6,caVO.getCaname());
			pstmt.setInt(7,caVO.getCano());							
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
	public void delete(Integer ca_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,ca_no);

			pstmt.executeUpdate();
			

			// Handle any driver errors
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
	public ClassifiedAdsVO findByPK(Integer ca_no) {
		ClassifiedAdsVO caVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ca_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				caVO = new ClassifiedAdsVO();
				caVO.setCano(rs.getInt("ca_no"));
				caVO.setTypeno(rs.getInt("type_no"));
				caVO.setBookno(rs.getInt("book_no"));
				caVO.setCastart(rs.getDate("ca_start"));
				caVO.setCaend(rs.getDate("ca_end"));
				caVO.setCath(rs.getInt("ca_th"));
				caVO.setCaname(rs.getString("ca_name"));
				
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
					return caVO;
				}


	@Override
	public List<ClassifiedAdsVO> getAll() {
		List<ClassifiedAdsVO> list = new ArrayList<ClassifiedAdsVO>();
		ClassifiedAdsVO caVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				caVO = new ClassifiedAdsVO();
				caVO.setCano(rs.getInt("ca_no"));
				caVO.setTypeno(rs.getInt("type_no"));
				caVO.setBookno(rs.getInt("book_no"));
	
				caVO.setCastart(rs.getDate("ca_start"));
				caVO.setCaend(rs.getDate("ca_end"));
				caVO.setCath(rs.getInt("ca_th"));
				caVO.setCaname(rs.getString("ca_name"));
				list.add(caVO); // Store the row in the list
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
			
	