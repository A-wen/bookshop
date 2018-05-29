package com.event_status.model;

import java.util.List;

public class Event_StatusService {
	
	private Event_StatusDAO_Interface DAO;
	
	public Event_StatusService(){
		DAO = new Event_StatusDAO("JNDI");
	}
	
	public Event_StatusVO findByPK(Integer e_No){
		return DAO.findByPK(e_No);
	}
	
	public List<Event_StatusVO> getAll(){
		return DAO.getAll();
	}
}
