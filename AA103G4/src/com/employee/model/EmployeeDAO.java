package com.employee.model;

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

import com.mem.model.MemVO;

public class EmployeeDAO implements EmployeeDAO_interface {
	
	/**  最終版本JNDI **/
	private static DataSource ds;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String AJAXLOGINACC_STMT = "SELECT * FROM employee WHERE emp_acc=?";
	private static final String AJAXLOGINPSW_STMT = "SELECT * FROM employee WHERE emp_psw=?";
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setString(2, employeeVO.getEmpAcc());
			pstmt.setString(3, employeeVO.getEmpPsw());

			pstmt.executeUpdate();
			// Handle any driver errors
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

	}

	@Override
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, employeeVO.getEmpPsw());
			pstmt.setString(2, employeeVO.getEmpName());
			pstmt.executeUpdate();
		}
		// Handle any driver errors

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
	public void delete(Integer emp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_no);

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

	public EmployeeVO findByPrimaryKey(Integer emp_no) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				employeeVO = new EmployeeVO();

				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));

			}
			// Handle any driver errors
		} catch (SQLException se) {
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
			con = ds.getConnection();
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

	@Override
	public EmployeeVO selectLogin(String emp_acc, String emp_psw) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN_STMT);
			pstmt.setString(1, emp_acc);
			pstmt.setString(2, emp_psw);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));
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
		return employeeVO;
	}

	@Override
	public EmployeeVO AjaxaccLogin(String emp_acc) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AJAXLOGINACC_STMT);
			pstmt.setString(1, emp_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmpno(rs.getInt("emp_no"));
				employeeVO.setEmpName(rs.getString("emp_name"));
				employeeVO.setEmpAcc(rs.getString("emp_acc"));
				employeeVO.setEmpPsw(rs.getString("emp_psw"));
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
		return employeeVO;
	}


}
