package com.s_book_det.model;

import java.util.List;
import java.util.Map;

public class S_book_detService {
	
private S_book_detDAO_interface dao;
	
	public S_book_detService() {
		dao = new S_book_detDAO();
	}
	
	public S_book_detVO addS_book_det(Integer s_book_no, Integer book_no) {

		S_book_detVO s_book_detVO = new S_book_detVO();

		s_book_detVO.setS_book_no(s_book_no);
		s_book_detVO.setBook_no(book_no);
		dao.insert(s_book_detVO);
		
		return s_book_detVO;
	}
	
	public void deleteS_book_det(Integer s_book_no, Integer book_no) {
		dao.delete(s_book_no, book_no);
	}

	public S_book_detVO getOneS_book_det(Integer s_gro_no) {
		return dao.findByPrimaryKey(s_gro_no);
	}

	public List<S_book_detVO> getAll() {
		return dao.getAll();
	}
	
	public List<S_book_detVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
