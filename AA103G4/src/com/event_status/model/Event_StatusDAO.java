package com.event_status.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.cy.DBConFactory;
import util.cy.DBConnect;

public class Event_StatusDAO implements Event_StatusDAO_Interface {

	private static final String SELECT_ALL_STMT =
			"SELECT S_NO,S_EXP FROM EVENT_STATUS";
	private static final String SELECT_BY_S_NO_STMT=
			"SELECT S_NO,S_EXP FROM EVENT_STATUS WHERE S_NO=?";
	
	private DBConnect dbCon;
	
	/**
	 * 預設建構子，使用JNDI連線
	 */
	public Event_StatusDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	/**
	 * 可傳JDBC/JNDI選擇連線方法
	 * @param conMethod
	 */
	public Event_StatusDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	@Override
	public Event_StatusVO findByPK(Integer e_No) {
		Event_StatusVO vo=null;
		try(
			Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_BY_S_NO_STMT,e_No)){
				while(rs.next()){
					vo = setVO(rs);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return vo;
	}

	@Override
	public List<Event_StatusVO> getAll() {
		List<Event_StatusVO> allStatus = new ArrayList<>();
		Event_StatusVO vo;
		try(
			Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_ALL_STMT)){
				while(rs.next()){
						allStatus.add(setVO(rs));
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allStatus;
	}
	
	private Event_StatusVO setVO(ResultSet rs)throws SQLException{
		Event_StatusVO vo = new Event_StatusVO();
		vo.setS_No(rs.getInt(1));
		vo.setS_Exp(rs.getString(2));
		return vo;
	}
	

}
