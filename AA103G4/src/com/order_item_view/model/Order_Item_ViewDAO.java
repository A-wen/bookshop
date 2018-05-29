package com.order_item_view.model;

import java.util.*;

import util.cy.DBConFactory;
import util.cy.DBConnect;

import java.sql.*;

public class Order_Item_ViewDAO implements Order_Item_ViewDAO_Interface{

	private static final String GET_GET_BY_O_ID_STMT =
		"SELECT O_ID,BOOK_NO,BOOK_NAME,BOOK_PRICE,O_AMOUNT FROM ORDER_ITEM_VIEW WHERE O_ID=? ORDER BY BOOK_NO";

	private DBConnect dbCon;
	
	public Order_Item_ViewDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	public Order_Item_ViewDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	
	@Override
	public List<Order_Item_ViewVO> getByOId(Integer o_id) {
		List<Order_Item_ViewVO> list = new ArrayList<Order_Item_ViewVO>();
		Order_Item_ViewVO VO = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con,GET_GET_BY_O_ID_STMT,o_id)){
			while (rs.next()){	
				VO = new Order_Item_ViewVO();
				VO.setO_Id(rs.getInt(1));
				VO.setBook_No(rs.getInt(2));
				VO.setBook_Name(rs.getString(3));
				VO.setBook_Price(rs.getInt(4));
				VO.setO_Amount(rs.getInt(5));
				list.add(VO); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return list;
	}
}
