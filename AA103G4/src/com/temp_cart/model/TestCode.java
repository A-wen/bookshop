package com.temp_cart.model; 

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.List;

public class TestCode {

	public static void main(String[] args) {
		Temp_CartService serv = new Temp_CartService();
//		System.out.println("會員101的購物車明細如下：");
//		List<CartItemVO> cart = serv.getAll(101);
//		for(CartItemVO item :cart){
//			System.out.println(item);
//		}
		System.out.println("==========");
		System.out.println("新增書籍到會員101的購物車內");
		try{
			serv.addItem(101, 1012, 50);
		}catch(SQLIntegrityConstraintViolationException e){
			System.out.println("這本書已經加過了!");
		}catch(SQLException e){
			System.out.println("其他SQL錯誤");
		}catch(Exception e){
			System.out.println("錯誤4");
		}
		System.out.println("hello");
		
//		System.out.println("會員101的新購物車明細如下：");
//		List<CartItemVO> newCart = serv.getAll(101);
//		for(CartItemVO item :newCart){
//			System.out.println(item);
//		}
//		System.out.println("==========");
//		System.out.println("會員101的購物車內，書號1010的書要買？本");
//		Temp_cartVO testVO = serv.findByPrimaryKey(101,1010);
//		System.out.println(testVO);

	}

}
