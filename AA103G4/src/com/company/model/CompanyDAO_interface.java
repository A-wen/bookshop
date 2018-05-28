package com.company.model;

import java.util.*;

import com.book.model.BookVO;

public interface CompanyDAO_interface {
	/** 新增出版社 **/
	public void insert(CompanyVO companyVO);

	/** 修改出版社 **/
	public void update(CompanyVO companyVO);

	/** 刪除出版社 **/
	public void delete(Integer comp_no);

	/** 查單一出版社 **/
	public CompanyVO findByPrimaryKey(Integer comp_no);

	/** 依照名稱查出版社 **/
	public List<CompanyVO> getByName(String comp_name);

	/** 列出所有出版社 **/
	public List<CompanyVO> getAll();

	/** 依出版社查書籍 **/
	public List<BookVO> getBookByCompany(Integer comp_no);
}
