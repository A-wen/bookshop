package mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mobile.MemberAppDAO_interface;


public class MemAppDAO implements MemAppDAO_interface {

	/** ���嚙踐��姿�嚙踝蕭�蝘克NDI **/
	private static DataSource ds;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String RESETPSW_STMT = "UPDATE mem set mem_psw=? where mem_mail=?";
	private static final String REGISTEREDPHO_STMT = "SELECT * FROM mem WHERE mem_tel=?";
	private static final String REGISTEREDNIC_STMT = "SELECT * FROM mem WHERE mem_nic=?";
	private static final String REGISTEREDACC_STMT = "SELECT * FROM mem WHERE mem_mail=?";
	private static final String LOGIN_STMT = "SELECT * FROM mem WHERE mem_mail=? and mem_psw=?";
	private static final String INSERT_STMT = "INSERT INTO mem (mem_no,mem_name,mem_nic,mem_tel,mem_mail,mem_psw,mem_photo) VALUES (mem_seq.NEXTVAL, ?, ?,?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM mem order by mem_no";
	private static final String GET_ONE_STMT = "SELECT mem_no,mem_name,mem_nic,mem_tel,mem_mail,mem_psw,mem_photo FROM mem where mem_no = ?";
	private static final String UPDATE = "UPDATE mem set mem_name=?,mem_nic=?,mem_mail=?,mem_tel=?,mem_psw=?,mem_photo=? where mem_no=?";

	
	public String loginCheck(MemAppVO memAppVO) {//package憟辣
		final String check = "select mem_no, mem_psw  from mem where  mem_mail = ?";
		String feedback = "0";
		String password = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(check);
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
	// check if Duplicate
	public int isAccountDup(MemAppVO memAppVO){
		final String check = "select count(*) from member where mem_no = ?";
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(check);
			pstmt.setInt(1, memAppVO.getMemNo());
			rs = pstmt.executeQuery();
			if (rs.next()){
				count = rs.getInt(1);
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
		
		return count;
	}
	////�����
	@Override
	public void insert(MemAppVO memAppVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MemAppVO memAppVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemAppVO findByPrimaryKey(Integer mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemAppVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemAppVO selectLogin(String mem_mail, String psw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemAppVO checkMemMailRepeat(String mem_mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemAppVO checkMemNicRepeat(String mem_nic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemAppVO checkMemPhoneRepeat(String mem_tel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ResetPsw(MemAppVO memAppVO) {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public String loginCheck(MemAppVO memVO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

//thanks
//package com.mem.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.mem.model.MemberAppDAO_interface;
//
//public class MemberAppDAO implements MemberAppDAO_interface{
//	private static DataSource ds = null;
//	
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private static final String GET_ONE_BY_ACCT_PWD = "SELECT MEM_NO FROM MEMBER WHERE MEM_MAIL=? AND MEM_PSW=?";
//	
//	
//	@Override
//	public String findByAcctPwd(String mem_mail, String mem_psw) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String mem_no = null;
//		ResultSet rs = null;
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_BY_ACCT_PWD);
//			pstmt.setString(1, mem_mail);
//			pstmt.setString(2, mem_psw);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				mem_no = rs.getString("mem_no");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return mem_no;
//	
//	}
//		
//}
//

