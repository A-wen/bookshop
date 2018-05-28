package com.fun.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FunJDBCDAO implements FunDAO_inteface {

	private static final String INSERT_STMT = "INSERT INTO fun(fun_no,fun_name) VALUES (fun_seq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM fun order by fun_no";
	private static final String GET_ONE_STMT = "SELECT fun_no,fun_name FROM fun where fun_no = ?";
	private static final String DELETE = "DELETE FROM fun where fun_no = ?";
	private static final String UPDATE = "UPDATE fun set fun_name=? where fun_no = ?";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "JIN";
	String psw = "aa611235";

	@Override
	public void insert(FunVO funVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, funVO.getFunName());

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	}

	@Override
	public void update(FunVO funVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, funVO.getFunName());
			pstmt.setInt(2, funVO.getFunno());

			pstmt.executeUpdate();
		}
		// Handle any driver errors
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer fun_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, fun_no);

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
	/**�d�ߥ\��s���o�������\�઺�W�ٱԭz����**/
	@Override
	public FunVO findByPrimaryKey(Integer fun_no) {
		FunVO funVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fun_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				funVO = new FunVO();

				funVO.setFunNo(rs.getInt("fun_no"));
				funVO.setFunName(rs.getString("fun_name"));
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
		return funVO;
	}
	@Override
	public List<FunVO> getAll() {
	List<FunVO> list = new ArrayList<FunVO>();
	FunVO funVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, psw);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// "SELECT * FROM member order by mem_no";
			// empVO �]�٬� Domain objects
			funVO = new FunVO();
			funVO.setFunNo(rs.getInt("fun_no"));
			funVO.setFunName(rs.getString("fun_name"));
			list.add(funVO); // Store the row in the list
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
		FunJDBCDAO dao = new FunJDBCDAO();
		FunVO funVO = new FunVO();
//		System.err.println(dao.getAll());
//		System.err.println(dao.findByPrimaryKey(101));
//		funVO.setFunName("�U�άd��");
//		dao.insert(funVO);
//		System.err.println(dao.findByPrimaryKey(102));
		
		
		/******************************�H�U���ե�****************************/
		
		/****�d����****/
//		List<FunVO> lists = dao.getAll();
//		for(FunVO funvo:lists){
//			System.err.println(funvo.getFunno());
//			System.err.println(funvo.getFunName());
//		}
		/**��@�d��**/
//		FunVO funvo = dao.findByPrimaryKey(101);
//		System.err.println(funvo.getFunno());
//		System.err.println(funvo.getFunName());
		
		/**�ק�**/
//		funVO.setFunName("�޲z�s�i");
//		funVO.setFunNo(101);
//		dao.update(funVO);
		
		/**�R��**/
//		dao.delete(101);
//		
		/**�s�W**/
//		funVO.setFunName("�޲z�v��");
//		dao.insert(funVO);
		
		
	
	
	
	}
		

}
