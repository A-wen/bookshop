package com.info.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class InformationDAO implements InformationDAO_interface{
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	 private static final String INSERT= "INSERT INTO Information(info_no,info_term,info_exp, info_title)VALUES(information_seq.NEXTVAL,?,?,?)";
		
		private static final String DELETE="DELETE from Information where info_no=?";
		
		private static final String UPDATE="UPDATE Information set info_term=?,info_exp=?,info_title=?  where info_no=?";
		
		private static final String GET_ALL_STMT="SELECT info_no,info_term,info_exp,info_title FROM information order by info_no";
		
		private static final String GET_ONE_STMT="SELECT info_no,info_term,info_exp,info_title FROM information where info_no = ?";

	@Override
	public void insert(InformationVO infoVO) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
	
			try {
				
				con=ds.getConnection();
				pstmt=con.prepareStatement(INSERT);
				
				pstmt.setDate(1,infoVO.getInfoterm());
				pstmt.setString(2,infoVO.getInfoexp());
				pstmt.setString(3,infoVO.getInfotitle());
				pstmt.executeQuery();
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public void update(InformationVO infoVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			
			pstmt.setDate(1,infoVO.getInfoterm());
			pstmt.setString(2,infoVO.getInfoexp());
			pstmt.setString(3,infoVO.getInfotitle());
			pstmt.setInt(4,infoVO.getInfono());
			pstmt.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer info_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(DELETE);
			
			pstmt.setInt(1,info_no);
			pstmt.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public InformationVO findPK(Integer info_no) {
		
		
		
		InformationVO infoVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, info_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				infoVO=new InformationVO();
				infoVO.setInfono(rs.getInt("info_no"));
				infoVO.setInfotitle(rs.getString("info_title"));
				infoVO.setInfoterm(rs.getDate("info_term"));
				infoVO.setInfoexp(rs.getString("info_exp"));
				
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
		return infoVO;
	}

	@Override
	public List<InformationVO> getAll() {
		
		List<InformationVO> list =new ArrayList<InformationVO>();
		InformationVO infoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO  Domain objects
				infoVO=new InformationVO();
				
				infoVO.setInfono(rs.getInt("info_no"));
				infoVO.setInfoterm(rs.getDate("info_term"));
				infoVO.setInfoexp(rs.getString("info_exp"));
				infoVO.setInfotitle(rs.getString("info_title"));
				
				list.add(infoVO); // Store the row in the list
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

	
	
	