package com.employee.model;

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
/*******************JDBC版本測試用*********************/
public class EmployeeJDBCDAO implements EmployeeDAO_interface {

	// private static DataSource ds;
	// static {
	// try {
	// Context ctx = new InitialContext();
	// ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "JIN";
	String Psw = "aa611235";
	private static final String LOGIN_STMT = "SELECT * FROM employee WHERE emp_acc=? and emp_psw=?";
	private static final String INSERT_STMT = "INSERT INTO employee (emp_no,emp_name,emp_acc,emp_psw) VALUES (employee_seq.NEXTVAL, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM employee order by emp_no";
	private static final String GET_ONE_STMT = "SELECT emp_no,emp_name,emp_acc,emp_psw FROM employee where emp_no = ?";
	private static final String DELETE = "DELETE FROM employee where emp_no = ?";
	private static final String UPDATE = "UPDATE employee set emp_psw=? where emp_name = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			// con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setString(2, employeeVO.getEmpAcc());
			pstmt.setString(3, employeeVO.getEmpPsw());

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		} catch (SQLException se) {
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
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getEmpPsw());
			pstmt.setString(2, employeeVO.getEmpName());
			

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer emp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_no);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public EmployeeVO findByPrimaryKey(Integer emp_no) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));

			}
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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				// empVO 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));
				list.add(employeeVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors }
		}
		// Handle any driver errors
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
		/********以下測試用********/
		
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

		EmployeeVO employeeVO = new EmployeeVO();
//		employeeVO.setEmpName("陳思璇");
//		employeeVO.setEmpAcc("JSONS6619@gamil.com");
//		employeeVO.setEmpPsw("marvens21");
//		employeeVO.setEmpno(110);
//		dao.update(employeeVO);
//		dao.insert(employeeVO);
//		System.err.println(dao.getAll());
//		System.out.println(dao.getAll());
//		System.err.println(dao.findByPrimaryKey(110));
//		dao.delete(109);
		
		
		
		/**查全部**/
//		List<EmployeeVO> list = dao.getAll();
//		for(EmployeeVO employeeVO1:list){
//			System.out.println(employeeVO1.getEmpno());
//			System.out.println(employeeVO1.getEmpName());
//			System.out.println(employeeVO1.getEmpAcc());
//			System.out.println(employeeVO1.getEmpPsw());
//		}
		
		/**查單一**/
//		EmployeeVO employeeVO1 = dao.findByPrimaryKey(101);
//		System.out.println(employeeVO1.getEmpno());
//		System.out.println(employeeVO1.getEmpName());
//		System.out.println(employeeVO1.getEmpAcc());
//		System.out.println(employeeVO1.getEmpPsw());
		
		/**新增**/
//		employeeVO.setEmpName("派大星");
//		employeeVO.setEmpAcc("H1l2@gmail.com");
//		employeeVO.setEmpPsw("az123");
//		dao.insert(employeeVO);
		
		/**刪除**/
//		dao.delete(101);
		
		
		/**修改**/
//		employeeVO.setEmpName("海綿寶寶");
//		employeeVO.setEmpAcc("YOYO@gmail.com");
//		employeeVO.setEmpPsw("aa103");
//		employeeVO.setEmpno(101);
//		dao.update(employeeVO);
		
	}

	@Override
	public EmployeeVO selectLogin(String emp_acc, String emp_psw) {
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, Psw);
			pstmt = con.prepareStatement(LOGIN_STMT);
			rs = pstmt.executeQuery();
				
			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors }
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
		return employeeVO;
	}

	@Override
	public EmployeeVO AjaxaccLogin(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
