package com.ca.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.mem.model.*;
import com.ca.model.*;

public class CaServlet extends HttpServlet {

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
				String str = req.getParameter("ca_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}	
							
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=query");

					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ca_no = null;
				try {
					ca_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
		
					RequestDispatcher failureView = req.
							getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=query");

					failureView.forward(req, res);
					return;//程式中斷
					}
				
				/***************************2.開始查詢資料*****************************************/
				ClassifiedAdsService caSvc = new ClassifiedAdsService();
				ClassifiedAdsVO caVO = caSvc.getOneClassifiedAds(ca_no);
				if (caVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
		
					RequestDispatcher failureView = req.
							getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=query");

					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("caVO", caVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/ca/listOneCa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
		
				RequestDispatcher failureView = req.
						getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=query");

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
				Integer ca_no = new Integer(req.getParameter("ca_no"));
				
				/***************************2.開始查詢資料****************************************/
				ClassifiedAdsService caSvc = new ClassifiedAdsService();
				ClassifiedAdsVO caVO = caSvc.getOneClassifiedAds(ca_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("caVO", caVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/ca/ClassifiedAds.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
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
			
			//String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ca_no = new Integer(req.getParameter("CA_NO").trim());
				Integer type_no = new Integer(req.getParameter("TYPE_NO").trim());
				Integer book_no = new Integer(req.getParameter("BOOK_NO").trim());
				
				
				String ca_name = req.getParameter("CA_NAME").trim();
				if (ca_name == null)
				{
					errorMsgs.add("請輸入廣告書名");
				}
				
				Integer ca_th = new Integer(req.getParameter("ca_th").trim());	
				if (ca_th == null)
				{
					errorMsgs.add("請輸入點擊次數");
				}
				
				java.sql.Date ca_start = null;
				try {
					ca_start = java.sql.Date.valueOf(req.getParameter("ca_start").trim());
				} catch (IllegalArgumentException e) {
					ca_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date ca_end = null;
				try {
					ca_end = java.sql.Date.valueOf(req.getParameter("ca_end").trim());
				} catch (IllegalArgumentException e) {
					ca_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}

			
				

				ClassifiedAdsVO caVO = new ClassifiedAdsVO();
				caVO.setCano(ca_no);
				caVO.setTypeno(type_no);
				caVO.setBookno(book_no);			
				caVO.setCastart(ca_start);
				caVO.setCaend(ca_end);
				caVO.setCath(ca_th);
				caVO.setCaname(ca_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("caVO", caVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=update");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ClassifiedAdsService caSvc = new ClassifiedAdsService();
				caVO = caSvc.updateClassifiedAds( ca_no, type_no,book_no , ca_start, ca_end, ca_th, ca_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
				req.setAttribute("listOneCa",caSvc.getOneClassifiedAds(caVO.getCano())); // 資料庫取出的list物件,存入request
				
//				if(requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<EmpVO> list  = empSvc.getAll(map);
//					req.setAttribute("listEmps_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//				}
				String url = "/Back-End/ca/ClassifiedAds.jsp?action=all";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=update");
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
				Integer ca_no = new Integer(req.getParameter("CA_NO").trim());
				Integer type_no = new Integer(req.getParameter("TYPE_NO").trim());
				Integer book_no = new Integer(req.getParameter("BOOK_NO").trim());
				String ca_name = req.getParameter("CA_NAME").trim();
				if (ca_name == null)
				{
					errorMsgs.add("請輸入廣告書名");
				}
				
				Integer ca_th = new Integer(req.getParameter("ca_th").trim());	
				if (ca_th == null)
				{
					errorMsgs.add("請輸入點擊次數");
				}
				
				java.sql.Date ca_start = null;
				try {
					ca_start = java.sql.Date.valueOf(req.getParameter("ca_start").trim());
				} catch (IllegalArgumentException e) {
					ca_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date ca_end = null;
				try {
					ca_end = java.sql.Date.valueOf(req.getParameter("ca_end").trim());
				} catch (IllegalArgumentException e) {
					ca_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}


				ClassifiedAdsVO caVO = new ClassifiedAdsVO();
				caVO.setTypeno(type_no);
				caVO.setBookno(book_no);	
				caVO.setCastart(ca_start);
				caVO.setCaend(ca_end);
				caVO.setCath(ca_th);
				caVO.setCaname(ca_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("caVO", caVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=add");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ClassifiedAdsService caSvc = new ClassifiedAdsService();
				caVO = caSvc.addClassifiedAds( type_no,book_no,ca_start,ca_end,ca_th,ca_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back-End/ca/ClassifiedAds.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=add");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				Integer ca_no = new Integer(req.getParameter("ca_no"));
				
				/***************************2.開始刪除資料***************************************/
				ClassifiedAdsService caSvc = new ClassifiedAdsService();
				ClassifiedAdsVO caVO = caSvc.getOneClassifiedAds(ca_no);
				caSvc.deleteClassifiedAds(ca_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
					req.setAttribute("listOneCa",caSvc.getOneClassifiedAds(caVO.getCano())); // 資料庫取出的list物件,存入request
//				
//				if(requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<EmpVO> list  = empSvc.getAll(map);
//					req.setAttribute("listEmps_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//				}
				
				String url = "/Back-End/ca/ClassifiedAds.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/ca/ClassifiedAds.jsp?action=query");
				failureView.forward(req, res);
			}
		}
		
//		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//
//				/***************************
//				 * 1.將輸入資料轉為Map
//				 **********************************/
//				// 採用Map<String,String[]> getParameterMap()的方法
//				// 注意:an immutable java.util.Map
//				// Map<String, String[]> map = req.getParameterMap();
//				HttpSession session = req.getSession();
//				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
//				if (req.getParameter("whichPage") == null) {
//					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
//					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
//					map2 = (HashMap<String, String[]>) map1.clone();
//					session.setAttribute("map", map2);
//					map = (HashMap<String, String[]>) req.getParameterMap();
//				}
//
//				/***************************
//				 * 2.開始複合查詢
//				 ***************************************/
//				EmpService empSvc = new EmpService();
//				List<EmpVO> list = empSvc.getAll(map);
//
//				/***************************
//				 * 3.查詢完成,準備轉交(Send the Success view)
//				 ************/
//				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
