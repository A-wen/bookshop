package com.cou.model;

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

public class CouponAppDAO implements CouponAppDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} 
	
	private static final String GET_ALL_COUPON_STMT = "SELECT * FROM coupon WHERE mem_no = ?";
	
	@Override
	public List<CouponVO> getAllCoupons(Integer mem_no) {
		List<CouponVO> couponList = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_COUPON_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				couponVO = new CouponVO();
				
				couponVO.setCouno(rs.getInt("cou_no"));
				couponVO.setCoustart(rs.getDate("cou_start"));
				couponVO.setCouend(rs.getDate("cou_end"));
				couponVO.setCoudis(rs.getDouble("cou_dis"));
				couponVO.setCouexp(rs.getString("cou_exp"));
				couponVO.setMemno(rs.getInt("mem_no"));
				couponVO.setCouname(rs.getString("cou_name"));
				couponList.add(couponVO);
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
		return couponList;
	}

}
