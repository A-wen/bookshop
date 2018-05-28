package com.order_info_view.model;

import java.util.List;

public class Order_Info_ViewService {

	private Order_Info_ViewDAO_Interface DAO;
	
	public Order_Info_ViewService(){
		DAO = new Order_Info_ViewDAO();
	}
	
	public Order_Info_ViewVO findByPrimaryKey (Integer o_id){
		return DAO.findByPrimaryKey(o_id);
	}
	
	public List<Order_Info_ViewVO> getByMemNo(Integer mem_No){
		return DAO.getByMemNo(mem_No);
	}
	
	public List<Order_Info_ViewVO> getAll(){
		return DAO.getAll();
	}
	
}
