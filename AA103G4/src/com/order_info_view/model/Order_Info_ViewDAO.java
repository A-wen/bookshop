package com.order_info_view.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.cy.DBConFactory;
import util.cy.DBConnect;



public class Order_Info_ViewDAO implements Order_Info_ViewDAO_Interface{

	private static final String GET_ALL_STMT =
		"SELECT O_ID,MEM_NO,P_DESC,O_DESC,O_SUM,O_DATE,ADDR,TEL,D_DESC,D_NAME FROM ORDER_INFO_VIEW ORDER BY O_ID DESC";
	private static final String GET_ONE_STMT =
		"SELECT O_ID,MEM_NO,P_DESC,O_DESC,O_SUM,O_DATE,ADDR,TEL,D_DESC,D_NAME FROM ORDER_INFO_VIEW WHERE O_ID=?";
	private static final String GET_ORDER_BY_MEM_STMT =
		"SELECT O_ID,MEM_NO,P_DESC,O_DESC,O_SUM,O_DATE,ADDR,TEL,D_DESC,D_NAME FROM ORDER_INFO_VIEW WHERE MEM_NO=?";

	private DBConnect dbCon;
	
	public Order_Info_ViewDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	public Order_Info_ViewDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	@Override
	public Order_Info_ViewVO findByPrimaryKey (Integer o_id){
		Order_Info_ViewVO VO = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, GET_ONE_STMT,o_id)){
			while (rs.next()){	
				VO = new Order_Info_ViewVO();
				VO.setO_Id(rs.getInt(1));
				VO.setMem_No(rs.getInt(2));
				VO.setP_Desc(rs.getString(3));
				VO.setO_Desc(rs.getString(4));
				VO.setO_Sum(rs.getInt(5));
				VO.setO_Date(rs.getTimestamp(6));
				VO.setAddr(rs.getString(7));
				VO.setTel(rs.getString(8));
				VO.setD_Desc(rs.getString(9));
				VO.setD_Name(rs.getString(10));
				}
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
			return VO;	
	}
	
	@Override
	public List<Order_Info_ViewVO> getByMemNo(Integer mem_No){
		List<Order_Info_ViewVO> list = new ArrayList<Order_Info_ViewVO>();
		Order_Info_ViewVO VO = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, GET_ORDER_BY_MEM_STMT,mem_No)){
			while (rs.next()){	
				VO = new Order_Info_ViewVO();
				VO.setO_Id(rs.getInt(1));
				VO.setMem_No(rs.getInt(2));
				VO.setP_Desc(rs.getString(3));
				VO.setO_Desc(rs.getString(4));
				VO.setO_Sum(rs.getInt(5));
				VO.setO_Date(rs.getTimestamp(6));
				VO.setAddr(rs.getString(7));
				VO.setTel(rs.getString(8));
				VO.setD_Desc(rs.getString(9));
				VO.setD_Name(rs.getString(10));
				list.add(VO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return list;
	}
	
	@Override
	public List<Order_Info_ViewVO> getAll(){
		List<Order_Info_ViewVO> list = new ArrayList<Order_Info_ViewVO>();
		Order_Info_ViewVO VO = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, GET_ALL_STMT)){
			while (rs.next()){	
				VO = new Order_Info_ViewVO();
				VO.setO_Id(rs.getInt(1));
				VO.setMem_No(rs.getInt(2));
				VO.setP_Desc(rs.getString(3));
				VO.setO_Desc(rs.getString(4));
				VO.setO_Sum(rs.getInt(5));
				VO.setO_Date(rs.getTimestamp(6));
				VO.setAddr(rs.getString(7));
				VO.setTel(rs.getString(8));
				VO.setD_Desc(rs.getString(9));
				VO.setD_Name(rs.getString(10));
				list.add(VO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("DB錯誤：" + se.getMessage());
		}
		return list;
	}

}
