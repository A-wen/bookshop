package com.order_item.model;

import java.util.*;

public interface Order_ItemDAO_Interface {
	
	/**
	 * 傳入購物車物件，將物件寫入DB
	 * @param order_itemVO
	 */
	public void insert (Order_ItemVO order_itemVO);
	/**
	 * 傳入訂單編號，取出該訂單下的訂單項目
	 * @param o_id 訂單編號
	 * @return 訂單項目
	 */
	public List <Order_ItemVO> getByOId(Integer o_id);
	
//	public Order_ItemVO findByOID(Integer o_id);

	
	/**
	 * 傳入會員編號，建立
	 * @param mem_no
	 * @return
	 */
	public Set<Order_ItemVO> createTempOrdItem(Integer mem_no);
}
