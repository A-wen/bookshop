package com.shopping_cart.model;
public class Shopping_CartVO {
	private Integer m_No;
	private Integer b_No;
	private String b_Name;
	private String b_Author;
	private byte[] b_Pic;
	private Integer b_Price;
	private Integer b_Qty;
	private Integer b_Stock;
	private String p_Name;
	private Float p_Disc;
	private Integer p_Price;
	
	public Integer getP_Price() {
		return p_Price;
	}
	public void setP_Price(Integer p_Price) {
		this.p_Price = p_Price;
	}
	public Integer getM_No() {
		return m_No;
	}
	public void setM_No(Integer m_No) {
		this.m_No = m_No;
	}
	public String getB_Name() {
		return b_Name;
	}
	public void setB_Name(String b_Name) {
		this.b_Name = b_Name;
	}
	public String getB_Author() {
		return b_Author;
	}
	public void setB_Author(String b_Author) {
		this.b_Author = b_Author;
	}
	public byte[] getB_Pic() {
		return b_Pic;
	}
	public void setB_Pic(byte[] b_Pic) {
		this.b_Pic = b_Pic;
	}
	public Integer getB_Price() {
		return b_Price;
	}
	public void setB_Price(Integer b_Price) {
		this.b_Price = b_Price;
	}
	public Integer getB_Qty() {
		return b_Qty;
	}
	public void setB_Qty(Integer b_Qty) {
		this.b_Qty = b_Qty;
	}
	public Integer getB_Stock() {
		return b_Stock;
	}
	public void setB_Stock(Integer b_Stock) {
		this.b_Stock = b_Stock;
	}
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
	public Float getP_Disc() {
		return p_Disc;
	}
	public void setP_Disc(Float p_Disc) {
		this.p_Disc = p_Disc;
	}
	public Integer getB_No() {
		return b_No;
	}
	public void setB_No(Integer b_No) {
		this.b_No = b_No;
	}
	
	@Override
	public String toString() {
		return "Shopping_CartVO [m_No=" + m_No + ",b_No=" + b_No + ", b_Name=" + b_Name + ", b_Author=" + b_Author + ", b_Price="
				+ b_Price + ", b_Qty=" + b_Qty + ", b_Stock=" + b_Stock + ", p_Name=" + p_Name + ", p_Disc=" + p_Disc
				+ ", p_Price="+ p_Price +"]";
	}
	
	

	
	
	

	
}
