package com.s_gro_info.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.event_info.model.Event_InfoVO;


public class S_gro_infoVO implements java.io.Serializable{
	private Integer s_gro_no;
	private String s_gro_name;
	private String s_con;
	private Integer mem_no;
	private Integer cs_no;
	private Date cre_date;
	private String s_gro_sta;
	private Set<Event_InfoVO> events = new HashSet<Event_InfoVO>();
	
	
	public Integer getS_gro_no() {
		return s_gro_no;
	}
	public void setS_gro_no(Integer s_gro_no) {
		this.s_gro_no = s_gro_no;
	}
	public String getS_gro_name() {
		return s_gro_name;
	}
	public void setS_gro_name(String s_gro_name) {
		this.s_gro_name = s_gro_name;
	}
	public String getS_con() {
		return s_con;
	}
	public void setS_con(String s_con) {
		this.s_con = s_con;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getCs_no() {
		return cs_no;
	}
	public void setCs_no(Integer cs_no) {
		this.cs_no = cs_no;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}
	public String getS_gro_sta() {
		return s_gro_sta;
	}
	public void setS_gro_sta(String s_gro_sta) {
		this.s_gro_sta = s_gro_sta;
	}
	public Set<Event_InfoVO> getEvents() {
		return events;
	}
	public void setEvents(Set<Event_InfoVO> events) {
		this.events = events;
	}
	
	
}
