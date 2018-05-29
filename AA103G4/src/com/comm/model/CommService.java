package com.comm.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class CommService {
	private CommDAO_interface dao;

	public CommService() {
		dao = new CommDAO();
	}

	/** 新增評論 
	 * 接書號、會員編號、評論內容、等級
	 * 評論編號SEQ產生
	 * 日期抓系統日期 
	 **/
	public CommVO addComm(Integer book_no, Integer mem_no, String comm_desc, Integer comm_level) {
		CommVO commVO = new CommVO();
		
		commVO.setBook_no(book_no);
		commVO.setMem_no(mem_no);
		commVO.setComm_desc(comm_desc);
		commVO.setComm_level(comm_level);
		dao.insert(commVO);
		return commVO;
	}

	/** 修改評論 **/
	public CommVO updateComm(Integer comm_no, Integer book_no, Integer mem_no, String comm_desc, Integer comm_level,
			Date comm_date) {
		CommVO commVO = new CommVO();
		commVO.setComm_no(comm_no);
		commVO.setBook_no(book_no);
		commVO.setMem_no(mem_no);
		commVO.setComm_desc(comm_desc);
		commVO.setComm_level(comm_level);
		commVO.setComm_date(comm_date);
		dao.update(commVO);
		return commVO;
	}

	/** 刪除評論 **/
	public void deleteComm(Integer comm_no) {
		dao.delete(comm_no);
	}

	/** 查詢單一評論 **/
	public CommVO getOneComm(Integer comm_no) {
		return dao.findByPrimaryKey(comm_no);
	}

	/** 查詢某會員的評論 **/
	public List<CommVO> getCommByMem(Integer mem_no) {
		return dao.getByMember(mem_no);
	}

	/** 查詢某本書的評論 **/
	public List<CommVO> getCommByBook(Integer book_no) {
		return dao.getByBook(book_no);
	}

	/** 列出所有評論 **/
	public List<CommVO> getAllComm() {
		return dao.getAll();
	}
}
