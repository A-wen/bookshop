package com.info.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.info.model.*;


public class InformationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//查公告編號
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("info_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer info_no = null;
				try {
					info_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				InformationService infoSvc = new InformationService();
				InformationVO infoVO = infoSvc.getOneInformation(info_no);
				if (infoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("infoVO", infoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/info/listOneInfo.jsp?action=search";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=search");
				failureView.forward(req, res);
			}
		}
		
		//修改
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求
			System.out.println("進入修改");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer info_no = new Integer(req.getParameter("info_no"));
				
				/***************************2.開始查詢資料****************************************/
				InformationService infoSvc = new InformationService();
				InformationVO infoVO = infoSvc.getOneInformation(info_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("infoVO", infoVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/info/backhomeinfo.jsp?action=update";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_info_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_info_input.jsp的請求
			System.out.println("進入修改2");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer info_no = new Integer(req.getParameter("info_no").trim());
				
				String info_title = req.getParameter("info_title").trim();
				if (info_title == null || (info_title.trim()).length() == 0) {
					errorMsgs.add("請輸入公告標題");
				}	
				System.out.println(req.getParameter("info_exp"));
				String info_exp = req.getParameter("info_exp").trim();	
				if (info_exp == null || (info_exp.trim()).length() == 0) {
					errorMsgs.add("請輸入公告內容");
				}
				
				java.sql.Date info_term = null;
				try {
					info_term = java.sql.Date.valueOf(req.getParameter("info_term").trim());
				} catch (IllegalArgumentException e) {
					info_term=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}			

				InformationVO infoVO = new InformationVO();
				infoVO.setInfono(info_no);
				infoVO.setInfotitle(info_title);
				infoVO.setInfoexp(info_exp);
				infoVO.setInfoterm(info_term);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=update");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				InformationService infoSvc = new InformationService();
				infoVO = infoSvc.updateInformation(info_no,info_term ,info_exp,info_title);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				 // 資料庫取出的list物件,存入request
				
				req.setAttribute("infoVO", infoVO);
                String url = "/Back-End/info/backhomeinfo.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=update");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addInformation.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);		
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String info_title = req.getParameter("info_title").trim();
				if (info_title == null || (info_title.trim()).length() == 0) {
					errorMsgs.add("請輸入公告標題");
				}
				String info_exp = req.getParameter("info_exp").trim();
				if (info_exp == null || (info_exp.trim()).length() == 0) {
					errorMsgs.add("請輸入公告內容");
				}
				
			
				java.sql.Date info_term = null;
				try {
					info_term = java.sql.Date.valueOf(req.getParameter("info_term").trim());
				} catch (IllegalArgumentException e) {
					info_term=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				InformationVO infoVO = new InformationVO();
				infoVO.setInfoterm(info_term); 
				infoVO.setInfoexp(info_exp);
				infoVO.setInfotitle(info_title);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("infoVO", infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=add");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				InformationService infoSvc = new InformationService();
				infoVO = infoSvc.addInformation(info_term,info_exp,info_title);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back-End/info/backhomeinfo.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllInfo.jsp
				successView.forward(req, res);				
				return;
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=query");
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
				Integer info_no = new Integer(req.getParameter("info_no"));
				
				/***************************2.開始刪除資料***************************************/
				InformationService infoSvc = new InformationService();
				InformationVO infoVO = infoSvc.getOneInformation(info_no);
				infoSvc.deleteInformation(info_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/

				if(requestURL.equals("/info/listOneInfo.jsp")  || requestURL.equals("/Back-End/info/backhomeinfo.jsp?action=all "))
					req.setAttribute("listOneInfo",infoSvc.getOneInformation(infoVO.getInfono())); // 資料庫取出的list物件,存入request
				req.getQueryString();
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher("/Back-End/info/backhomeinfo.jsp?action=all"); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
