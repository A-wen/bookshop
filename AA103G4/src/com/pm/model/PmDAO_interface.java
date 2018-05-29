package com.pm.model;

import java.util.*;

public interface PmDAO_interface {

	/** 新增活動書單 **/
	public void insert(PmVO pmVO);

	/** 活動書單沒有修改 需要就新增，不用就刪除 **/

	/** 刪除活動書單 **/
	public void delete(Integer pd_no, Integer book_no);

	/** 依照活動查書 **/
	public List<PmVO> findBooksByPm(Integer pd_no);

	/** 列出所有活動書單 **/
	public List<PmVO> getAll();
}
