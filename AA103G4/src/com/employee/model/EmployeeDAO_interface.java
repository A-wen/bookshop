package com.employee.model;

import java.util.List;


public interface EmployeeDAO_interface {
	/** 查詢一筆員工資料 **/
	public EmployeeVO findByPrimaryKey(Integer emp_no);

	/** 查詢全部員工資料 **/
	public List<EmployeeVO> getAll();

	/** 新增一筆員工資料 **/
	public void insert(EmployeeVO employeeVO);

	/** 修改一筆員工資料 **/
	public void update(EmployeeVO employeeVO);

	/** 刪除一筆員工資料 **/
	public void delete(Integer emp_no);
	
	/** 登入 **/
	public EmployeeVO selectLogin(String email, String passwd);
	
	public EmployeeVO AjaxaccLogin(String email);
}
