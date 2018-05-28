package com.book.model;

public class BookVO implements java.io.Serializable{
	private Integer book_no;
	private String book_name;
	private Integer book_price;
	private Integer type_no;
	private Integer comp_no;
	private Integer book_qty;
	private String isbn;
	private String book_author;
	private byte[] book_pic;
	private String book_desc;
	private Integer saleable;
	
	public Integer getBook_no() {
		return book_no;
	}
	public void setBook_no(Integer book_no) {
		this.book_no = book_no;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public Integer getBook_price() {
		return book_price;
	}
	public void setBook_price(Integer book_price) {
		this.book_price = book_price;
	}
	public Integer getType_no() {
		return type_no;
	}
	public void setType_no(Integer type_no) {
		this.type_no = type_no;
	}
	public Integer getComp_no() {
		return comp_no;
	}
	public void setComp_no(Integer comp_no) {
		this.comp_no = comp_no;
	}
	public Integer getBook_qty() {
		return book_qty;
	}
	public void setBook_qty(Integer book_qty) {
		this.book_qty = book_qty;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public byte[] getBook_pic() {
		return book_pic;
	}
	public void setBook_pic(byte[] book_pic) {
		this.book_pic = book_pic;
	}
	public String getBook_desc() {
		return book_desc;
	}
	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}
	public Integer getSaleable() {
		return saleable;
	}
	public void setSaleable(Integer saleable) {
		this.saleable = saleable;
	}
	@Override
	public String toString() {
		return "BookVO [book_no=" + book_no + ", book_name=" + book_name + ", book_price=" + book_price + ", type_no="
				+ type_no + ", comp_no=" + comp_no + ", book_qty=" + book_qty + ", isbn=" + isbn + ", book_author="
				+ book_author + ", book_desc=" + book_desc + ", saleable=" + saleable + "]";
	}
	
}
