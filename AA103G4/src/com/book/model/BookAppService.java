package com.book.model;

import java.util.List;



public class BookAppService {
	private BookAppDAO_interface dao;

	public BookAppService() {
		dao = new BookAppDAO();
	}
	
	public byte[] getBookImg(Integer book_no) {
		return dao.getBookImg(book_no);
	}
	
	public BookVO getOneBook(Integer book_no){
		return dao.getOneBook(book_no);
	}
	
	public List<BookVO>getBooksByType(Integer type_no){
		return dao.getBooksByType(type_no);
	}
}
