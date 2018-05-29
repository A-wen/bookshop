package com.ca.model;

import java.sql.Date;

public class ClassifiedAdsVO implements java.io.Serializable{
	
	private Integer ca_no;
	private Integer type_no;
	private Integer book_no;
	private Date ca_start;
	private Date ca_end;
	private Integer ca_th;
	private String ca_name;
	
	public Integer getCano() {
		return ca_no;
	}
	public void setCano(Integer ca_no) {
		this.ca_no = ca_no;
	}
	public Integer getTypeno() {
		return type_no;
	}
	public void setTypeno(Integer type_no) {
		this.type_no = type_no;
	}
	public Integer getBookno() {
		return book_no;
	}
	public void setBookno(Integer book_no) {
		this.book_no = book_no;
	}
	public Date getCastart() {
		return ca_start;
	}
	public void setCastart(Date ca_start) {
		this.ca_start = ca_start;
	}
	public Date getCaend() {
		return ca_end;
	}
	public void setCaend(Date ca_end) {
		this.ca_end = ca_end;
	}
	public Integer getCath() {
		return ca_th;
	}
	public void setCath(Integer ca_th) {
		this.ca_th = ca_th;
	}
	public String getCaname() {
		return ca_name;
	}
	public void setCaname(String ca_name) {
		this.ca_name = ca_name;
	}
	
	
}