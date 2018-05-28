package com.temp_cart.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.temp_cart.model.Temp_CartService;

@WebServlet("/Front-End/Cart/synccart")
public class synccart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Temp_CartService serv = new Temp_CartService();
		Integer mem_no = Integer.parseInt(request.getParameter("mem"));
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) { //迭代搜索cookies陣列
			if (cookie.getName().equals("cart")) {
				Gson gson = new Gson();
				Type collectionType = new TypeToken<Map<String,Integer>>() {
					}.getType();
				Map<String,Integer> books = gson.fromJson(cookie.getValue(),collectionType);
				try {
					serv.syncCart(mem_no, books);
		            cookie.setValue("");
		            cookie.setPath("/");
		            cookie.setMaxAge(0);
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
