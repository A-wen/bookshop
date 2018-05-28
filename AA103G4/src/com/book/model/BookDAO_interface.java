package com.book.model;

import java.util.*;

public interface BookDAO_interface {

	/** 新增書籍 **/
	public void insert(BookVO bookVO);

	/** 修改書籍:修改時有重新上傳圖片 **/
	public void update(BookVO bookVO);

	/** 修改書籍:修改時沒重新上傳圖片 **/
	public void updateNoPic(BookVO bookVO);

	/** 刪除書籍 **/
	public void delete(Integer book_no);

	/**依書號找書籍**/
	public BookVO findByPrimaryKey(Integer book_no);

	/**依照書名尋找書籍，結果可能為多，回傳List**/
	public List<BookVO> getByName(String name);

	/**列出全部書籍**/
	public List<BookVO> getAll();

	/**複合查詢書籍，，結果可能為多，回傳List**/
	public List<BookVO> getAll(Map<String, String[]> map);

	/**給購物車用，接收一個書籍編號List回bookVO的List**/
	public List<BookVO> getBookList(List<Integer> book_no_list);
	
	/**複合查詢書籍，，結果可能為多，回傳List**/
	public List<BookVO> query(Map<String, String[]> map);
}
