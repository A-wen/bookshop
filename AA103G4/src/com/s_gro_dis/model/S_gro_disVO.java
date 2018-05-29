package com.s_gro_dis.model;

import java.sql.Date;

public class S_gro_disVO implements java.io.Serializable{
	private Integer dis_ar_no;
	private Integer s_gro_no;
	private Integer mem_no;
	private String title;
	private String dis_con;
	private Date ht_date;
	public Integer getDis_ar_no() {
		return dis_ar_no;
	}
	public void setDis_ar_no(Integer dis_ar_no) {
		this.dis_ar_no = dis_ar_no;
	}
	public Integer getS_gro_no() {
		return s_gro_no;
	}
	public void setS_gro_no(Integer s_gro_no) {
		this.s_gro_no = s_gro_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDis_con() {
		return dis_con;
	}
	public void setDis_con(String dis_con) {
		this.dis_con = dis_con;
	}
	public Date getHt_date() {
		return ht_date;
	}
	public void setHt_date(Date ht_date) {
		this.ht_date = ht_date;
	}
}
