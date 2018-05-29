package com.order_item.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Testorderitem")
public class Testorderitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Order_ItemService serv = new Order_ItemService();
		List <Order_ItemVO> itemList = serv.getByOId(1000000001);
		PrintWriter out = response.getWriter();
		for(Order_ItemVO item : itemList){
			out.println(item.getBook_no());
		}
	}



}
