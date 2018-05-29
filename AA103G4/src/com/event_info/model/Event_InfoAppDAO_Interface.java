package com.event_info.model;

import java.util.List;


public interface Event_InfoAppDAO_Interface {

	public void insert(Event_InfoVO event_InfoVO);

	public void update(Event_InfoVO event_InfoVO);

	public void delete(Integer e_No);

	Event_InfoVO findById(Integer e_No);

	List<Event_InfoVO> getAll();

	byte[] getImage(Integer e_No);
}
