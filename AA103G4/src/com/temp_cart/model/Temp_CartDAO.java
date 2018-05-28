package com.temp_cart.model;

import java.util.*;

import com.shopping_cart.model.Shopping_CartVO;

import util.cy.DBConFactory;
import util.cy.DBConnect;

import java.sql.*;

public class Temp_CartDAO implements Temp_CartDAO_Interface{

	//SQL指令
	private static final String INSERT_CS = 
		"{call ADD_TO_TEMP_CART(?,?,?)}";
	private static final String INSERT_STMT = 
		"INSERT INTO TEMP_CART (MEM_NO,BOOK_NO,B_AMOUNT) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO,BOOK_NO, B_AMOUNT FROM TEMP_CART ORDER BY MEM_NO , BOOK_NO"; 
	private static final String GET_ONE_STMT = 
		"SELECT MEM_NO,BOOK_NO, B_AMOUNT FROM TEMP_CART WHERE MEM_NO =? AND BOOK_NO= ?";
	private static final String DELETE_ALL = 
		"DELETE FROM TEMP_CART WHERE MEM_NO = ?";
	private static final String DELETE_BOOKS = 
		"DELETE FROM TEMP_CART WHERE MEM_NO = ? AND ";
	private static final String UPDATE = 
		"UPDATE TEMP_CART SET B_AMOUNT=? WHERE MEM_NO=? AND BOOK_NO=?";
	private static final String GET_MEMBER_CART_STMT = 
		"SELECT B_NO,B_NAME,B_AUTHOR,B_PIC,B_PRICE,B_QTY,B_STOCK,P_NAME,P_DISC,P_PRICE "
		+"FROM SHOPPING_CART WHERE M_NO=?";

	
	//連線設定
	private DBConnect dbCon;
	
	/**
	 * 預設建構子(使用JNDI連線)
	 */
	public Temp_CartDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	/**
	 * 傳入連線方法，設定連線取得方式
	 * @param conMethod JNDI或JDBC
	 */
	public Temp_CartDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	@Override
	public boolean insert (Temp_cartVO temp_cartVO) {
		boolean result = false;
		try(Connection con = dbCon.getCon();
			CallableStatement cs = con.prepareCall(INSERT_CS);){
			Integer rows = 0; 
			cs.setInt(1, temp_cartVO.getMem_no());
			cs.setInt(2, temp_cartVO.getBook_no());
			cs.setInt(3, temp_cartVO.getB_amount());
			rows = cs.executeUpdate();
			if (rows==1){result=true;}
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return result;
	}
	
	@Override
	public boolean update(Temp_cartVO temp_cartVO){
		int result = 0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(UPDATE)){
				pstmt.setInt(1, temp_cartVO.getB_amount());
				pstmt.setInt(2, temp_cartVO.getMem_no());
				pstmt.setInt(3, temp_cartVO.getBook_no());				
				pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		if (result==1){
			return true;
		}else{
			return false;
		}
	}
	
	
	@Override
	public int delAll(Integer mem_no) {
		int result = 0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(DELETE_ALL)){
				pstmt.setInt(1, mem_no);
				result = pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return result;
	}
	
	@Override
	public Temp_cartVO findByPrimaryKey(Integer mem_no,Integer book_no) {

		Temp_cartVO temp_cartVO = null;
		try (Connection con = dbCon.getCon();
				ResultSet rs = dbCon.query(con, GET_MEMBER_CART_STMT,mem_no,book_no);	){
			while (rs.next()) {
				temp_cartVO = new Temp_cartVO();
				temp_cartVO.setMem_no(rs.getInt("mem_no"));
				temp_cartVO.setBook_no(rs.getInt("book_no"));
				temp_cartVO.setB_amount(rs.getInt("b_amount"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return temp_cartVO;
	}
	
	@Override
	public List<Temp_cartVO> getAll() {
		List<Temp_cartVO> list = new ArrayList<Temp_cartVO>();
		Temp_cartVO temp_cartVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dbCon.getCon();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp_cartVO = new Temp_cartVO();
				temp_cartVO.setMem_no(rs.getInt("mem_no"));
				temp_cartVO.setBook_no(rs.getInt("book_no"));
				temp_cartVO.setB_amount(rs.getInt("b_amount"));
				list.add(temp_cartVO); 
			}
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
		return list;
	}

	@Override
	public List<Shopping_CartVO> getAllByMem(Integer mem_no) {
		List<Shopping_CartVO> cartItems = new ArrayList<>();
		Shopping_CartVO vo = null;
//		System.out.println(GET_MEMBER_CART_STMT);
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, GET_MEMBER_CART_STMT,mem_no);){
				while (rs.next()) {
					vo = new Shopping_CartVO();
					vo.setM_No(mem_no);
					vo.setB_No(rs.getInt(1));
					vo.setB_Name(rs.getString(2));
					vo.setB_Author(rs.getString(3));	
					vo.setB_Pic(rs.getBytes(4));
					vo.setB_Price(rs.getInt(5));
					vo.setB_Qty(rs.getInt(6));
					vo.setB_Stock(rs.getInt(7));
					vo.setP_Name(rs.getString(8));
					vo.setP_Disc(rs.getFloat(9));
					vo.setP_Price(rs.getInt(10));
//					System.out.println(vo);
					cartItems.add(vo);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return cartItems;
	}

	@Override
	public int delBooks(Integer mem_no, Integer[] books) {
		int result = 0;
		StringBuilder bookno = new StringBuilder();
		bookno.append("book_no = ");
		bookno.append(books[0]);	
		if(books.length>1){ //要刪的書超過1本
			for(int i=1;i<books.length;i++){
				bookno.append(" or book_no = ");
				bookno.append(books[i]);	
			}
		}
		final String SQLStr = DELETE_BOOKS + bookno.toString();
//		System.out.println("指令"+SQLStr);
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(SQLStr)){
				pstmt.setInt(1, mem_no);
				result = pstmt.executeUpdate();
		}catch (SQLException se) {
				throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return result;
	}

	
	@Override
	public boolean update(Integer mem_no,List<Temp_cartVO> bookList){
		boolean result =false;
		try(Connection con = dbCon.getCon()){
			try{
				con.setAutoCommit(false);
				try(CallableStatement cs = con.prepareCall(INSERT_CS);){
					//更新的Batch
					for(Temp_cartVO vo : bookList){
						cs.setString(1, vo.getMem_no().toString());
						cs.setString(2, vo.getBook_no().toString());
						cs.setString(3, vo.getB_amount().toString());
						cs.addBatch();
					}
					cs.executeBatch(); //送出Batch
					con.commit();
					result = true;
				}catch(SQLException se){
					con.rollback();
					throw new RuntimeException("DB錯誤：" + se.getMessage()+"\n已取消更新");
				}
			}catch(SQLException se){
					throw new RuntimeException("DB錯誤：" + se.getMessage());
			}
			finally{
				try{
					con.setAutoCommit(true);
				}catch(SQLException se){
					throw new RuntimeException("DB錯誤：" + se.getMessage());
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return result;
	}
		
}
