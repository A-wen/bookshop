package com.chat_log.model;

import java.util.List;

public interface Chat_LogDAO_Interface {

	public int insert(Chat_LogVO chat_LogVO);
	
	/**
	 * 傳入會員編號，找出該會員在所有讀書會聊天室內的發言紀錄
	 * @param m_No 會員編號
	 * @return List<Chat_LogVO> 該會員在所有讀書會的發言紀錄
	 */
	public List<Chat_LogVO> getMemberChatLog(Integer m_No);
	
	/**
	 * 傳入讀書會編號，找出該讀書會的所有會員再聊天室內的發言紀錄
	 * @param g_No 讀書會編號
	 * @return List<Chat_LogVO> 該讀書會的所有會員發言紀錄
	 */
	public List<Chat_LogVO> getGroupChatLog(Integer g_No); 
	
	/**
	 * 傳入讀書會編號與要顯示的數量，回傳該讀書會聊天室設定數量的集合
	 */
	public List<Chat_LogVO> getGroupChat(Integer g_No); 
}
