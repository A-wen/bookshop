package com.mem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cou.model.CouponVO;
import org.apache.log4j.Logger;
import com.mem.controller.MemServlet;

public class MemDAO implements MemDAO_interface {

	private static Logger logger = Logger.getLogger(MemDAO.class);
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
	private static final String DELETE = "DELETE FROM mem where mem_no = ?";
	private static final String UPDATE = "UPDATE mem set mem_name=?,mem_nic=?,mem_mail=?,mem_tel=?,mem_psw=?,mem_photo=? where mem_no=?";
	private static final String GET_BY_PSW = "SELECT * FROM mem WHERE mem_psw=?";

	private static final String GET_Coupons_ByMemno_STMT="select cou_name,cou_exp,cou_start,cou_end,cou_dis from coupon where mem_no=?";
	@Override
	public MemVO selectLogin(String mem_mail, String mem_psw) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
			pstmt.setString(1, mem_mail);
			pstmt.setString(2, mem_psw);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}

	@Override
	public String insert(MemVO memVO) {
		String result = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			String cols[] = { "mem_no" }; //用來取sequence的東西
			pstmt = con.prepareStatement(INSERT_STMT,cols); //prepareStatement要多一個參數

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_nic());
			pstmt.setString(3, memVO.getMem_tel());
			pstmt.setString(4, memVO.getMem_mail());
			pstmt.setString(5, memVO.getMem_psw());
			pstmt.setBytes(6, memVO.getMem_photo());
			pstmt.executeUpdate();
			logger.info("測試新東西");
			//取新增後自主產生的會員編號
			ResultSet rsKeys = pstmt.getGeneratedKeys();
			rsKeys.next(); //既使只有一個結果，也要呼叫rs.next，讓指標前進(指標原先是在表單外)
			result = rsKeys.getString(1);
			logger.info("會員編號："+result);
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return result;
	}

	@Override
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			// "UPDATE mem set mem_name=?,mem_nic=?,mem_mail=?,mem_tel=?,mem_psw=?,mem_photo=? where mem_no=?";
			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_nic());
			pstmt.setString(3, memVO.getMem_mail());
			pstmt.setString(4, memVO.getMem_tel());
			pstmt.setString(5, memVO.getMem_psw());
			pstmt.setBytes(6, memVO.getMem_photo());
			pstmt.setInt(7, memVO.getMem_no());
			pstmt.executeUpdate();
		}

		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public MemVO findByPrimaryKey(Integer mem_no) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			// "SELECT mem_no,mem_name,mem_tel,mem_mail,mem_psw,mem_photo FROM
			// member where mem_no = ?";
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
				list.add(memVO); // Store the row in the list
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/****
	 * Ajax檢查註冊帳號方法 
	 ****/
	@Override
	public MemVO checkMemMailRepeat(String mem_mail) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTEREDACC_STMT);
			pstmt.setString(1, mem_mail);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}

	/****
	 * Ajax檢查註冊暱稱方法
	 ****/
	@Override
	public MemVO checkMemNicRepeat(String mem_nic) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTEREDNIC_STMT);
			pstmt.setString(1, mem_nic);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}

	@Override
	public MemVO checkMemPhoneRepeat(String mem_tel) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REGISTEREDPHO_STMT);
			pstmt.setString(1, mem_tel);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}

	@Override
	public void ResetPsw(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(RESETPSW_STMT);
			pstmt.setString(1, memVO.getMem_psw());
			pstmt.setString(2, memVO.getMem_mail());
			pstmt.executeUpdate();
		}

		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemVO getUserInfo(Integer mem_no) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			final String sql = "SELECT MEM_NAME,MEM_TEL FROM MEM WHERE MEM_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_no(mem_no);
				memVO.setMem_name(rs.getString(1));
				memVO.setMem_tel(rs.getString(2));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
		
	}
	
	@Override
	public MemVO getByPSW(String psw) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PSW);
			pstmt.setString(1, psw);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {
				if(count>1){
					return null;
				}
				count ++;
				memVO = new MemVO();
				memVO.setMem_no(rs.getInt("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_nic(rs.getString("mem_nic"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_mail(rs.getString("mem_mail"));
				memVO.setMem_psw(rs.getString("mem_psw"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memVO;
	}
	
	public Set<CouponVO> getCouponsByMemno(Integer mem_no) {
		Set<CouponVO> set = new LinkedHashSet<CouponVO>();
		CouponVO couVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Coupons_ByMemno_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				couVO = new CouponVO();
				couVO.setCouname(rs.getString("cou_name"));
				couVO.setCouexp(rs.getString("cou_exp"));
				couVO.setCoustart(rs.getDate("cou_start"));
				couVO.setCouend(rs.getDate("cou_end"));
				couVO.setCoudis(rs.getDouble("cou_dis"));
				set.add(couVO); // Store the row in the vector
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
