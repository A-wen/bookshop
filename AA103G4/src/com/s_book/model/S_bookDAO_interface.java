package com.s_book.model;

import java.util.*;

public interface S_bookDAO_interface {
	    public void insert(S_bookVO s_bookVO);
	    public void update(S_bookVO s_bookVO);
	    public void delete(Integer s_book_no);
	    public S_bookVO findByPrimaryKey(Integer s_book_no);
	    public List<S_bookVO> getAll();
	  //萬用複合查詢(傳入參數型態Map)(回傳 List)
	    public List<S_bookVO> getAll(Map<String, String[]> map);
}
