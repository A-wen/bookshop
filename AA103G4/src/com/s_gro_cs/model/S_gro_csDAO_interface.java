package com.s_gro_cs.model;

import java.util.List;
import java.util.Set;

import com.s_gro_info.model.S_gro_infoVO;

public interface S_gro_csDAO_interface {
		public void insert(S_gro_csVO s_gro_csVO);
	    public void update(S_gro_csVO s_gro_csVO);
	    public void delete(Integer cs_no); 
	    public S_gro_csVO findByPrimaryKey(Integer cs_no);
	    public List<S_gro_csVO> getAll();
	  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  	public List<EmpVO> getAll(Map<String, String[]> map);
	  //查詢某部門的員工(一對多)(回傳 Set)
	    public Set<S_gro_infoVO> getS_gro_infosByCs_no(Integer cs_no);
}
