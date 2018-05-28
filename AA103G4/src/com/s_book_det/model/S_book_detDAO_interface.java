package com.s_book_det.model;

import java.util.*;

public interface S_book_detDAO_interface {
	public void insert(S_book_detVO s_book_detVO);
    public void delete(Integer s_book_no, Integer book_no);
    public S_book_detVO findByPrimaryKey(Integer s_book_no);
    public List<S_book_detVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<S_book_detVO> getAll(Map<String, String[]> map);
}
