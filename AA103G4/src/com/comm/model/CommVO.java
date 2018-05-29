package com.comm.model;

import java.sql.Date;

public class CommVO implements java.io.Serializable{
	private Integer comm_no;
	private Integer book_no;
	private Integer mem_no;
	private String comm_desc;
	private Integer comm_level;
	private Date comm_date;
	
	public Integer getComm_no() {
		return comm_no;
	}
	public void setComm_no(Integer comm_no) {
		this.comm_no = comm_no;
	}
	public Integer getBook_no() {
		return book_no;
	}
	public void setBook_no(Integer book_no) {
		this.book_no = book_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getComm_desc() {
		return comm_desc;
	}
	public void setComm_desc(String comm_desc) {
		this.comm_desc = comm_desc;
	}
	public Integer getComm_level() {
		return comm_level;
	}
	public void setComm_level(Integer comm_level) {
		this.comm_level = comm_level;
	}
	public Date getComm_date() {
		return comm_date;
	}
	public void setComm_date(Date comm_date) {
		this.comm_date = comm_date;
	}
	
}
