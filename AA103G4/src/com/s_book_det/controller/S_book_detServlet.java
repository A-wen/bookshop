package com.s_book_det.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_book_det.model.*;
import com.s_gro_dis.model.S_gro_disVO;
import com.book.model.BookVO;
import com.s_book.model.*;

public class S_book_detServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("s_book_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請勿空白");
				}
				System.out.println(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer s_book_no = null;
				try {
					s_book_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				S_book_detService s_book_detSvc = new S_book_detService();
				S_book_detVO s_book_detVO = s_book_detSvc.getOneS_book_det(s_book_no);
				if (s_book_detVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("s_book_detVO", s_book_detVO); 
				String url = "/s_book_det/listOneS_book_det.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer s_book_no = new Integer(req.getParameter("s_book_no").trim());
				Integer book_no = new Integer(req.getParameter("book_no").trim());
							
				S_book_detVO s_book_detVO = new S_book_detVO();
				s_book_detVO.setS_book_no(s_book_no);
				s_book_detVO.setBook_no(book_no);				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_book_detVO", s_book_detVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_book_det/addS_book_det.jsp");
					failureView.forward(req, res);
					return;
				}
				

				S_book_detService s_book_detSvc = new S_book_detService();
				s_book_detVO = s_book_detSvc.addS_book_det(s_book_no, book_no);
				

				String url = "/s_book_det/listAllS_book_det.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_book_det/addS_book_det.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); 
//			System.out.println("requestURL="+requestURL);
			try {
				Integer s_book_no = new Integer(req.getParameter("s_book_no"));
				Integer book_no = new Integer(req.getParameter("book_no"));
				
				S_book_detService s_book_detSvc = new S_book_detService();
				s_book_detSvc.deleteS_book_det(s_book_no, book_no);
				
				System.out.println("s_book_no="+s_book_no);
				System.out.println("book_no="+book_no);
//				if(requestURL.equals("/Front-End/s_gro/listS_book_dets_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<S_book_detVO> list  = s_book_detSvc.getAll(map);
//					req.setAttribute("listS_book_dets_ByCompositeQuery",list); 
//					System.out.println("list="+list.size());
//				}
				
				String url = "/Front-End/s_gro/listS_book_dets_ByCompositeQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料::"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front-End/s_gro/listS_book_dets_ByCompositeQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listS_book_dets_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				

				Map<String, String[]> map = req.getParameterMap();
				List<Integer> book_no_list = new ArrayList<Integer>();

				S_book_detService s_book_detSvc = new S_book_detService();
				List<S_book_detVO> list  = s_book_detSvc.getAll(map);
				
				/*******************************************************************************/
//				for (int i=0;i<list.size();i++) {					
//					book_no_list.add(list.get(i).getBook_no());
//					System.out.println("book_no_list:"+list.get(i).getBook_no());
//				}
//				
//				BookService ss = new BookService();
//				List<BookVO> book_list = ss.getBookList(book_no_list);


				req.setAttribute("listS_book_dets_ByCompositeQuery", list);
//				System.out.println("list="+list.size());
				com.book.model.BookService bookServ = new com.book.model.BookService();
				List<BookVO> allBook = bookServ.getAllBook();
				req.setAttribute("allBook", allBook);
				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/s_gro/listS_book_dets_ByCompositeQuery.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
