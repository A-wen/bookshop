package com.event_info.model;

import java.sql.Timestamp;
import java.util.Set;

import com.event_member.model.Event_MemberVO;
import com.event_status.model.Event_StatusVO;
import com.s_gro_info.model.S_gro_infoVO;

public class Event_InfoVO implements java.io.Serializable{
	private Integer e_No;
	private S_gro_infoVO s_gro_info;
	private String e_Name;
	private Event_StatusVO	event_Status;
	private String e_Intro;
	private String e_Desc;
	private Timestamp e_Date;
	private String	e_Loc;
	private String	e_Addr;
	private Integer e_Limit;
	private String e_Img;
	private Set<Event_MemberVO> eMems;
	
	
	public Integer getE_No() {
		return e_No;
	}
	public void setE_No(Integer e_No) {
		this.e_No = e_No;
	}

	public S_gro_infoVO getS_gro_info() {
		return s_gro_info;
	}
	public void setS_gro_info(S_gro_infoVO s_gro_info) {
		this.s_gro_info = s_gro_info;
	}
	
	public String getE_Name() {
		return e_Name;
	}
	public void setE_Name(String e_Name) {
		this.e_Name = e_Name;
	}
	public Event_StatusVO getEvent_Status() {
		return event_Status;
	}
	public void setEvent_Status(Event_StatusVO event_Status) {
		this.event_Status = event_Status;
	}
	public String getE_Intro() {
		return e_Intro;
	}
	public void setE_Intro(String e_Intro) {
		this.e_Intro = e_Intro;
	}
	public String getE_Desc() {
		return e_Desc;
	}
	public void setE_Desc(String e_Desc) {
		this.e_Desc = e_Desc;
	}
	public Timestamp getE_Date() {
		return e_Date;
	}
	public void setE_Date(Timestamp e_Date) {
		this.e_Date = e_Date;
	}
	public String getE_Loc() {
		return e_Loc;
	}
	public void setE_Loc(String e_Loc) {
		this.e_Loc = e_Loc;
	}
	public String getE_Addr() {
		return e_Addr;
	}
	public void setE_Addr(String e_Addr) {
		this.e_Addr = e_Addr;
	}
	public Integer getE_Limit() {
		return e_Limit;
	}
	public void setE_Limit(Integer e_Limit) {
		this.e_Limit = e_Limit;
	}
	public String getE_Img() {
		return e_Img;
	}
	public void setE_Img(String e_Img) {
		this.e_Img = e_Img;
	}
	public Set<Event_MemberVO> geteMems() {
		return eMems;
	}
	public void seteMems(Set<Event_MemberVO> eMems) {
		this.eMems = eMems;
	}
	
}
