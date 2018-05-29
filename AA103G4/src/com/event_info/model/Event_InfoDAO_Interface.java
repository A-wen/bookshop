package com.event_info.model;

import java.util.List;

public interface Event_InfoDAO_Interface {
	
	/**
	 * 新增資料庫內容
	 * @param event_InfoVO 活動事件物件
	 * @return 影響的列數
	 */
	public boolean insert(Event_InfoVO event_InfoVO);
	
	/**
	 * 更新資料庫內容(圖片未更新)
	 * @param event_InfoVO 活動事件物件
	 * @return 影響的列數
	 */
	public boolean update(Event_InfoVO event_InfoVO);
	
	/**
	 * 更新資料庫內容(圖片有更新)
	 * @param event_InfoVO 活動事件物件
	 * @return 影響的列數
	 */
	public boolean update(Event_InfoVO event_InfoVO,String oriImg,String fileLoc);
	
	
	/**
	 * 因為不能給使用者刪除，所以實際上為更改狀態碼到5
	 * @param e_No 要更改的活動編號
	 * @return 影響的列數
	 */
	public boolean delete(Integer e_No);
	
	/**
	 * 使用活動編號尋找活動事件物件
	 * @param e_No 活動編號
	 * @return 活動事件物件
	 */
	public Event_InfoVO findByPK(Integer e_No);
	
	/**
	 * 取得所有活動資料(包含狀態碼=5)
	 * @return List<Event_infoVO>
	 */
	public List<Event_InfoVO> getAll();
	
	/**
	 * 使用讀書會編號搜索，取得該讀書會的所有活動(不包含狀態碼=5)
	 * @param 讀書會編號
	 * @return 該讀書會的所有活動
	 */
	public List<Event_InfoVO> getByG_No(Integer g_No);
	
	/**
	 * 使用讀書會編號與關鍵字搜索活動名稱
	 * @param g_No 讀書會編號
	 * @param keyword 關鍵字
	 * @return 搜索結果(不包含狀態碼=5）
	 */
	public List<Event_InfoVO> getByKeyword(Integer g_No,String keyword);
	
	/**
	 * 使用會員編號搜索，並取得該會員所參加的所有活動(不包含狀態碼=5)
	 * @param M_NO 會員編號
	 * @return List<Event_infoVO>
	 */
	
	public List<Event_InfoVO> getByM_No(Integer m_No);
	
	/**
	 * 使用讀書會編號，搜索N天內讀書會的所有活動
	 * @param g_N0 讀書會編號
	 * @param days 要搜索的N天
	 * @return 活動清單
	 */
	public List<Event_InfoVO> getByNDay(Integer g_No,Integer days);
	
	/**
	 * 取得所有讀書會活動數量
	 * @return
	 */
	public Long eventCount();
	
	/** 
	 * 傳入開始/結束編號。取得特定數量的讀書會活動清單 
	 * @param startNum 開始編號
	 * @param endNumber 每頁要顯示的數量
	 * @return 讀書會活動集合
	 */
	public List<Event_InfoVO> getByPage(Integer startNum,Integer rows);
}
