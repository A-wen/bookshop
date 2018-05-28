package com.book.model;

import java.util.List;


public interface BookAppDAO_interface {
		
	public BookVO findByPrimaryKey(Integer book_no); 
	
	public byte[] getBookImg(Integer book_no);

	public BookVO getOneBook(Integer book_no);
	
	public List<BookVO>getBooksByType(Integer type_no);
}
