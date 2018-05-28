package com.person_msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import util.cy.DBConFactory;
import util.cy.DBConnect;

public class Person_MsgDAO implements Person_MsgDAO_Interface {

	//SQL指令
	private static String INSERT_STMT = 
			"INSERT INTO PERSON_MSG VALUES(PERSON_MSG_SEQ.NEXTVAL,?,1,?,1,?,CURRENT_TIMESTAMP)";
	private static String SELECT_BY_USER_STMT =
			"SELECT DISTINCT PM_SEND,PM_RECV FROM PERSON_MSG "
			+"WHERE (PM_RECV = ? AND R_STATUS = 1) or (PM_SEND = ? AND S_STATUS = 1)";
	private static String SELECT_CONV_BY_USERS_STMT =
			"SELECT PM_NO,PM_SEND,S_STATUS,PM_RECV,R_STATUS,PM_CONTENT,PM_DATE from ("
					+ "SELECT * from PERSON_MSG "
					+ "WHERE (PM_SEND = ? AND PM_RECV = ? AND S_STATUS = 1) "
					+ " OR (PM_RECV = ?  AND PM_SEND = ? AND R_STATUS = 1) "
					+ "ORDER BY PM_DATE DESC) "
			+ "WHERE ROWNUM <= 10";
	
	//連線物件
	private DBConnect dbCon;
	
	/**
	 * 預設建構子會設定JNDI連線
	 */
	public Person_MsgDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	/**
	 * 傳入JDBC或JNDI建立連線
	 * @param ConMethod
	 */
	public Person_MsgDAO(String ConMethod){
		dbCon = DBConFactory.createCon(ConMethod);
	}
	
	@Override
	public boolean insert(Person_MsgVO person_MsgVO) {
		int result=0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
			pstmt.setInt(1, person_MsgVO.getPm_Send());
			pstmt.setInt(2, person_MsgVO.getPm_Recv());
			pstmt.setString(3, person_MsgVO.getPm_Content());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result==1){return true;}
		else{return false;}
	}

	@Override
	public int archive(Integer PM_Send, Integer PM_Recv) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Set<Integer> getByUser(Integer member) {
		Connection con = dbCon.getCon();
		Set<Integer> recvTarget = new HashSet<Integer>();
		try {
			ResultSet rs = dbCon.query(con, SELECT_BY_USER_STMT,member,member);
			while (rs.next()){
				recvTarget.add(rs.getInt(1));
				recvTarget.add(rs.getInt(2));
			}
			recvTarget.remove(member);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return recvTarget;
	}
	
	@Override
	public Stack<Person_MsgVO> getByConv(Integer PM_Send, Integer PM_Recv) {
		Connection con = dbCon.getCon();
		Stack<Person_MsgVO> dialog = new Stack<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_CONV_BY_USERS_STMT);
			pstmt.setInt(1, PM_Send);
			pstmt.setInt(2, PM_Recv);
			pstmt.setInt(3, PM_Send);
			pstmt.setInt(4, PM_Recv);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				Person_MsgVO vo = setVO(rs);
				dialog.push(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dialog;
	}	
	
	private Person_MsgVO setVO(ResultSet rs) throws SQLException {
		Person_MsgVO vo = new Person_MsgVO();
		vo.setPm_No(rs.getInt(1));
		vo.setPm_Send(rs.getInt(2));	
		vo.setS_Status(rs.getInt(3));
		vo.setPm_Recv(rs.getInt(4));
		vo.setR_Status(rs.getInt(5));
		vo.setPm_Content(rs.getString(6));
		vo.setPm_Date(rs.getTimestamp(7));
		return vo;

	}

	




}
