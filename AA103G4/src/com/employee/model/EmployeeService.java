package com.employee.model;

import java.util.List;

public class EmployeeService {
	/** 介面 變數 **/
	EmployeeDAO_interface dao;

	/** 多型 **/
	public EmployeeService() {
		dao = new EmployeeDAO();
	}

	/** 查詢全部後台員工資料 **/
	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}

	/** 查詢一筆後台員工資料 **/
	public EmployeeVO getOneEmp(Integer emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	/** 新增一筆員工資料 **/
	public EmployeeVO addEmp(String emp_name, String emp_acc, String emp_psw) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpName(emp_name);
		employeeVO.setEmpAcc(emp_acc);
		employeeVO.setEmpPsw(emp_psw);
		dao.insert(employeeVO);
		return employeeVO;
	}

	/** 修改員工資料 **/
	public EmployeeVO updateEmp(String emp_name, String emp_psw) {

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpName(emp_name);
		employeeVO.setEmpPsw(emp_psw);
		dao.update(employeeVO);

		return employeeVO;
	}

	/** 刪除一筆員工資料 **/
	public void deleteEmp(Integer emp_no) {
		dao.delete(emp_no);
	}

	/** 員工使用信箱及密碼登入，可以查詢到他的個人資料 **/
	public EmployeeVO selectLogin(String emp_acc, String emp_psw) {
		return dao.selectLogin(emp_acc, emp_psw);
	}

	/** Ajax 檢查帳號 **/
	public EmployeeVO AjaxaccLogin(String emp_acc) {
		return dao.AjaxaccLogin(emp_acc);
	}
}
