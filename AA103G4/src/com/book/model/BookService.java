package com.book.model;

import java.util.*;

public class BookService {
	private BookDAO_interface dao;

	public BookService() {
		dao = new BookDAO();
	}

	/**
	 * 新增書籍 接書名、定價、類別、出版社、庫存、ISBN、作者、圖片、敘述
	 * 書籍編號SEQ產生，銷售預設可銷售
	 **/
	public BookVO addBook(String book_name, Integer book_price, Integer type_no, Integer comp_no, Integer book_qty,
			String isbn, String book_author, byte[] book_pic, String book_desc) {
		BookVO bookVO = new BookVO();
		bookVO.setBook_name(book_name);
		bookVO.setBook_price(book_price);
		bookVO.setType_no(type_no);
		bookVO.setComp_no(comp_no);
		bookVO.setBook_qty(book_qty);
		bookVO.setIsbn(isbn);
		bookVO.setBook_author(book_author);
		bookVO.setBook_pic(book_pic);
		bookVO.setBook_desc(book_desc);
		dao.insert(bookVO);
		return bookVO;
	}

	/** 修改書籍:修改時有重新上傳圖片 **/
	public BookVO updateBook(Integer book_no, String book_name, Integer book_price, Integer type_no, Integer comp_no,
			Integer book_qty, String isbn, String book_author, byte[] book_pic, String book_desc, Integer saleable) {
		BookVO bookVO = new BookVO();
		bookVO.setBook_no(book_no);
		bookVO.setBook_name(book_name);
		bookVO.setBook_price(book_price);
		bookVO.setType_no(type_no);
		bookVO.setComp_no(comp_no);
		bookVO.setBook_qty(book_qty);
		bookVO.setIsbn(isbn);
		bookVO.setBook_author(book_author);
		bookVO.setBook_pic(book_pic);
		bookVO.setBook_desc(book_desc);
		bookVO.setSaleable(saleable);
		dao.update(bookVO);
		return bookVO;
	}

	/** 修改書籍:修改時沒重新上傳圖片 **/
	public BookVO updateBookNoPic(Integer book_no, String book_name, Integer book_price, Integer type_no,
			Integer comp_no, Integer book_qty, String isbn, String book_author, String book_desc, Integer saleable) {
		BookVO bookVO = new BookVO();
		bookVO.setBook_no(book_no);
		bookVO.setBook_name(book_name);
		bookVO.setBook_price(book_price);
		bookVO.setType_no(type_no);
		bookVO.setComp_no(comp_no);
		bookVO.setBook_qty(book_qty);
		bookVO.setIsbn(isbn);
		bookVO.setBook_author(book_author);
		bookVO.setBook_desc(book_desc);
		bookVO.setSaleable(saleable);
		dao.updateNoPic(bookVO);
		return bookVO;
	}

	/** 依照書籍編號刪除書籍 **/
	public void deleteBook(Integer book_no) {
		dao.delete(book_no);
	}

	/**依書號找書籍**/
	public BookVO getOneBook(Integer book_no) {
		return dao.findByPrimaryKey(book_no);
	}

	/** 依照書名尋找書籍，結果可能為多，回傳List **/
	public List<BookVO> getBookByName(String name) {
		return dao.getByName(name);
	}

	/** 列出全部書籍 **/
	public List<BookVO> getAllBook() {
		return dao.getAll();
	}

	/** 複合查詢書籍，，結果可能為多，回傳List **/
	public List<BookVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	/** 給購物車用，接收一個書籍編號List回bookVO的List **/
	public List<BookVO> getBookList(List<Integer> book_no_list) {
		return dao.getBookList(book_no_list);
	}

	/** 複合查詢書籍，結果可能為多，回傳List (不一定照書號排，而且可以選擇範圍) **/
	public List<BookVO> query(Map<String, String[]> map) {
		return dao.query(map);
	}

}
