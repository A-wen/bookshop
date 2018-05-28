package com.order_item_view.model;

import java.util.List;

import com.order_item.model.Order_ItemVO;

public class Order_Item_ViewService {

	private Order_Item_ViewDAO_Interface DAO;
	
	public Order_Item_ViewService(){
		DAO = new Order_Item_ViewDAO();
	}
	
	public List<Order_Item_ViewVO> getByOId(Integer o_id){
		return DAO.getByOId(o_id);
	}
}
