package com.cm.model;

import java.io.Serializable;
import java.sql.Date;

public class CmVO  implements Serializable{
	private Integer cm_no;
	private Integer cm_th;
	private String cm_name;
	private String cm_inv;
	private String cm_url;
	private Date cm_start;
	private Date cm_end;
	private byte[]cm_pic; 
	
	public Integer getCmNo() {
		return cm_no;
	}
	public void setCmNo(Integer cm_no) {
		this.cm_no = cm_no;
	}
	public Integer getCmTh() {
		return cm_th;
	}
	public void setCmTh(Integer cm_th) {
		this.cm_th = cm_th;
	}
	public String getCmName() {
		return cm_name;
	}
	public void setCmName(String cm_name) {
		this.cm_name = cm_name;
	}
	public String getCminv() {
		return cm_inv;
	}
	public void setCminv(String cm_inv) {
		this.cm_inv = cm_inv;
	}
	public String getCmUrl() {
		return cm_url;
	}
	public void setCmUrl(String cm_url) {
		this.cm_url = cm_url;
	}
	public Date getCmStart() {
		return cm_start;
	}
	public void setCmStart(Date cm_start) {
		this.cm_start = cm_start;
	}
	public Date getCmEnd() {
		return cm_end;
	}
	public void setCmEnd(Date cm_end) {
		this.cm_end = cm_end;
	}
	public byte[] getCmPic() {
		return cm_pic;
	}
	public void setCmPic(byte[] cm_pic) {
		this.cm_pic = cm_pic;
	}

	

}
