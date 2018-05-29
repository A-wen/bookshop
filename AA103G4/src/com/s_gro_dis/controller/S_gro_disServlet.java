package com.s_gro_dis.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_book.model.S_bookService;
import com.s_book.model.S_bookVO;
import com.s_gro_dis.model.*;

public class S_gro_disServlet extends HttpServlet{
	
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
				String str = req.getParameter("dis_ar_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入讀書會書單編號");
				}
				System.out.println(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer dis_ar_no = null;
				try {
					dis_ar_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("討論文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				S_gro_disVO s_gro_disVO = s_gro_disSvc.getOneS_gro_dis(dis_ar_no);
				if (s_gro_disVO == null) {
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
				req.setAttribute("s_gro_disVO", s_gro_disVO); // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_dis/listOneS_gro_dis.jsp";
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
				Integer dis_ar_no = new Integer(req.getParameter("dis_ar_no"));
				
				/***************************2.開始查詢資料****************************************/
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				S_gro_disVO s_gro_disVO = s_gro_disSvc.getOneS_gro_dis(dis_ar_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("s_gro_disVO", s_gro_disVO);         // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_dis/update_s_gro_dis_input.jsp";
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
				Integer dis_ar_no = new Integer(req.getParameter("dis_ar_no").trim());
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String title = req.getParameter("title").trim();
				String dis_con = req.getParameter("dis_con").trim();
				
				java.sql.Date ht_date = null;
				try {
					ht_date = java.sql.Date.valueOf(req.getParameter("ht_date").trim());
				} catch (IllegalArgumentException e) {
					ht_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
								
				S_gro_disVO s_gro_disVO = new S_gro_disVO();
				s_gro_disVO.setDis_ar_no(dis_ar_no);
				s_gro_disVO.setS_gro_no(s_gro_no);
				s_gro_disVO.setMem_no(mem_no);
				s_gro_disVO.setTitle(title);
				s_gro_disVO.setDis_con(dis_con);
				s_gro_disVO.setHt_date(ht_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_disVO", s_gro_disVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_dis/update_s_gro_dis_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				s_gro_disVO = s_gro_disSvc.updateS_gro_dis(dis_ar_no, s_gro_no, mem_no, title, 
					 dis_con, ht_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("s_gro_disVO", s_gro_disVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				if(requestURL.equals("/s_gro_dis/listS_gro_diss_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<S_gro_disVO> list  = s_gro_disSvc.getAll(map);
					req.setAttribute("listS_gro_diss_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_dis/update_s_gro_dis_input.jsp");
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
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String title = req.getParameter("title").trim();
				String dis_con = req.getParameter("dis_con").trim();
				
				java.sql.Date ht_date = null;
				try {
					ht_date = java.sql.Date.valueOf(req.getParameter("ht_date").trim());
				} catch (IllegalArgumentException e) {
					ht_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
								
				S_gro_disVO s_gro_disVO = new S_gro_disVO();
				s_gro_disVO.setS_gro_no(s_gro_no);
				s_gro_disVO.setMem_no(mem_no);
				s_gro_disVO.setTitle(title);
				s_gro_disVO.setDis_con(dis_con);
				s_gro_disVO.setHt_date(ht_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_disVO", s_gro_disVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_dis/addS_gro_dis.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				s_gro_disVO = s_gro_disSvc.addS_gro_dis(s_gro_no, mem_no, title, 
					 dis_con, ht_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/s_gro_dis/listAllS_gro_dis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_dis/addS_gro_dis.jsp");
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
				Integer dis_ar_no = new Integer(req.getParameter("dis_ar_no"));
				
				/***************************2.開始刪除資料***************************************/
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				S_gro_disVO s_gro_disVO = s_gro_disSvc.getOneS_gro_dis(dis_ar_no);
				s_gro_disSvc.deleteS_gro_dis(dis_ar_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				if(requestURL.equals("/s_gro_dis/listS_gro_diss_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<S_gro_disVO> list  = s_gro_disSvc.getAll(map);
					req.setAttribute("listS_gro_diss_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listS_gro_diss_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				S_gro_disService s_gro_disSvc = new S_gro_disService();
				List<S_gro_disVO> list  = s_gro_disSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listS_gro_diss_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/s_gro/listS_gro_diss_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
