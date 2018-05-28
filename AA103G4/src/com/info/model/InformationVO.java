package com.info.model;

import java.sql.Date;

public class InformationVO implements java.io.Serializable{
    private Integer info_no;
    private Date info_term;
    private String info_exp;
    private String info_title;
    
    public Date getInfoterm() {
		return info_term;
	}
	public void setInfoterm(Date info_term) {
		this.info_term = info_term;
	}    
	public String getInfotitle() {
		return info_title;
	}
	public void setInfotitle(String info_title) {
		this.info_title = info_title;
	}
	
	public Integer getInfono() {
		return info_no;
	}
	public void setInfono(Integer info_no) {
		this.info_no = info_no;
	}
	public String getInfoexp() {
		return info_exp;
	}
	public void setInfoexp(String info_exp) {
		this.info_exp = info_exp;
	}
	@Override
	public String toString() {
		return "InformationVO [info_no=" + info_no + ", info_term=" + info_term + ", info_exp=" + info_exp
				+ ", info_title=" + info_title + "]";
	}
	
	

}