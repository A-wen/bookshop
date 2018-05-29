package com.cou.model;
import java.io.Serializable;
import java.sql.Date;

public class CouponVO implements Serializable{
	
	private int cou_no;
	private Date cou_start;
	private Date cou_end;
	private double cou_dis;
	private String cou_exp;
	private int mem_no;
	private String cou_name;
	
	
	public int getCouno() {
		return cou_no;
	}
	public void setCouno(int cou_no) {
		this.cou_no = cou_no;
	}
	public Date getCoustart() {
		return cou_start;
	}
	public void setCoustart(Date cou_start) {
		this.cou_start = cou_start;
	}
	public Date getCouend() {
		return cou_end;
	}
	public void setCouend(Date cou_end) {
		this.cou_end = cou_end;
	}
	public double getCoudis() {
		return cou_dis;
	}
	public void setCoudis(double cou_dis) {
		this.cou_dis = cou_dis;
	}
	public String getCouexp() {
		return cou_exp;
	}
	public void setCouexp(String cou_exp) {
		this.cou_exp = cou_exp;
	}
	public int getMemno() {
		return mem_no;
	}
	public void setMemno(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getCouname() {
		return cou_name;
	}
	public void setCouname(String cou_name) {
		this.cou_name = cou_name;
	}
	
}
