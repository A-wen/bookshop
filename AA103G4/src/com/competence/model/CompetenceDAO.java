package com.competence.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.fun.model.FunVO;

public class CompetenceDAO implements CompetenceDAO_interface {
	private static DataSource ds;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO competence (emp_no,fun_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM competence order by emp_no";
	private static final String GETBYFUN = "SELECT emp_no FROM competence where fun_no = ?";
	private static final String GETBYEMP = "SELECT fun_no,emp_no FROM competence where emp_no = ?";
	private static final String GETBYEMPNO = "SELECT fun_no FROM competence where emp_no = ?";
	private static final String DELETE = "DELETE FROM competence where emp_no = ? and fun_no=?";
	private static final String DELETEALL = "DELETE FROM competence where emp_no = ?";
	private static final String UPDATE = "UPDATE competence set fun_no=? where emp_no = ? and fun_no=?";

	// /** 得知此員工編號，並可以新增功能權限編號給此員工 **/
	// @Override
	// public void insert(CompetenceVO competenceVO) {
	// Connection con = null;
	// PreparedStatement pstmt = null;
	//
	// try {
	// con = ds.getConnection();
	// pstmt = con.prepareStatement(INSERT_STMT);
	// pstmt.setInt(1, competenceVO.getEmpNo());
	// pstmt.setInt(2, competenceVO.getFunNo());
	// pstmt.executeUpdate();
	// }
	// // Handle any SQL errors
	// catch (SQLException se) {
	// throw new RuntimeException("A database error occured. " +
	// se.getMessage());
	// } finally {
	// if (pstmt != null) {
	// try {
	// pstmt.close();
	// } catch (SQLException se) {
	// se.printStackTrace(System.err);
	// }
	// }
	// if (con != null) {
	// try {
	// con.close();
	// } catch (Exception e) {
	// e.printStackTrace(System.err);
	// }
	// }
	// }
	//
	// }
	/** 藉由新的功能編號修改此員工舊的功能權限的方法 **/
	@Override
	public void update(Integer new_no, Integer emp_no, Integer fun_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, new_no);
			pstmt.setInt(2, emp_no);
			pstmt.setInt(3, fun_no);

			pstmt.executeUpdate();
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/** 刪除員工的功能某權限的方法 **/
	@Override
	public void delete(Integer emp_no, Integer fun_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, emp_no);
			pstmt.setInt(2, fun_no);
			pstmt.executeUpdate();
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/** 查詢功能權限編號，知道那些員工擁有此權限 **/
	@Override
	public List<CompetenceVO> findByFun(Integer fun_no) {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYFUN);
			pstmt.setInt(1, fun_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				competenceVO = new CompetenceVO();
				competenceVO.setEmpNo(rs.getInt("emp_no"));
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/** 查詢全部員工編號及功能權限編號 **/
	@Override
	public List<CompetenceVO> getAll() {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				competenceVO = new CompetenceVO();
				competenceVO.setEmpNo(rs.getInt("emp_no"));
				competenceVO.setFunNo(rs.getInt("fun_no"));
				list.add(competenceVO); // Store the row in the list
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	/** 查詢員工權限編號，得知這員工擁有了那些權限 **/
	@Override
	public List<CompetenceVO> findByEmp(Integer emp_no) {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYEMP);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				competenceVO = new CompetenceVO();
				competenceVO.setFunNo(rs.getInt("fun_no"));
				competenceVO.setEmpNo(rs.getInt("emp_no"));
				list.add(competenceVO);
//				System.out.println(list);
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Integer> findByEmpNo(Integer emp_no) {
		List<Integer> list = new ArrayList<Integer>();
		CompetenceVO competenceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYEMPNO);
			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				competenceVO = new CompetenceVO();
				competenceVO.setFunNo(rs.getInt("fun_no"));
				list.add(competenceVO.getFunNo());
//				System.out.println(list);
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(Integer emp_no, String[] list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		deleteALL(emp_no);
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			if (list != null) {
				for (String fun_no : list) {
					pstmt.setInt(1, emp_no);
					pstmt.setInt(2, Integer.parseInt(fun_no));
					pstmt.executeUpdate();
				}
			}
		}
		// Handle any SQL errors
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void deleteALL(Integer emp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEALL);
			pstmt.setInt(1, emp_no);
			pstmt.executeUpdate();
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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




}
