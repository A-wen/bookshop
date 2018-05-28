package com.event_photo.model;

public class Event_PhotoVO implements java.io.Serializable{
	private Integer p_No;
	private Integer e_No;
	private String p_Name;
	private Integer p_Seq;
	
	public Integer getP_No() {
		return p_No;
	}
	public void setP_No(Integer p_No) {
		this.p_No = p_No;
	}
	public Integer getE_No() {
		return e_No;
	}
	public void setE_No(Integer e_No) {
		this.e_No = e_No;
	}
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
	public Integer getP_Seq() {
		return p_Seq;
	}
	public void setP_Seq(Integer p_Seq) {
		this.p_Seq = p_Seq;
	}
	@Override
	public String toString() {
		return "Event_PhotoVO [p_No=" + p_No + ", e_No=" + e_No + ", p_Name=" + p_Name + ", p_Seq=" + p_Seq + "]";
	}
}
