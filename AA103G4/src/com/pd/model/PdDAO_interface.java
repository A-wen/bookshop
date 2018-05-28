package com.pd.model;

import java.util.*;

public interface PdDAO_interface {

	/** 新增活動 **/
	public void insert(PdVO pdVO);

	/** 修改活動 **/
	public void update(PdVO pdVO);

	/** 刪除活動 **/
	public void delete(Integer pd_no);

	/** 查單一活動 **/
	public PdVO findByPrimaryKey(Integer pd_no);

	/** 列出所有活動 **/
	public List<PdVO> getAll();
}
