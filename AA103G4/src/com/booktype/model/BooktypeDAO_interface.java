package com.booktype.model;

import java.util.*;

import com.book.model.BookVO;

public interface BooktypeDAO_interface {

	/** 新增書籍類別 **/
	public void insert(BooktypeVO typeVO);

	/** 修改書籍類別 **/
	public void update(BooktypeVO typeVO);

	/** 刪除書籍類別 **/
	public void delete(Integer type_no);

	/** 尋找單一書籍類別 **/
	public BooktypeVO findByPrimaryKey(Integer type_no);

	/** 列出此類別的書籍 **/
	public List<BookVO> getBookBytype(Integer type_no);

	/** 列出所有書籍類別 **/
	public List<BooktypeVO> getAll();

}
