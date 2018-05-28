package com.s_book.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.s_gro_info.model.S_gro_infoVO;

	public class S_bookService {
		
	private S_bookDAO_interface dao;
	
	public S_bookService() {
		dao = new S_bookDAO();
	}
	
	public S_bookVO addS_book(Integer s_gro_no, java.sql.Date cre_date,
			java.sql.Date end_date) {

		S_bookVO s_bookVO = new S_bookVO();

		s_bookVO.setS_gro_no(s_gro_no);
		s_bookVO.setCre_date(cre_date);
		s_bookVO.setEnd_date(end_date);
		dao.insert(s_bookVO);

		return s_bookVO;
	}
	
	public S_bookVO updateS_book(Integer s_book_no, Integer s_gro_no,
			java.sql.Date cre_date, java.sql.Date end_date) {

		S_bookVO s_bookVO = new S_bookVO();

		s_bookVO.setS_book_no(s_book_no);
		s_bookVO.setS_gro_no(s_gro_no);
		s_bookVO.setCre_date(cre_date);
		s_bookVO.setEnd_date(end_date);
		dao.update(s_bookVO);

		return s_bookVO;
	}
	
	public void deleteS_book(Integer s_book_no) {
		dao.delete(s_book_no);
	}

	public S_bookVO getOneS_book(Integer s_book_no) {
		return dao.findByPrimaryKey(s_book_no);
	}

	public List<S_bookVO> getAll() {
		return dao.getAll();
	}
	public List<S_bookVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
