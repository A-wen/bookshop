package com.cm.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CmDAO implements  CmDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
	private static final String INSERT_STMT = "INSERT INTO CM(cm_no, cm_name, cm_start, cm_end, cm_pic, cm_th, cm_inv, cm_url)VALUES(cm_seq.NEXTVAL,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_STMT="SELECT cm_no, cm_name, cm_start, cm_end, cm_pic, cm_th, cm_inv, cm_url FROM CM order by cm_no";
	
	private static final String GET_ONE_STMT="SELECT cm_no, cm_name, cm_start, cm_end, cm_pic, cm_th, cm_inv, cm_url FROM CM WHERE cm_no=?";
	
	private static final String DELETE="DELETE from cm where cm_no=?";
	
	private static final String UPDATE="UPDATE cm set cm_name=?,cm_start=?,cm_end=?,cm_pic=?,cm_th=?,cm_inv=?,cm_url=? where cm_no=?";
	
	

	@Override
	public void insert(CmVO cmVO) {
		
		Connection con= null;
		PreparedStatement pstmt=null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,cmVO.getCmName());
			pstmt.setDate(2,cmVO.getCmStart());
			pstmt.setDate(3,cmVO.getCmEnd());
			pstmt.setBytes(4,cmVO.getCmPic());
			pstmt.setInt(5,cmVO.getCmTh());
			pstmt.setString(6,cmVO.getCminv());
			pstmt.setString(7,cmVO.getCmUrl());
							
							
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
	public void delete(Integer cm_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,cm_no);

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
	public void update(CmVO cmVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,cmVO.getCmName());
			pstmt.setDate(2,cmVO.getCmStart());
			pstmt.setDate(3,cmVO.getCmEnd());
			pstmt.setBytes(4,cmVO.getCmPic());
			pstmt.setInt(5,cmVO.getCmTh());
			pstmt.setString(6,cmVO.getCminv());
			pstmt.setString(7,cmVO.getCmUrl());
			pstmt.setInt(8,cmVO.getCmNo());
							
							
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
	public CmVO findByPK(Integer cm_no) {

		CmVO cmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,cm_no);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cmVo 嚙稽嚙誶穿蕭 Domain objects
				cmVO = new CmVO();
				cmVO.setCmNo(rs.getInt("cm_no"));
				cmVO.setCmName(rs.getString("cm_name"));				
				cmVO.setCmStart(rs.getDate("cm_start"));
				cmVO.setCmEnd(rs.getDate("cm_end"));
				cmVO.setCmPic(rs.getBytes("cm_pic"));
				cmVO.setCmTh(rs.getInt("cm_th"));
				cmVO.setCminv(rs.getString("cm_inv"));
				cmVO.setCmUrl(rs.getString("cm_url"));
			
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
		return cmVO;
	}

	@Override
	public List<CmVO> getAll() {
		List<CmVO> list=new ArrayList<CmVO>();
		CmVO cmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				cmVO = new CmVO();
				cmVO.setCmNo(rs.getInt("cm_no"));
				cmVO.setCmName(rs.getString("cm_name"));
				cmVO.setCmStart(rs.getDate("cm_start"));
				cmVO.setCmEnd(rs.getDate("cm_end"));
				cmVO.setCmPic(rs.getBytes("cm_pic"));				
				cmVO.setCmTh(rs.getInt("cm_th"));
				cmVO.setCminv(rs.getString("cm_inv"));
				cmVO.setCmUrl(rs.getString("cm_url"));
				//cmVO.set
				list.add(cmVO); // Store the row in the list
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

}
	