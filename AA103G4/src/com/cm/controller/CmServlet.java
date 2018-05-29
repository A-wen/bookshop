package com.cm.controller;

import java.io.*;
import java.util.*;
import com.cm.model.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/uploadServlet3.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)



public class CmServlet extends HttpServlet {
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
				String str = req.getParameter("cm_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer cm_no = null;
				try {
					cm_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CmService cmSvc = new CmService();
				CmVO cmVO = cmSvc.getOneCm(cm_no);
				if (cmVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cmVO", cmVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/cm/listOneCm.jsp?action=search";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=search");
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
				Integer cm_no = new Integer(req.getParameter("cm_no"));
				
				
				/***************************2.開始查詢資料****************************************/
				CmService cmSvc = new CmService();
				CmVO cmVO = cmSvc.getOneCm(cm_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cmVO", cmVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/cm/CommercialMessage.jsp?action=update";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_cm_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_cm_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer cm_no = new Integer(req.getParameter("cm_no").trim());
				String cm_name = req.getParameter("cm_name").trim();
								
				java.sql.Date cm_start = null;
				try {
					cm_start = java.sql.Date.valueOf(req.getParameter("cm_start").trim());
				} catch (IllegalArgumentException e) {
					cm_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}	
				java.sql.Date cm_end = null;
				try {
					cm_end = java.sql.Date.valueOf(req.getParameter("cm_end").trim());
				} catch (IllegalArgumentException e) {
					cm_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}	
				
				Integer cm_th = new Integer(req.getParameter("cm_th"));
				String cm_inv = req.getParameter("cm_inv").trim();
				String cm_url = req.getParameter("cm_url").trim();
				
				

				CmVO cmVO = new CmVO();				
								
				cmVO.setCmNo(cm_no);
				cmVO.setCmName(cm_name);
				cmVO.setCmStart(cm_start);
				cmVO.setCmEnd(cm_end);		
				
				Part part = req.getPart("cm_pic");
				InputStream in = part.getInputStream();
				byte[] cm_pic = new byte[in.available()];
				in.read(cm_pic);
				cmVO.setCmPic(cm_pic);
				in.close();
				
				cmVO.setCmTh(cm_th);
				cmVO.setCminv(cm_inv);
				cmVO.setCmUrl(cm_url);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmVO", cmVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=update");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CmService cmSvc = new CmService();
				cmVO = cmSvc.updateCm(cm_no,cm_th,cm_name,cm_inv,cm_url,cm_start,cm_end,cm_pic);
		
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				 // 資料庫取出的list物件,存入request
				

                String url = "/Back-End/cm/CommercialMessage.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=update");
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
				String cm_name = req.getParameter("cm_name").trim();
				
				
				java.sql.Date cm_start = null;
				try {
					cm_start = java.sql.Date.valueOf(req.getParameter("cm_start").trim());
				} catch (IllegalArgumentException e) {
					cm_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				java.sql.Date cm_end = null;
				try {
					cm_end = java.sql.Date.valueOf(req.getParameter("cm_end").trim());
				} catch (IllegalArgumentException e) {
					cm_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				
				String cm_inv = req.getParameter("cm_inv").trim();
				String cm_url = req.getParameter("cm_url").trim();
				Integer cm_th = new Integer(req.getParameter("cm_th").trim());

				
				CmVO cmVO = new CmVO();
				cmVO.setCmName(cm_name);
				cmVO.setCmStart(cm_start);
				cmVO.setCmEnd(cm_end);		
				Part part = req.getPart("cm_pic");
				InputStream in = part.getInputStream();
				byte[] cm_pic = new byte[in.available()];
				in.read(cm_pic);
				cmVO.setCmPic(cm_pic);
				in.close();
				
				
				cmVO.setCminv(cm_inv);
				cmVO.setCmUrl(cm_url);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmVO", cmVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=add");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CmService cmSvc = new CmService();
				cmVO = cmSvc.addCm(cm_th,cm_name,cm_inv,cm_url,cm_start,cm_end,cm_pic);				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back-End/cm/CommercialMessage.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCm.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=add");
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
				Integer cm_no = new Integer(req.getParameter("cm_no"));
				
				/***************************2.開始刪除資料***************************************/
				CmService cmSvc = new CmService();
				CmVO cmVO = cmSvc.getOneCm(cm_no);
				cmSvc.deleteCm(cm_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				InformationService infoSvc = new InformationService();
				if(requestURL.equals("/cm/listOneCm.jsp")  || requestURL.equals("/cm/listAllCm.jsp"))
					req.setAttribute("listOneCm",cmSvc.getOneCm(cmVO.getCmNo())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher("/Back-End/cm/CommercialMessage.jsp?action=all"); // 刪除成功後,轉交回送出刪除的來源網頁
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
