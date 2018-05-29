package com.competence.model;

import java.util.List;

public interface CompetenceDAO_interface {


	/** 修改 **/
	public void update(Integer new_no, Integer emp_no, Integer fun_no);

	/** 刪除 **/
	public void delete(Integer emp_no, Integer fun_no);

	/** 刪全部 **/
	public void deleteALL(Integer emp_no);

	/** 藉由功能編號找到權限 **/
	public List<CompetenceVO> findByFun(Integer fun_no);

	/** 藉由員工編號找到權限**/
	public List<CompetenceVO> findByEmp(Integer emp_no);

	/** 找所有權縣員工編號及功能編號 **/
	public List<CompetenceVO> getAll();

	/** 藉由員工編號找到功能編號 **/
	public List<Integer> findByEmpNo(Integer emp_no);

	/** 藉由員工編號及功能編號新增員工權限 **/
	public void insert(Integer emp_no, String[] fun_no);
	
}
