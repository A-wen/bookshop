package com.employee.model;

import java.io.Serializable;

public class EmployeeVO implements Serializable{

	private Integer emp_no;
	private String emp_name;
	private String emp_acc;
	private String emp_psw;

//	@Override
//	public String toString() {
//		return this.emp_no + this.emp_name + this.emp_acc + this.emp_psw + "\n";
//	}
	

	public Integer getEmpno() {
		return emp_no;
	}

	public void setEmpno(Integer emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmpName() { 
		return emp_name;
	}

	public void setEmpName(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmpAcc() {
		return emp_acc;
	}

	public void setEmpAcc(String emp_acc) {
		this.emp_acc = emp_acc;
	}

	public String getEmpPsw() {
		return emp_psw;
	}

	public void setEmpPsw(String emp_psw) {
		this.emp_psw = emp_psw;
	}

}
