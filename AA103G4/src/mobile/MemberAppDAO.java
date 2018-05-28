package mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mobile.MemberAppDAO_interface;

public class MemberAppDAO implements MemberAppDAO_interface{
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String GET_ONE_BY_ACCT_PWD = "SELECT MEM_NO FROM mem WHERE MEM_MAIL=? AND MEM_PSW=?";
	private static final String GET_MEM_IMG = "SELECT MEM_PHOTO FROM mem where mem_no = ?";
	private static final String LOGIN_STMT = "SELECT * FROM mem WHERE mem_mail=? and mem_psw=?";
	
	@Override
	public String findByAcctPwd(String mem_mail, String mem_psw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String mem_no = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ACCT_PWD);
			pstmt.setString(1, mem_mail);
			pstmt.setString(2, mem_psw);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				mem_no = rs.getString("mem_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return mem_no;
	
	}
	
	public byte[] getMemImage(String mem_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] mem_photo = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_IMG);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				mem_photo = rs.getBytes("mem_photo");
			}
		}catch(SQLException e){
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return mem_photo;
	}
	
	public String loginCheck(MemAppVO memAppVO) {//package憟辣
			
		String feedback = "0";
		String password = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
			pstmt.setString(1, memAppVO.getMemMail());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				password = rs.getString("mem_psw");
				
				if (password.equals(memAppVO.getMemPsw())) {
					feedback = rs.getString("mem_no");
				} else {
					feedback = "1";
				}
			} 
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} 
		finally {
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
		
		return feedback;
		
	}
}


