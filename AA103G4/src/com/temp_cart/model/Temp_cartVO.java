package com.temp_cart.model;

public class Temp_cartVO {
	private Integer mem_no;
	private Integer book_no;
	private Integer b_amount;
	
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getBook_no() {
		return book_no;
	}
	public void setBook_no(Integer book_no) {
		this.book_no = book_no;
	}
	public Integer getB_amount() {
		return b_amount;
	}
	public void setB_amount(Integer b_amount) {
		this.b_amount = b_amount;
	}
	@Override
	public String toString() {
		return "Temp_cartVO [mem_no=" + mem_no + ", book_no=" + book_no + ", b_amount=" + b_amount + "]";
	}
	
	

}
