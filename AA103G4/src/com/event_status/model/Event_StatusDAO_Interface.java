package com.event_status.model;

import java.util.List;

public interface Event_StatusDAO_Interface {

	/**
	 * 傳入狀態碼，取得狀態物件
	 * @param pk 狀態碼
	 * @return 狀態物件
	 */
	public Event_StatusVO findByPK(Integer e_No);
	
	/**
	 * 取得全部狀態物件
	 * @return 全部狀態物件
	 */
	public List<Event_StatusVO> getAll();
}
