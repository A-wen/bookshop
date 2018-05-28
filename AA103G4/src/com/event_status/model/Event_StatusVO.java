package com.event_status.model;

import java.util.HashSet;
import java.util.Set;

import com.event_info.model.Event_InfoVO;

public class Event_StatusVO implements java.io.Serializable{

	private Integer s_No;
	private String s_Exp;
	private Set<Event_InfoVO> events = new HashSet<Event_InfoVO>();
	
	public Integer getS_No() {
		return s_No;
	}
	public void setS_No(Integer s_No) {
		this.s_No = s_No;
	}
	public String getS_Exp() {
		return s_Exp;
	}
	public void setS_Exp(String s_Exp) {
		this.s_Exp = s_Exp;
	}
		
	public Set<Event_InfoVO> getEvents() {
		return events;
	}
	public void setEvents(Set<Event_InfoVO> events) {
		this.events = events;
	}
	@Override
	public String toString() {
		return "Event_StatusVO [s_No=" + s_No + ", s_Exp=" + s_Exp + "]";
	}
}
