package com.s_book.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_book.model.*;
import com.s_gro_dis.model.S_gro_disVO;
import com.s_gro_info.model.S_gro_infoService;
import com.s_gro_info.model.S_gro_infoVO;

public class S_bookServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("s_book_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入讀書會書單編號");
				}
//				System.out.println(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer s_book_no = null;
				try {
					s_book_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("讀書會書單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				S_bookService s_bookSvc = new S_bookService();
				S_bookVO s_bookVO = s_bookSvc.getOneS_book(s_book_no);
				if (s_bookVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("s_bookVO", s_bookVO); // 資料庫取出的empVO物件,存入req
				String url = "/s_book/listOneS_book.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】	
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer s_book_no = new Integer(req.getParameter("s_book_no"));
				
				/***************************2.開始查詢資料****************************************/
				S_bookService empSvc = new S_bookService();
				S_bookVO s_bookVO = empSvc.getOneS_book(s_book_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("s_bookVO", s_bookVO);         // 資料庫取出的empVO物件,存入req
				String url = "/s_book/update_s_book_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer s_book_no = new Integer(req.getParameter("s_book_no").trim());
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());				
				
				java.sql.Date cre_date = null;
				try {
					cre_date = java.sql.Date.valueOf(req.getParameter("cre_date").trim());
				} catch (IllegalArgumentException e) {
					cre_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				S_bookVO s_bookVO = new S_bookVO();
				s_bookVO.setS_book_no(s_book_no);
				s_bookVO.setS_gro_no(s_gro_no);
				s_bookVO.setCre_date(cre_date);
				s_bookVO.setEnd_date(end_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_bookVO", s_bookVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_book/update_s_book_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				S_bookService empSvc = new S_bookService();
				s_bookVO = empSvc.updateS_book(s_book_no, s_gro_no, cre_date, end_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("s_bookVO", s_bookVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_book/update_s_book_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());
				
				java.sql.Date cre_date = null;
				try {
					cre_date = java.sql.Date.valueOf(req.getParameter("cre_date").trim());
				} catch (IllegalArgumentException e) {
					cre_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
				} catch (IllegalArgumentException e) {
					end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				S_bookVO s_bookVO = new S_bookVO();
				s_bookVO.setS_gro_no(s_gro_no);
				s_bookVO.setCre_date(cre_date);
				s_bookVO.setEnd_date(end_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_bookVO", s_bookVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_book/addS_book.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				S_bookService s_bookSvc = new S_bookService();
				s_bookVO = s_bookSvc.addS_book(s_gro_no, cre_date, end_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/s_book/listAllS_book.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_book/addS_book.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer s_book_no = new Integer(req.getParameter("s_book_no"));

				/***************************2.開始刪除資料***************************************/
				S_bookService s_bookSvc = new S_bookService();
				s_bookSvc.deleteS_book(s_book_no);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				if(requestURL.equals("/s_book/listS_books_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<S_bookVO> list  = s_bookSvc.getAll(map);
					req.setAttribute("listS_books_ByCompositeQuery",list); 
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listS_books_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				}
				
				/***************************2.開始複合查詢***************************************/
				S_bookService s_bookSvc = new S_bookService();
				List<S_bookVO> list  = s_bookSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listS_books_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/s_gro/listS_books_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("club_book".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
		

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String url = "/Front-End/s_gro/listAllS_book.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher( "/Front-End/HomePage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
