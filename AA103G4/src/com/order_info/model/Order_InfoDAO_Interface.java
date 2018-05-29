package com.order_info.model;

import java.util.List;

public interface Order_InfoDAO_Interface {
	
	/**
	 * 傳入訂單物件，建立新訂單
	 * @param order_infoVO
	 * @return 是否成功
	 */
	public boolean insert (Order_InfoVO order_infoVO);

	/**
	 * 傳入訂單編號，回傳該訂單編號物件
	 * @param o_id 訂單編號
	 * @return 訂單編號物件
	 */
	public Order_InfoVO findByPrimaryKey (Integer o_id);
	
	/**
	 * 傳入會員編號，取得該會員所有的訂單
	 * @param mem_No 會員編號
	 * @return 該會員的訂單集合
	 */
	public List<Order_InfoVO> getByMemNo(Integer mem_No);
	
	/**
	 * 取得目前所有交易訂單紀錄 (依訂單編號遞減排序)
	 * @return 所有訂單交易紀錄
	 */
	public List<Order_InfoVO> getAll();
}
