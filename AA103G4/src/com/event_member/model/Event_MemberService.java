package com.event_member.model;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.event_info.controller.EventInfoServlet;
import com.event_info.model.Event_InfoVO;
import com.mem.model.MemVO;

public class Event_MemberService {

	private static Logger logger = Logger.getLogger(Event_MemberService.class);
	private Event_MemberDAO_Interface DAO;
	
	
	public Event_MemberService(){
		DAO = new Event_MemberDAO();
	}
	
	/**
	 * 傳入活動編號與會員編號查詢參加狀態
	 * @param e_No 活動編號
	 * @param m_No 會員編號
	 * @return 所選活動中，該會員的參加狀態
	 */
	public Event_MemberVO findByPK(Integer e_No, Integer m_No){
		return DAO.findByPK(e_No, m_No);
	}
	
	public int insert(Integer e_No, Integer m_No, Integer m_Status){
		Event_MemberVO event_MemberVO = setEventMemVO(e_No, m_No, m_Status);
		return DAO.saveOrUpdate(event_MemberVO);
	}
	
	public int update(Integer e_No, Integer m_No, Integer m_Status){
		Event_MemberVO event_MemberVO = setEventMemVO(e_No, m_No, m_Status);
		return DAO.saveOrUpdate(event_MemberVO);
	}
	
	public int delete(Integer e_No,Integer m_No){
		Event_MemberVO event_MemberVO = setEventMemVO(e_No, m_No, 3);
		return DAO.saveOrUpdate(event_MemberVO);
	}
	
	public List<Event_MemberVO> getMemberList(Integer e_No){
		return DAO.getMemberList(e_No);
	}
	
	public List<Event_MemberVO> getEventList(Integer m_No){
		return DAO.getEventList(m_No);
	}
	
	public Map<String,Integer> getEventMemberCount(Integer e_No){
		return DAO.getEventMemberCount(e_No);
	}
	
	private Event_MemberVO setEventMemVO(Integer e_No, Integer m_No, Integer m_Status){
		Event_MemberVO eventMemVO = new Event_MemberVO();
		MemVO memVO = new MemVO();
		Event_InfoVO eventInfoVO = new Event_InfoVO();
		memVO.setMem_no(m_No);
		eventInfoVO.setE_No(e_No);
		eventMemVO.setMemVO(memVO);
		eventMemVO.setEvent_InfoVO(eventInfoVO);
		eventMemVO.setM_Status(m_Status);
		return eventMemVO;
	}
}
