package com.booktype.model;

import java.util.List;

import com.book.model.BookVO;

public class BooktypeService {
	private BooktypeDAO_interface dao;

	public BooktypeService() {
		dao = new BooktypeDAO();
	}

	/**
	 * 新增書籍類別 輸入類別名稱，編號由SEQ產生
	 **/
	public BooktypeVO addBooktype(String type_name) {
		BooktypeVO booktypeVO = new BooktypeVO();
		booktypeVO.setType_name(type_name);
		dao.insert(booktypeVO);
		return booktypeVO;
	}

	/** 修改書籍類別 **/
	public BooktypeVO updateBooktype(Integer type_no, String type_name) {
		BooktypeVO booktypeVO = new BooktypeVO();
		booktypeVO.setType_no(type_no);
		booktypeVO.setType_name(type_name);
		dao.update(booktypeVO);
		return booktypeVO;
	}

	/** 刪除書籍類別 **/
	public void deleteBooktype(Integer type_no) {
		dao.delete(type_no);
	}

	/** 尋找單一書籍類別 **/
	public BooktypeVO getOneBooktype(Integer type_no) {
		return dao.findByPrimaryKey(type_no);
	}

	/** 列出此類別的書籍 **/
	public List<BooktypeVO> getAllBooktype() {
		return dao.getAll();
	}

	/** 列出所有書籍類別 **/
	public List<BookVO> getBookByBooktype(Integer type_no) {
		return dao.getBookBytype(type_no);
	}
}
