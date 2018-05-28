package com.event_photo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.event_member.model.Event_MemberVO;

import util.cy.DBConFactory;
import util.cy.DBConnect;


public class Event_PhotoDAO implements Event_PhotoDAO_Interface {

	//定義SQL指令
	private static final String INSERT_STMT = 
			"INSERT INTO EVENT_PHOTO VALUES(EVENT_PHOTO_SEQ.NEXTVAL,?,?,?)";
	private static final String UPDATE_STMT =
			"UPDATE EVENT_PHOTO SET E_NO = ? , P_NAME = ? , P_SEQ = ? WHERE P_NO = ?";
	private static final String DELET_STMT =
			"DELETE FROM EVENT_PHOTO WHERE P_NO = ?";
	private static final String SELECT_ALL_STMT =
			"SELECT P_NO,E_NO,P_NAME,P_SEQ FROM EVENT_PHOTO";
	private static final String SELECT_PK_STMT =
			"SELECT P_NO,E_NO,P_NAME,P_SEQ FROM EVENT_PHOTO WHERE P_NO = ?";
	private static final String SELECT_EVENT_PHOTO_STMT =
			"SELECT P_NO,E_NO,P_NAME,P_SEQ FROM EVENT_PHOTO WHERE E_NO = ?";
	
	private DBConnect dbCon;
	
	/**
	 * 預設建構子，使用JNDI連線
	 */
	public Event_PhotoDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	/**
	 * 傳入JDBC或JNDI來設定要使用哪種連線方式
	 * @param conMethod "JDBC"使用JDBC連線 "JNDI"使用連線池
	 */
	public Event_PhotoDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	@Override
	public boolean insert(Event_PhotoVO event_PhotoVO) {
		int result=0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)){
				pstmt.setInt(1,event_PhotoVO.getE_No());
				pstmt.setString(2,event_PhotoVO.getP_Name());
				pstmt.setInt(3, event_PhotoVO.getP_Seq());
				result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result==1){return true;}
		else{return false;}
	}

	@Override
	public boolean update(Event_PhotoVO event_PhotoVO) {
		int result=0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT)) {
			pstmt.setInt(1,event_PhotoVO.getE_No());
			pstmt.setString(2,event_PhotoVO.getP_Name());
			pstmt.setInt(3, event_PhotoVO.getP_Seq());
			pstmt.setInt(4, event_PhotoVO.getP_No());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result==1){return true;}
		else{return false;}
	}

	@Override
	public boolean delete(Integer p_No) {
		int result=0;
		try(Connection con = dbCon.getCon();
			PreparedStatement pstmt = con.prepareStatement(DELET_STMT)){
			pstmt.setInt(1,p_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result==1){return true;}
		else{return false;}
	}

	@Override
	public Event_PhotoVO findByPK(Integer p_No) {
		Event_PhotoVO event_PhotoVO = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_PK_STMT,p_No);){
			while(rs.next()){
				event_PhotoVO = setVO(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event_PhotoVO;
	}
	
	@Override
	public List<Event_PhotoVO> getAll() {
		List<Event_PhotoVO> allPhotos = new ArrayList();
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_ALL_STMT)) {
			while(rs.next()){
				Event_PhotoVO photoVO = setVO(rs);
				allPhotos.add(photoVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPhotos;
	}
	
	@Override
	public List<Event_PhotoVO> getByE_No(Integer e_No) {
		List<Event_PhotoVO> eventPhotos = new ArrayList<>();
		
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_EVENT_PHOTO_STMT,e_No)){
			while(rs.next()){
				Event_PhotoVO photoVO = setVO(rs);
				eventPhotos.add(photoVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventPhotos;
	}
	//pending
	@Override
	public List<Event_PhotoVO> sortByE_No(List<Event_PhotoVO> photoList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//設定VO內容用
	private Event_PhotoVO setVO(ResultSet rs) throws SQLException {
		Event_PhotoVO vo = new Event_PhotoVO();
		vo.setP_No(rs.getInt(1));
		vo.setE_No(rs.getInt(2));
		vo.setP_Name(rs.getString(3));
		vo.setP_Seq(rs.getInt(4));
		return vo;
	}

}
