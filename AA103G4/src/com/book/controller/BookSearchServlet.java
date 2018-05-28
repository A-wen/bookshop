package com.book.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.model.BookService;
import com.book.model.BookVO;

@WebServlet("/Front-End/Search/search.do")
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BookService serv = new BookService();
		List<BookVO> bookList = null;
		//從請求參數中取出map
//		Map<String, String[]> map = request.getParameterMap();
		if (request.getParameter("page") == null){
			HashMap<String, String[]> oriMap = (HashMap<String, String[]>)request.getParameterMap();
			
			HashMap<String, String[]> cloneMap = new HashMap<String, String[]>(oriMap);
//			cloneMap=(HashMap<String, String[]>)oriMap.clone();
			if(cloneMap.size()==0){ //沒有帶任何參數.預設搜索結果
				cloneMap.put("ROWNUM",setPage(1));
				
				String [] orderby = {"BOOK_NO DESC"};
				cloneMap.put("ORDERBY",orderby);
				bookList = serv.query(cloneMap);
			}
		}
		for(BookVO book:bookList){
			System.out.println(book);
		}
		request.setAttribute("bookList", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Front-End/book/booklist.jsp");
		dispatcher.forward(request, response);

//		List<BookVO> list = serv.query(cloneMap);
		
	
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private String[] setPage(int pageNum){
		int pageItems = 9;
		String[] sql = {(1+(pageItems*(pageNum-1))) + " AND " + (pageItems*pageNum)};
		return sql;
		
	}
}
