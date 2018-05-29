package com.order_info.model;

import java.sql.Timestamp;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.delivery_status.model.Delivery_StatusVO;
import com.mem.model.MemVO;
import com.order_item.model.Order_ItemVO;
import com.paymeny_method.model.Payment_MethodVO;
import com.order_status.model.Order_StatusVO;

public class Order_InfoVO implements java.io.Serializable{

	private Integer o_Id; 
	private MemVO memVO;
	private Payment_MethodVO paymentMethod;
	private Order_StatusVO orderStatus;
	private Integer o_Sum;
	private Timestamp o_Date;
	private Delivery_StatusVO deliveryStatus;
	private String d_Name;
	private String addr;
	private String tel;
	private Set<Order_ItemVO> orderItems; //訂單集合
	
	public Integer getO_Id() {
		return o_Id;
	}
	public void setO_Id(Integer o_Id) {
		this.o_Id = o_Id;
	}
	
	public MemVO getMemVO() {
		return memVO;
	}
	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}
	
	public Payment_MethodVO getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Payment_MethodVO paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public Order_StatusVO getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Order_StatusVO orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getO_Sum() {
		return o_Sum;
	}
	public void setO_Sum(Integer o_Sum) {
		this.o_Sum = o_Sum;
	}
	
	public Timestamp getO_Date() {
		return o_Date;
	}
	public void setO_Date(Timestamp o_Date) {
		this.o_Date = o_Date;
	}
	
	public Delivery_StatusVO getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(Delivery_StatusVO deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	public String getD_Name() {
		return d_Name;
	}
	public void setD_Name(String d_Name) {
		this.d_Name = d_Name;
	}
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
		
	public Set<Order_ItemVO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<Order_ItemVO> orderItems) {
		this.orderItems = orderItems;
	}
	
	public String getOrderInfo(){
		StringBuilder sb = new StringBuilder();
//		sb.append("訂單編號:"+getO_Id()+"\n");
		sb.append("會員編號:"+memVO.getMem_no()+"\n");
		sb.append("付款方式:"+paymentMethod.getP_Code()+"\n");
		sb.append("訂單狀況:"+orderStatus.getO_Code()+"\n");
		sb.append("訂單金額:"+o_Sum+"\n");
		sb.append("訂單成立日期："+o_Date+"\n");
		sb.append("配送狀況:"+deliveryStatus.getD_Code()+"\n");
		sb.append("收件者:"+d_Name+"\n");
		sb.append("聯絡電話:"+tel+"\n");
		for(Order_ItemVO item:orderItems){
			sb.append("購買商品："+item.getBook_no()+"\n");
		}
		return sb.toString();
	}
	
	//有用到聯合主鍵，所以必須改寫equals與hashCode
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof Order_InfoVO)) {
            return false;
        }
 
        Order_InfoVO compareItem = (Order_InfoVO) obj;
        return new EqualsBuilder()
                    .append(this.memVO.getMem_no(), compareItem.memVO.getMem_no())
                    .isEquals(); 
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.memVO.getMem_no())
                    .toHashCode();
    }	
	
    
	
}
