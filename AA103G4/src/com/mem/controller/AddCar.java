package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.mem.model.MemVO;
import com.temp_cart.model.Temp_CartService;

@WebServlet(urlPatterns ={
		"/member/AddCar.do",
	})
public class AddCar extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String requestURL = request.getParameter("requestURL");
		Temp_CartService serv = new Temp_CartService();
		MemVO memVO = (MemVO)(request.getSession().getAttribute("memVO"));
		String action = request.getParameter("action");
		Integer mem_no = memVO.getMem_no();
		PrintWriter out = response.getWriter();
		
		if("add".equals(action)){
			String[] item = URLDecoder.decode(request.getParameter("item"), "UTF-8").split(":");
			try {
				serv.addItem(mem_no, Integer.parseInt(item[0]), Integer.parseInt(item[1]));
//				if (requestURL.equals("/Front-End/member/memberTrackBook.jsp")) {
//					request.setAttribute("sweet2","新增成功");
//					String url = requestURL;
//					RequestDispatcher successView = request.getRequestDispatcher(url);
//					successView.forward(request,response);
//				}
				
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
