package com.pm.model;

import java.util.List;

public class PmService {
	private PmDAO_interface dao;

	public PmService() {
		dao = new PmDAO();
	}

	/**
	 * 新增活動書單 接活動編號跟書籍編號
	 **/
	public PmVO addPm(Integer pd_no, Integer book_no) {
		PmVO pmVO = new PmVO();
		pmVO.setPd_no(pd_no);
		pmVO.setBook_no(book_no);
		dao.insert(pmVO);
		return pmVO;
	}

	/** 活動書單沒有修改 需要就新增，不用就刪除 **/

	/** 刪除活動書單 **/
	public void deletePm(Integer pd_no, Integer book_no) {
		dao.delete(pd_no, book_no);
	}

	/** 依照活動查書 **/
	public List<PmVO> getBooksByPm(Integer pd_no) {
		return dao.findBooksByPm(pd_no);
	}

	/** 列出所有活動書單 **/
	public List<PmVO> getAllPm() {
		return dao.getAll();
	}

}
