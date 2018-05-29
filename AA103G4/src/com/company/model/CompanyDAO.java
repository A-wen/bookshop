package com.company.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.book.model.BookVO;

public class CompanyDAO implements CompanyDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO company VALUES (COMPANY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM company order by comp_no";
	private static final String GET_ONE_STMT = "SELECT * FROM company where comp_no = ?";
	private static final String GET_BY_NAME = "SELECT * FROM company where comp_name like ?";
	private static final String DELETE = "DELETE FROM company where comp_no = ?";
	private static final String UPDATE = "UPDATE company set comp_name=?, comp_tel=?, comp_add=?, comp_number=?, comp_contact=?, comp_email=? where comp_no = ?";
	private static final String GET_BOOK_BY_COMPANY = "SELECT * FROM book where comp_no = ? order by book_no";

	@Override
	public void insert(CompanyVO companyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, companyVO.getComp_name());
			pstmt.setString(2, companyVO.getComp_tel());
			pstmt.setString(3, companyVO.getComp_add());
			pstmt.setString(4, companyVO.getComp_number());
			pstmt.setString(5, companyVO.getComp_contact());
			pstmt.setString(6, companyVO.getComp_email());

			pstmt.executeUpdate();

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
	public void update(CompanyVO companyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, companyVO.getComp_name());
			pstmt.setString(2, companyVO.getComp_tel());
			pstmt.setString(3, companyVO.getComp_add());
			pstmt.setString(4, companyVO.getComp_number());
			pstmt.setString(5, companyVO.getComp_contact());
			pstmt.setString(6, companyVO.getComp_email());
			pstmt.setInt(7, companyVO.getComp_no());

			pstmt.executeUpdate();

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
	public void delete(Integer comp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, comp_no);

			pstmt.executeUpdate();

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
	public List<CompanyVO> getByName(String name) {
		List<CompanyVO> list = new ArrayList<CompanyVO>();
		CompanyVO companyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_NAME);
			String str = "%" + name + "%";
			pstmt.setString(1, str);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				companyVO = new CompanyVO();
				companyVO.setComp_no(rs.getInt("comp_no"));
				companyVO.setComp_name(rs.getString("comp_name"));
				companyVO.setComp_tel(rs.getString("comp_tel"));
				companyVO.setComp_add(rs.getString("comp_add"));
				companyVO.setComp_number(rs.getString("comp_number"));
				companyVO.setComp_contact(rs.getString("comp_contact"));
				companyVO.setComp_email(rs.getString("comp_email"));
				list.add(companyVO);
			}

		} catch (SQLException se) {
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
	public CompanyVO findByPrimaryKey(Integer comp_no) {

		CompanyVO companyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, comp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				companyVO = new CompanyVO();
				companyVO.setComp_no(rs.getInt("comp_no"));
				companyVO.setComp_name(rs.getString("comp_name"));
				companyVO.setComp_tel(rs.getString("comp_tel"));
				companyVO.setComp_add(rs.getString("comp_add"));
				companyVO.setComp_number(rs.getString("comp_number"));
				companyVO.setComp_contact(rs.getString("comp_contact"));
				companyVO.setComp_email(rs.getString("comp_email"));
			}

		} catch (SQLException se) {
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
		return companyVO;
	}

	@Override
	public List<CompanyVO> getAll() {
		List<CompanyVO> list = new ArrayList<CompanyVO>();
		CompanyVO companyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				companyVO = new CompanyVO();
				companyVO.setComp_no(rs.getInt("comp_no"));
				companyVO.setComp_name(rs.getString("comp_name"));
				companyVO.setComp_tel(rs.getString("comp_tel"));
				companyVO.setComp_add(rs.getString("comp_add"));
				companyVO.setComp_number(rs.getString("comp_number"));
				companyVO.setComp_contact(rs.getString("comp_contact"));
				companyVO.setComp_email(rs.getString("comp_email"));
				list.add(companyVO);
			}

		} catch (SQLException se) {
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
	public List<BookVO> getBookByCompany(Integer comp_no) {
		List<BookVO> list = new ArrayList<BookVO>();
		BookVO bookVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOOK_BY_COMPANY);
			pstmt.setInt(1, comp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setBook_no(rs.getInt("book_no"));
				bookVO.setBook_name(rs.getString("book_name"));
				bookVO.setBook_price(rs.getInt("book_price"));
				bookVO.setType_no(rs.getInt("type_no"));
				bookVO.setComp_no(rs.getInt("comp_no"));
				bookVO.setBook_qty(rs.getInt("book_qty"));
				bookVO.setIsbn(rs.getString("isbn"));
				bookVO.setBook_author(rs.getString("book_author"));
				bookVO.setBook_pic(rs.getBytes("book_pic"));
				bookVO.setBook_desc(rs.getString("book_desc"));
				bookVO.setSaleable(rs.getInt("saleable"));
				list.add(bookVO);
			}

		} catch (SQLException se) {
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
}