package com.competence.model;

import java.io.Serializable;

public class CompetenceVO implements Serializable {

	private Integer emp_no;
	private Integer fun_no;

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "擁有權限(功能編號):" + this.fun_no + "\n";
//	}

	public Integer getEmpNo() {
		return emp_no;
	}

	public void setEmpNo(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getFunNo() {
		return fun_no;
	}

	public void setFunNo(Integer fun_no) {
		this.fun_no = fun_no;
	}
}
