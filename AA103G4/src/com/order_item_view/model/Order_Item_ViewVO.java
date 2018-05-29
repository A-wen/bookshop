package com.order_item_view.model;

public class Order_Item_ViewVO {
	private Integer o_Id;
	private Integer book_No;
	private String book_Name;
	private Integer book_Price;
	private Integer o_Amount;
	public Integer getO_Id() {
		return o_Id;
	}
	public void setO_Id(Integer o_Id) {
		this.o_Id = o_Id;
	}
	public Integer getBook_No() {
		return book_No;
	}
	public void setBook_No(Integer book_No) {
		this.book_No = book_No;
	}
	public String getBook_Name() {
		return book_Name;
	}
	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}
	public Integer getBook_Price() {
		return book_Price;
	}
	public void setBook_Price(Integer book_Price) {
		this.book_Price = book_Price;
	}
	public Integer getO_Amount() {
		return o_Amount;
	}
	public void setO_Amount(Integer o_Amount) {
		this.o_Amount = o_Amount;
	}
	@Override
	public String toString() {
		return "Order_Item_ViewVO [o_Id=" + o_Id + ", book_No=" + book_No + ", book_Name=" + book_Name + ", book_Price="
				+ book_Price + ", o_Amount=" + o_Amount + "]";
	}
}
