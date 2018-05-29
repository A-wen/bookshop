package com.order_info.model;

import java.util.List;
import java.util.Set;

import com.order_item.model.Order_ItemVO;

public class Order_InfoService {

	private Order_InfoDAO_Interface DAO;
	
	public Order_InfoService(){
		DAO = new Order_InfoDAO();
	}
	
	public boolean createOrder(Order_InfoVO ordVO){
		return DAO.insert(ordVO);
	}
	
	public Order_InfoVO findByPrimaryKey (Integer o_id){
		return DAO.findByPrimaryKey(o_id);
	}
	
	public List<Order_InfoVO> getByMemNo(Integer mem_No){
		return DAO.getByMemNo(mem_No);
	}
	
	public List<Order_InfoVO> getAll(){
		return DAO.getAll();
	}
	
}
