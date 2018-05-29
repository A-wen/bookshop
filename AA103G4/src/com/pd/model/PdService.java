package com.pd.model;

import java.util.*;

public class PdService {
	private PdDAO_interface dao;

	public PdService() {
		dao = new PdDAO();
	}

	/**
	 * 新增活動 接名稱、描述、起始日、結束日、折扣
	 **/
	public PdVO addPd(String pd_name, String pd_desc, String startdate, String enddate, Double discount) {
		PdVO pdVO = new PdVO();
		pdVO.setPd_name(pd_name);
		pdVO.setPd_desc(pd_desc);
		pdVO.setStartdate(java.sql.Date.valueOf(startdate));
		pdVO.setEnddate(java.sql.Date.valueOf(enddate));
		pdVO.setDiscount(discount);
		dao.insert(pdVO);
		return pdVO;
	}

	/** 修改活動 **/
	public PdVO updatePd(Integer pd_no, String pd_name, String pd_desc, String startdate, String enddate,
			Double discount) {
		PdVO pdVO = new PdVO();
		pdVO.setPd_no(pd_no);
		pdVO.setPd_name(pd_name);
		pdVO.setPd_desc(pd_desc);
		pdVO.setStartdate(java.sql.Date.valueOf(startdate));
		pdVO.setEnddate(java.sql.Date.valueOf(enddate));
		pdVO.setDiscount(discount);
		dao.update(pdVO);
		return pdVO;
	}

	/** 刪除活動 **/
	public void deletePd(Integer pd_no) {
		dao.delete(pd_no);
	}

	/** 查單一活動 **/
	public PdVO getOnePd(Integer pd_no) {
		return dao.findByPrimaryKey(pd_no);
	}

	/** 列出所有活動 **/
	public List<PdVO> getAllPd() {
		return dao.getAll();
	}
}
