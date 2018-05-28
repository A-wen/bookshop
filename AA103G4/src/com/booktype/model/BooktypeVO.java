package com.booktype.model;

public class BooktypeVO implements java.io.Serializable {
	private Integer type_no;
	private String type_name;

	public Integer getType_no() {
		return type_no;
	}
	
	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
}
