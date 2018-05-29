package com.event_photo.model;

import java.util.List;

public interface Event_PhotoDAO_Interface {
	public boolean insert(Event_PhotoVO event_PhotoVO);
	public boolean update(Event_PhotoVO event_PhotoVO);
	public boolean delete(Integer p_No);

	/**
	 * 取得Event_Photo內的特定照片
	 * @return Event_PhotoVO 特定照片
	 */
	public Event_PhotoVO findByPK(Integer p_No);
	
	/**
	 * 取得Event_Photo內的所有資料
	 * @return List<Event_PhotoVO>
	 */
	public List<Event_PhotoVO> getAll();
	
	/**
	 * 使用活動編號搜索，並取得該活動的所有照片
	 * @param e_No 活動編號
	 * @return List<Event_PhotoVO> 該活動的所有照片
	 */
	public List<Event_PhotoVO> getByE_No(Integer e_No);
		
	/**
	 * 傳入照片清單，依照照片排序編號排序後回傳
	 * @param List<Event_PhotoVO>
	 * @return List<Event_PhotoVO>
	 */
	public List<Event_PhotoVO> sortByE_No(List<Event_PhotoVO> photoList);

}
