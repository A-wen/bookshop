package com.fun.model;

import java.io.Serializable;

public class FunVO implements Serializable {

	private Integer fun_no;
	private String fun_name;

//	@Override
//	public String toString() {
//		return "�\��s��:" + this.fun_no + "," + "�\��W��:" + this.fun_name + "\n";
//	}

	public Integer getFunno() {
		return fun_no;
	}

	public void setFunNo(Integer fun_no) {
		this.fun_no = fun_no;
	}

	public String getFunName() {
		return fun_name;
	}

	public void setFunName(String fun_name) {
		this.fun_name = fun_name;
	}
}
