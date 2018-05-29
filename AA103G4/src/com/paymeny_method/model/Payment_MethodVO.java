package com.paymeny_method.model;

public class Payment_MethodVO {
	private Integer p_Code;
	private String p_Desc;
	
	public Payment_MethodVO(){
		
	}
	
	public Payment_MethodVO(Integer p_Code){
		this.p_Code = p_Code;
	}
	
	public Integer getP_Code() {
		return p_Code;
	}
	public void setP_Code(Integer p_Code) {
		this.p_Code = p_Code;
	}
	public String getP_Desc() {
		return p_Desc;
	}
	public void setP_Desc(String p_Desc) {
		this.p_Desc = p_Desc;
	}
}
