package com.cou.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.cou.model.*;
import com.mem.model.*;




public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_cou_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cou_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer cou_no = null;
				try {
					cou_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("優惠編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CouponService couSvc = new CouponService();
				CouponVO couVO = couSvc.getOneCoupon(cou_no);
				if (couVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=search");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couVO", couVO); // 資料庫取出的couVO物件,存入req
				String url = "/Back-End/cou/listOneCoupon.jsp?action=search";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneCoupon.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=search");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCoupon.jsp 或  /mem/listMems_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/cou/listAllCoupon.jsp】 或  【/mem/listEmps_ByDeptno.jsp】 或 【 /mem/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer cou_no = new Integer(req.getParameter("cou_no"));
				
				/***************************2.開始查詢資料****************************************/
				CouponService couSvc = new CouponService();
				CouponVO couVO = couSvc.getOneCoupon(cou_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("couVO", couVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back-End/cou/Coupon.jsp?action=update";
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
		
		if ("update".equals(action)) { // 來自update_cou_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/cou/listAllCoupon.jsp】 或  【/mem/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer cou_no = new Integer(req.getParameter("cou_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
	
				
				java.sql.Date cou_start = null;
				try {
					cou_start = java.sql.Date.valueOf(req.getParameter("cou_start").trim());
				} catch (IllegalArgumentException e) {
					cou_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠開始日期!");
				}
				java.sql.Date cou_end = null;
				try {
					cou_end = java.sql.Date.valueOf(req.getParameter("cou_end").trim());
				} catch (IllegalArgumentException e) {
					cou_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠截止日期!");
				}
				String cou_exp = req.getParameter("cou_exp").trim();
				if (cou_exp == null || (cou_exp.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠內容");
				}
				String cou_name = req.getParameter("cou_name").trim();
				if (cou_name == null || (cou_name.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠名稱");
				}

				Double cou_dis = null;
				try {
					cou_dis = new Double(req.getParameter("cou_dis").trim());
				} catch (NumberFormatException e) {
					cou_dis = 0.0;
					errorMsgs.add("請輸入折扣金額.");
				}

				


				CouponVO couVO = new CouponVO();
				couVO.setCouno(cou_no);
				couVO.setMemno(mem_no);
				couVO.setCoustart(cou_start);
				couVO.setCouend(cou_end);
				couVO.setCouexp(cou_exp);
				couVO.setCouname(cou_name);
				couVO.setCoudis(cou_dis);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couVO", couVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=update");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CouponService couSvc = new CouponService();
				couVO = couSvc.updateCoupon(cou_no, cou_start, cou_end, cou_dis, cou_exp,mem_no, cou_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				MemService memSvc = new MemService();
//				if(requestURL.equals("/mem/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",memSvc.getEmpsByDeptno(deptno)); // 資料庫取出的list物件,存入request
//還要改
                String url ="/Back-End/cou/Coupon.jsp?action=all";
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=update");
				failureView.forward(req, res);
			}
		}
		 if ("insert".equals(action)) { // 來自addCoupon.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer mem_no = new Integer(req.getParameter("mem_no"));
				
						
					
					
					String cou_name = req.getParameter("cou_name").trim();
					if (cou_name == null || (cou_name.trim()).length() == 0) {
						errorMsgs.add("請輸入優惠名稱");
					}					
					java.sql.Date cou_start = null;
					try {
						cou_start = java.sql.Date.valueOf(req.getParameter("cou_start").trim());
					} catch (IllegalArgumentException e) {
						cou_start=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入優惠開始日期!");
					}
					java.sql.Date cou_end = null;
					try {
						cou_end = java.sql.Date.valueOf(req.getParameter("cou_end").trim());
					} catch (IllegalArgumentException e) {
						cou_end=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入優惠截止日期!");
					}
					String cou_exp = req.getParameter("cou_exp").trim();
					if (cou_exp == null || (cou_exp.trim()).length() == 0) {
						errorMsgs.add("請輸入優惠內容");
					}
					Double cou_dis = null;
					try {
						cou_dis = new Double(req.getParameter("cou_dis").trim());
					} catch (NumberFormatException e) {
						cou_dis = 0.0;
						errorMsgs.add("請輸入折扣金額.");
					}
					

					CouponVO couVO = new CouponVO();
					couVO.setCoustart(cou_start);
					couVO.setCouend(cou_end);
					couVO.setCouexp(cou_exp);
					couVO.setCouname(cou_name);
					couVO.setCoudis(cou_dis);
					couVO.setMemno(mem_no);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("couVO", couVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=add");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					CouponService couSvc = new CouponService();
					couVO = couSvc.addCoupon(  cou_start, cou_end,  cou_dis,  cou_exp,
							 mem_no,  cou_name);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/Back-End/cou/Coupon.jsp?action=all";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=add");
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
					Integer cou_no = new Integer(req.getParameter("cou_no"));
					
					/***************************2.開始刪除資料***************************************/
					CouponService couSvc = new CouponService();
					CouponVO couVO = couSvc.getOneCoupon(cou_no);
					couSvc.deleteCoupon(cou_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//					MemService deptSvc = new MemService();
//					if(requestURL.equals("/cou/listAllCoupon.jsp") || requestURL.equals("/cou/listAllDept.jsp"))
						req.setAttribute("listOneInfo",couSvc.getOneCoupon(couVO.getCouno())); // 資料庫取出的list物件,存入request
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher("/Back-End/cou/Coupon.jsp?action=all"); // 刪除成功後,轉交回送出刪除的來源網頁
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