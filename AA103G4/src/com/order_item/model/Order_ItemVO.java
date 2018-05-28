package com.order_item.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.order_info.model.Order_InfoVO;

public class Order_ItemVO implements java.io.Serializable{
	
	private Order_InfoVO orderInfo;
	private Integer book_no;
	private Integer o_amount;
	private String cou_no;
	private Integer o_discount;
	private Integer ord_subtotal;
	
	public Order_InfoVO getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(Order_InfoVO orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Integer getBook_no() {
		return book_no;
	}
	public void setBook_no(Integer book_no) {
		this.book_no = book_no;
	}
	public Integer getO_amount() {
		return o_amount;
	}
	public void setO_amount(Integer o_amount) {
		this.o_amount = o_amount;
	}
	public String getCou_no() {
		return cou_no;
	}
	public void setCou_no(String cou_no) {
		this.cou_no = cou_no;
	}
	public Integer getO_discount() {
		return o_discount;
	}
	public void setO_discount(Integer o_discount) {
		this.o_discount = o_discount;
	}
	public Integer getOrd_subtotal() {
		return ord_subtotal;
	}
	public void setOrd_subtotal(Integer ord_subtotal) {
		this.ord_subtotal = ord_subtotal;
	}

    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof Order_ItemVO)) {
            return false;
        }
 
        Order_ItemVO compareItem = (Order_ItemVO) obj;
        return new EqualsBuilder()
                    .append(this.orderInfo.getO_Id(), compareItem.orderInfo.getO_Id())
                    .append(book_no, book_no)
                    .isEquals(); 
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.orderInfo.getO_Id())
                    .append(this.book_no)
                    .toHashCode();
    }
	

}
