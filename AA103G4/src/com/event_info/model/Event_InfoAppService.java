package com.event_info.model;

import java.util.List;

public class Event_InfoAppService {
	private Event_InfoAppDAO_Interface dao;
	
	public Event_InfoAppService(){
		dao = new Event_InfoAppDAO();
	}
	
	public List<Event_InfoVO> getAll(){
		return dao.getAll();
	}
	
	public Event_InfoVO findById(Integer e_No){
		return dao.findById(e_No);
	}
	
	public byte[] getImage(Integer e_No){
		return dao.getImage(e_No);
	}

}
