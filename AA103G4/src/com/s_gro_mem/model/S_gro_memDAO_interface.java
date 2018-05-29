package com.s_gro_mem.model;

import java.util.*;

public interface S_gro_memDAO_interface {
          public void insert(S_gro_memVO s_gro_memVO);
          public void update(S_gro_memVO s_gro_memVO);
          public void delete(Integer s_gro_no, Integer mem_no);
          public S_gro_memVO findByPrimaryKey(Integer s_gro_no);
          //搜尋加入的讀書會
      	  public List<S_gro_memVO> findMemJoin(Integer mem_no);
      	  //搜尋成員
       	  public List<S_gro_memVO> findMemYet(Integer s_gro_no);
      	  public List<S_gro_memVO> findMemIn(Integer s_gro_no);
      	  public S_gro_memVO findaguy(Integer s_gro_no, Integer mem_no);
          public List<S_gro_memVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<S_gro_memVO> getAll(Map<String, String[]> map); 
        //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
          public void insert2 (S_gro_memVO s_gro_memVO , java.sql.Connection con);
}
