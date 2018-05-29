package com.s_gro_info.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.s_gro_mem.model.S_gro_memVO;

import util.HibernateUtil;

public interface S_gro_infoDAO_interface{
		public void insert(S_gro_infoVO s_gro_infoVO, Integer mem_no);
		public void update(S_gro_infoVO s_gro_infoVO);
		public void delete(Integer s_gro_no);
		public S_gro_infoVO findByID(Integer s_gro_no);
		public S_gro_infoVO findByPrimaryKey(Integer s_gro_no);
		//讀書會成員檢舉讀書會
//	    public S_gro_infoVO findinfo(Integer mem_no);
		//搜尋尚未加入的讀書會
    	public List<S_gro_infoVO> findClub(Integer mem_no);
    	//查詢某讀書會的成員(一對多)(回傳 Set)
	    public Set<S_gro_memVO> getS_gro_memByS_gro_no(Integer s_gro_no);
		public List<S_gro_infoVO> getAll();
		//萬用複合查詢(傳入參數型態Map)(回傳 List)
        public List<S_gro_infoVO> getAll(Map<String, String[]> map);
      //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
//	    public void insertWithEmps(S_gro_infoVO s_gro_infoVO , List<S_gro_memVO> list);
        
        public List<S_gro_infoVO> getByMenager(Integer mem_no);

}
