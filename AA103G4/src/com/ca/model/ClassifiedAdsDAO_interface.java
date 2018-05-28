package com.ca.model;

import java.util.List;



public interface ClassifiedAdsDAO_interface {
	public void insert(ClassifiedAdsVO caVO);
	public void update(ClassifiedAdsVO caVO);
	public void delete(Integer ca_no);
	public ClassifiedAdsVO findByPK(Integer ca_no);
	public List<ClassifiedAdsVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	

}
