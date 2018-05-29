package com.ca.model;

import java.sql.Date;
import java.util.List;



public class ClassifiedAdsService {

	private ClassifiedAdsDAO_interface dao;

	public ClassifiedAdsService() {
		dao = new ClassifiedAdsDAO();
	}
	public ClassifiedAdsVO addClassifiedAds( Integer type_no, Integer book_no ,Date ca_start, Date ca_end, Integer ca_th, String ca_name){
		
		ClassifiedAdsVO caVO = new  ClassifiedAdsVO();
		
		//新增
		caVO.setTypeno(type_no);
		caVO.setBookno(book_no);	
		caVO.setCastart(ca_start);
		caVO.setCaend(ca_end);
		caVO.setCath(ca_th);
		caVO.setCaname(ca_name);
		
		dao.insert(caVO);
		return caVO;
	}
	public ClassifiedAdsVO updateClassifiedAds(Integer ca_no, Integer type_no, Integer book_no ,Date ca_start, Date ca_end, Integer ca_th, String ca_name){
		
		ClassifiedAdsVO caVO = new  ClassifiedAdsVO();
		
		//修改
		caVO.setCano(ca_no);
		caVO.setTypeno(type_no);
		caVO.setBookno(book_no);
	
		caVO.setCastart(ca_start);
		caVO.setCaend(ca_end);
		caVO.setCath(ca_th);
		caVO.setCaname(ca_name);
		
		dao.update(caVO);
		return caVO;
	}
		
	public void deleteClassifiedAds(Integer ca_no) {
		dao.delete(ca_no);
	}

	public ClassifiedAdsVO getOneClassifiedAds(Integer ca_no) {
		return dao.findByPK(ca_no);
	}

	public List<ClassifiedAdsVO> getAll() {
		return dao.getAll();
	}
}