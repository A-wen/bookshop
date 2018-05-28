package com.delivery_status.model;

public class Delivery_StatusVO {

	private Integer d_Code;
	private String d_Desc;
	
	public Delivery_StatusVO(){
		d_Code = 1;
	}
	
	public Delivery_StatusVO(Integer d_Code){
		this.d_Code = d_Code;
	}

	public Integer getD_Code() {
		return d_Code;
	}

	public void setD_Code(Integer d_Code) {
		this.d_Code = d_Code;
	}

	public String getD_Desc() {
		return d_Desc;
	}

	public void setD_Desc(String d_Desc) {
		this.d_Desc = d_Desc;
	}
	
	
	
	
}
