package com.order_info_view.model;

import java.sql.Timestamp;

public class Order_Info_ViewVO implements java.io.Serializable{

	private Integer o_Id; 
	private Integer mem_No;
	private String p_Desc;
	private String o_Desc;
	private Integer o_Sum;
	private Timestamp o_Date;
	private String addr;
	private String tel;
	private String d_Desc;
	private String d_Name;
	
	public Integer getO_Id() {
		return o_Id;
	}
	public void setO_Id(Integer o_Id) {
		this.o_Id = o_Id;
	}
	public Integer getMem_No() {
		return mem_No;
	}
	public void setMem_No(Integer mem_No) {
		this.mem_No = mem_No;
	}
	public String getP_Desc() {
		return p_Desc;
	}
	public void setP_Desc(String p_Desc) {
		this.p_Desc = p_Desc;
	}
	public String getO_Desc() {
		return o_Desc;
	}
	public void setO_Desc(String o_Desc) {
		this.o_Desc = o_Desc;
	}
	public Integer getO_Sum() {
		return o_Sum;
	}
	public void setO_Sum(Integer o_Sum) {
		this.o_Sum = o_Sum;
	}
	public Timestamp getO_Date() {
		return o_Date;
	}
	public void setO_Date(Timestamp o_Date) {
		this.o_Date = o_Date;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getD_Desc() {
		return d_Desc;
	}
	public void setD_Desc(String d_Desc) {
		this.d_Desc = d_Desc;
	}
	public String getD_Name() {
		return d_Name;
	}
	public void setD_Name(String d_Name) {
		this.d_Name = d_Name;
	}
	@Override
	public String toString() {
		return "Order_Info_ViewVO [o_Id=" + o_Id + ", mem_No=" + mem_No + ", p_Desc=" + p_Desc + ", o_Desc=" + o_Desc
				+ ", o_Sum=" + o_Sum + ", o_Date=" + o_Date + ", addr=" + addr + ", tel=" + tel + ", d_Desc=" + d_Desc
				+ ", d_Name=" + d_Name + "]";
	}

}
