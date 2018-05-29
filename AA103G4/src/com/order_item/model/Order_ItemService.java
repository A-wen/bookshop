package com.order_item.model;

import java.util.List;

public class Order_ItemService {

	private Order_ItemDAO_Interface DAO;
	
	public Order_ItemService(){
		DAO = new Order_ItemDAO();
	}
	
	public List <Order_ItemVO> getByOId(Integer o_id){
		return DAO.getByOId(o_id);
	}
	
//	public Order_ItemVO getByOID(Integer o_id){
//		return DAO.findByOID(o_id);
//	}
}
