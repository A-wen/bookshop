package com.s_gro_cs.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_gro_cs.model.*;
import com.s_gro_info.model.*;

public class S_gro_csServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
				if ("listS_gro_infos_ByCs_no_A".equals(action) || "listS_gro_infos_ByCs_no_B".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ****************************************/
						Integer cs_no = new Integer(req.getParameter("cs_no"));

						/*************************** 2.開始查詢資料 ****************************************/
						S_gro_csService s_gro_csSvc = new S_gro_csService();
						Set<S_gro_infoVO> set = s_gro_csSvc.getS_gro_infosByCs_no(cs_no);

						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("listS_gro_infos_ByCs_no", set);    // 資料庫取出的set物件,存入request

						String url = null;
						if ("listS_gro_infos_ByCs_no_A".equals(action))
							url = "/Front-End/s_gro/listS_gro_infos_ByCs_no.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
						else if ("listS_gro_infos_ByCs_no_B".equals(action))
							url = "/Front-End/s_gro/listAllS_gro_cs.jsp";              // 成功轉交 dept/listAllDept.jsp

						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 ***********************************/
					} catch (Exception e) {
						throw new ServletException(e);
					}
				}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cs_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入類別名稱");
				}
				System.out.println(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer cs_no = null;
				try {
					cs_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("類別名稱格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				S_gro_csService empSvc = new S_gro_csService();
				S_gro_csVO s_gro_csVO = empSvc.getOneS_gro_cs(cs_no);
				if (s_gro_csVO == null) {
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
				req.setAttribute("s_gro_csVO", s_gro_csVO); // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_cs/listOneS_gro_cs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
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
				Integer cs_no = new Integer(req.getParameter("cs_no"));
				
				/***************************2.開始查詢資料****************************************/
				S_gro_csService empSvc = new S_gro_csService();
				S_gro_csVO s_gro_csVO = empSvc.getOneS_gro_cs(cs_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("s_gro_csVO", s_gro_csVO);         // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_cs/update_s_gro_cs_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
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
				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
				String cs_name = req.getParameter("cs_name").trim();				
				
				S_gro_csVO s_gro_csVO = new S_gro_csVO();
				s_gro_csVO.setCs_no(cs_no);
				s_gro_csVO.setCs_name(cs_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_csVO", s_gro_csVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_cs/update_s_gro_cs_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				S_gro_csService empSvc = new S_gro_csService();
				s_gro_csVO = empSvc.updateS_gro_cs(cs_no, cs_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("s_bookVO", s_gro_csVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_cs/update_s_gro_cs_input.jsp");
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
				String cs_name = req.getParameter("cs_name").trim();				
				
				S_gro_csVO s_gro_csVO = new S_gro_csVO();
				s_gro_csVO.setCs_name(cs_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_csVO", s_gro_csVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_cs/addS_gro_cs.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				S_gro_csService s_gro_csSvc = new S_gro_csService();
				s_gro_csVO = s_gro_csSvc.addS_gro_cs(cs_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/s_gro_info/addS_gro_ingo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_cs/addS_gro_cs.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer cs_no = new Integer(req.getParameter("cs_no"));
				
				/***************************2.開始刪除資料***************************************/
				S_gro_csService s_gro_csSvc = new S_gro_csService();
				s_gro_csSvc.deleteS_gro_cs(cs_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/s_gro_cs/listAllS_gro_cs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_cs/listAllS_gro_cs.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
