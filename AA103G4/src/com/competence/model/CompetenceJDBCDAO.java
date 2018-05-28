package com.competence.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetenceJDBCDAO implements CompetenceDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO competence (emp_no,fun_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM competence order by emp_no";
	private static final String GETBYFUN = "SELECT emp_no FROM competence where fun_no = ?";
	private static final String GETBYEMP = "SELECT fun_no FROM competence where emp_no = ?";
	private static final String DELETE = "DELETE FROM competence where emp_no = ? and fun_no=?";
	private static final String UPDATE = "UPDATE competence set fun_no=? where emp_no = ? and fun_no=?";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "Allen";
	String psw = "aa611235";



	@Override
	public void delete(Integer emp_no, Integer fun_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_no);
			pstmt.setInt(2, fun_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}

		// Handle any driver errors
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
	public List<CompetenceVO> findByFun(Integer fun_no) {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(GETBYFUN);

			pstmt.setInt(1, fun_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				competenceVO = new CompetenceVO();
				competenceVO.setEmpNo(rs.getInt("emp_no"));
				list.add(competenceVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}

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

	@Override
	public List<CompetenceVO> findByEmp(Integer emp_no) {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(GETBYEMP);

			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				competenceVO = new CompetenceVO();
				competenceVO.setFunNo(rs.getInt("fun_no"));
				list.add(competenceVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		}

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

	@Override
	public List<CompetenceVO> getAll() {
		List<CompetenceVO> list = new ArrayList<CompetenceVO>();
		CompetenceVO competenceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				competenceVO = new CompetenceVO();
				competenceVO.setEmpNo(rs.getInt("emp_no"));
				competenceVO.setFunNo(rs.getInt("fun_no"));
				list.add(competenceVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
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

	public static void main(String[] args) {
		CompetenceJDBCDAO dao = new CompetenceJDBCDAO();
		// CompetenceVO competenceVO = new CompetenceVO();
		// competenceVO.setEmpNo(102);
		// competenceVO.setFunNo(102);
		// System.err.println(dao.findByPrimaryKey(101));
		// System.err.println(dao.getAll());
		// dao.insert(competenceVO);

		/**********************************嚙瘡嚙磊嚙踝蕭嚙踝蕭 ****************************************/

		/** 嚙範嚙賠伐蕭嚙踝蕭嚙踝蕭嚙線嚙編嚙踝蕭嚙諄功嚙踝蕭嚙緞嚙踝蕭嚙編嚙踝蕭 **/
		// List<CompetenceVO> list = dao.getAll();
		// for(CompetenceVO competenceVO1 :list){
		// System.err.print("嚙踝蕭嚙線嚙編嚙踝蕭嚙踝蕭:"+competenceVO1.getEmpNo()+"嚙誰佗蕭嚙瘤..");
		// System.err.println("嚙穀嚙踝蕭嚙緞嚙踝蕭嚙編嚙踝蕭"+competenceVO1.getFunNo());
		// }

		/** 嚙範嚙賠功嚙踝蕭嚙緞嚙踝蕭嚙編嚙踝蕭嚙璀嚙踝蕭嚙瘩嚙踝蕭嚙褒哨蕭嚙線嚙誰佗蕭嚙踝蕭嚙緞嚙踝蕭 **/
//		List<CompetenceVO> list = dao.findByFun(101);
//		for (CompetenceVO competenceVO1 : list) {
//			System.err.println("嚙誰佗蕭嚙踝蕭嚙緞嚙踝蕭嚙穀嚙踝蕭嚙踝蕭u嚙踝蕭嚙編嚙踝蕭嚙踝蕭:" + competenceVO1.getEmpNo());
//		}
//		System.err.println(dao.findByEmp(101));
		
		/** 嚙範嚙賠哨蕭嚙線嚙緞嚙踝蕭嚙編嚙踝蕭嚙璀嚙緻嚙踝蕭嚙緻嚙踝蕭嚙線嚙誰佗蕭嚙瘤嚙踝蕭嚙踝蕭嚙緞嚙踝蕭 **/
//		List<CompetenceVO> list = dao.findByEmp(101);
//		for (CompetenceVO competenceVO1 : list) {
//			System.err.println("嚙踝蕭嚙踝蕭嚙線嚙賬有嚙踝蕭嚙穀嚙踝蕭嚙緞嚙踝蕭嚙踝蕭:" + competenceVO1.getFunNo() );
//		}

		/** 嚙磋嚙踝蕭嚙踝蕭嚙線嚙踝蕭嚙磐嚙穀嚙踝蕭嚙緞嚙踝蕭嚙踝蕭嚙踝蕭k **/
		// dao.delete(102,104);

		/** 嚙緻嚙踝蕭嚙踝蕭嚙踝蕭嚙線嚙編嚙踝蕭嚙璀嚙衛可嚙瘡嚙編嚙磕嚙穀嚙踝蕭嚙緞嚙踝蕭嚙編嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙線 **/
		// competenceVO.setEmpNo(104);
		// competenceVO.setFunNo(105);
		// dao.insert(competenceVO);

		/** 嚙褒由新嚙踝蕭嚙穀嚙踝蕭s嚙踝蕭嚙論改此嚙踝蕭嚙線嚙蝓迎蕭嚙穀嚙踝蕭嚙緞嚙踝蕭嚙踝蕭嚙踝蕭k **/
		// competenceVO.setEmpNo(104);
		// competenceVO.setFunNo(103);
		// dao.update(competenceVO,104);
	}

	@Override
	public List<Integer> findByEmpNo(Integer emp_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer new_no, Integer emp_no, Integer fun_no) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void insert(Integer emp_no, String[] fun_no) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteALL(Integer emp_no) {
		// TODO Auto-generated method stub
		
	}

}
