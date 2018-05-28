package com.comm.model;

import java.util.*;

public interface CommDAO_interface {
	
	/** 新增評論 **/
	public void insert(CommVO commVO);

	/** 修改評論 **/
	public void update(CommVO commVO);

	/** 刪除評論 **/
	public void delete(Integer comm_no);

	/** 查詢單一評論 **/
	public CommVO findByPrimaryKey(Integer comm_no);

	/** 查詢某會員的評論 **/
	public List<CommVO> getByMember(Integer mem_no);

	/** 查詢某本書的評論 **/
	public List<CommVO> getByBook(Integer book_no);

	/** 列出所有評論 **/
	public List<CommVO> getAll();
}
