package com.event_photo.model;

public class Event_PhotoService {
	
	private Event_PhotoDAO_Interface DAO;
	
	public Event_PhotoService(){
		DAO = new Event_PhotoDAO("JNDI");
	}
	
}
