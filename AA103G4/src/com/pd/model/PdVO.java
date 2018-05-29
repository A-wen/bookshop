package com.pd.model;

import java.sql.Date;

public class PdVO implements java.io.Serializable{
	private Integer pd_no;
	private String pd_name;
	private String pd_desc;
	private Date startdate;
	private Date enddate;
	private Double discount;
	
	public Integer getPd_no() {
		return pd_no;
	}
	public void setPd_no(Integer pd_no) {
		this.pd_no = pd_no;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getPd_desc() {
		return pd_desc;
	}
	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
