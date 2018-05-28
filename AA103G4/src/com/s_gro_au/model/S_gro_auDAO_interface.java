package com.s_gro_au.model;

import java.util.*;

public interface S_gro_auDAO_interface {
          public void insert(S_gro_auVO s_gro_auVO);
          public void update(S_gro_auVO s_gro_auVO);
          public void delete(Integer au_no);
          public S_gro_auVO findByPrimaryKey(Integer au_no);
          public List<S_gro_auVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<S_gro_auVO> getAll(Map<String, String[]> map); 
}
