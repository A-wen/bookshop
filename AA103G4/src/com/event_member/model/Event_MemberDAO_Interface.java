package com.event_member.model;

import java.util.List;
import java.util.Map;

public interface Event_MemberDAO_Interface {


	/**
	 * 傳入VO,新增或修改DB
	 * @param event_memberVO
	 * @return 操作是否成功
	 */
	public int saveOrUpdate(Event_MemberVO event_memberVO);
	
	/**
	 * 傳入活動編號與會員編號，找出某活動內某會員的參與狀況
	 * @param e_No 活動編號
	 * @param m_No 會員編號
	 * @return Event_MemberVO,找出某活動內某會員的參與狀況
	 */
	public Event_MemberVO findByPK(Integer e_No,Integer m_No);
	
	/**
	 * 傳入活動編號，找出該活動有報名或有興趣的會員清單
	 * @param e_No 活動編號
	 * @return List<Event_MemberVO> 會員清單
	 */
	public List<Event_MemberVO> getMemberList(Integer e_No);
	
	/**
	 * 傳入活動編號，傳回活動報名狀態
	 * @param e_No
	 * @return MAP<狀態,人數>
	 */
	public Map<String,Integer> getEventMemberCount(Integer e_No);
	
	/**
	 * 傳入會員編號，找出該會員有報名或有興趣的活動清單
	 * @param e_No 會員編號
	 * @return List<Event_MemberVO> 該會員有報名過的活動清單
	 */
	public List<Event_MemberVO> getEventList(Integer m_No); 
	
	
}
