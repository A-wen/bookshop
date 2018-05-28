package com.cm.model;

import java.util.List;


	public interface CmDAO_interface{
		public void insert(CmVO cmVO);
		public void update(CmVO cmVO);
		public void delete(Integer cm_no);
		public CmVO findByPK(Integer cm_no);
		public List<CmVO> getAll();
		//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//  public List<EmpVO> getAll(Map<String, String[]> map); 
	}


