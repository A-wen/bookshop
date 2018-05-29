package com.comm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookAppService;
import com.comm.model.CommAppService;
import com.comm.model.CommService;
import com.comm.model.CommVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@WebServlet("/CommAppServlet.do")
public class CommAppServlet extends HttpServlet{

private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
private Integer book_no;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null){
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		CommAppService commSvc = new CommAppService();
		String action = jsonObject.get("action").getAsString();
		book_no = Integer.parseInt(jsonObject.get("book_no").getAsString());
		System.out.println("action: "+ action);
		
		
		if("getBookComm".equals(action)){
			System.out.println(book_no);
			Integer book_no = jsonObject.get("book_no").getAsInt();
			Map<String, CommVO> comms = commSvc.getCommByBook(book_no);
			writeText(res, gson.toJson(comms));
		  }else if ("addBookComment".equals(action)){
			  	Integer book_no = jsonObject.get("book_no").getAsInt();
			  	Integer mem_no = jsonObject.get("mem_no").getAsInt();
				Integer comm_level = jsonObject.get("comm_level").getAsInt();
				String comm_desc = jsonObject.get("comm_desc").getAsString();
				//Date comm_date = new java.sql.Date(System.currentTimeMillis());
				System.out.println(jsonObject);
				

				String result = "fail";
				try {
					commSvc.addBookComment( book_no, mem_no, comm_desc, comm_level); //comm_date,
					result = "success";
				} catch (Exception e) {
					e.printStackTrace();
				}
				writeText(res, result);
		  }
		}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
	}
	
}
