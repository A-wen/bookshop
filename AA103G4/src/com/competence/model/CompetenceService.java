package com.competence.model;

import java.util.List;

import com.fun.model.FunVO;

public class CompetenceService {
	
	CompetenceDAO_interface dao;
	
	public CompetenceService(){
		dao = new CompetenceDAO();
	}
	
	public List<CompetenceVO> getAll() {
		return dao.getAll();
	}

	public List<CompetenceVO> getFunCompetece(Integer fun_no) {
		return dao.findByFun(fun_no);
	}
	
	
	public List<CompetenceVO> getEmpCompetece(Integer emp_no) {
		return dao.findByEmp(emp_no);
	}
	
	/**List contains**/
	public List<Integer> getEmpCompeteceFunNo(Integer emp_no){
		return dao.findByEmpNo(emp_no);
	}
	
	
	public void addCompetence(Integer emp_no,String[] fun_no) {
		dao.insert(emp_no,fun_no);
	}

	public CompetenceVO updateCompetece(Integer fun_no, Integer emp_no ,Integer new_fun) {

		CompetenceVO competenceVO = new CompetenceVO();
		competenceVO.setEmpNo(fun_no);
		competenceVO.setFunNo(emp_no);
		competenceVO.setFunNo(new_fun);
		dao.update(fun_no,emp_no,new_fun);
		return competenceVO;
	}
	
	public void deleteCompetece(Integer emp_no,Integer fun_no) {
		dao.delete(emp_no,fun_no);
	}
	
	public void deleteEmpCompetece(Integer emp_no){
		dao.deleteALL(emp_no);
	}
	
}
