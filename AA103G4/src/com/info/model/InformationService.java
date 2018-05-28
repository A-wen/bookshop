package com.info.model;

import java.sql.Date;
import java.util.List;



public class InformationService {
	private InformationDAO_interface dao;
	
	public InformationService(){
		dao = new InformationDAO();
	}
	public InformationVO addInformation( Date info_term, String info_exp,String info_title){
		
		 //新增
		InformationVO infoVO = new InformationVO();		
		infoVO.setInfoterm(info_term); 
		infoVO.setInfoexp(info_exp);
		infoVO.setInfotitle(info_title);
		dao.insert(infoVO);
		return infoVO;
	}
	public InformationVO updateInformation(Integer info_no, java.sql.Date info_term, String info_exp,String info_title){
		
		 //修改
		InformationVO infoVO = new InformationVO();		
		
		infoVO.setInfono(info_no);
		infoVO.setInfoterm(info_term); 
		infoVO.setInfoexp(info_exp);
		infoVO.setInfotitle(info_title);
		dao.update(infoVO);
		return infoVO;

    }
	public void deleteInformation(Integer info_no) {
		dao.delete(info_no);
	}

	public InformationVO getOneInformation(Integer info_no) {
		return dao.findPK(info_no);
	}

	public List<InformationVO> getAll() {
		return dao.getAll();
	}
}
