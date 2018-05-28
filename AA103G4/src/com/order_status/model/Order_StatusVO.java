package com.order_status.model;

public class Order_StatusVO {
	private Integer o_Code;
	private String o_Desc;
	
	public Order_StatusVO(){
		
	}
	public Order_StatusVO(int status){
		o_Code = status;
	}
	
	public Integer getO_Code() {
		return o_Code;
	}
	public void setO_Code(Integer o_Code) {
		this.o_Code = o_Code;
	}
	public String getO_Desc() {
		return o_Desc;
	}
	public void setO_Desc(String o_Desc) {
		this.o_Desc = o_Desc;
	}
	
	
}
