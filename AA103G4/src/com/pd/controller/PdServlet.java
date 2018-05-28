package com.pd.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.pd.model.*;


public class PdServlet extends  HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
		
//	if("getOneCompany".equals(action)){
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		try{
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer comp_no = new Integer(req.getParameter("comp_no"));
//			/*************************** 2.開始查詢資料 ****************************************/
//			CompanyService CompanySvc = new CompanyService();
//			CompanyVO companyVO = CompanySvc.getOneCompany(comp_no);
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("companyVO", companyVO); 
//			
//			String url = "/company/listOneCompany.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
//			successView.forward(req, res);
//		}catch(Exception e){
//			throw new ServletException(e);
//		}
//	}
//		
//	if ("listBooks_ByCompany_A".equals(action) || "listBooks_ByCompany_B".equals(action)) {
//
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer comp_no = new Integer(req.getParameter("comp_no"));
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			CompanyService companySvc = new CompanyService();
//			List<BookVO> list = companySvc.getBookByCompany(comp_no);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("listBooks_ByCompany", list);    // 資料庫取出的set物件,存入request
//
//			String url = null;
//			if ("listBooks_ByCompany_A".equals(action))
//				url = "/company/listBooks_ByCompany.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
//			else if ("listBooks_ByCompany_B".equals(action))
//				url = "/company/listAllCompany.jsp";              // 成功轉交 dept/listAllDept.jsp
//
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//			/*************************** 其他可能的錯誤處理 ***********************************/
//		} catch (Exception e) {
//			throw new ServletException(e);
//		}
//	}
//	
//	
	if ("delete_Pd".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			Integer pd_no = new Integer(req.getParameter("pd_no"));
			
			/***************************2.開始刪除資料***************************************/
			PdService pdSvc = new PdService();
			pdSvc.deletePd(pd_no);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			String url = "/pd/listAllPd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/pd/listAllPd.jsp");
			failureView.forward(req, res);
		}
	}
	if ("insert_Pd".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String pd_name;
			try {
				pd_name = req.getParameter("pd_name").trim();
				if(pd_name.length()==0){
					pd_name = "請重新輸入";
					errorMsgs.add("出版社名稱不得為空白");
					}
			} catch (ClassCastException e) {
				pd_name = "請重新輸入";
				errorMsgs.add("");
			}
			String pd_desc;
			try {
				pd_desc = req.getParameter("pd_desc").trim();
				if(pd_desc.length()==0){
					pd_desc = "請重新輸入";
					errorMsgs.add("出版社電話不得為空白");
					}
			} catch (ClassCastException e) {
				pd_desc = "請重新輸入";
				errorMsgs.add("");
			}
			String startdate;
			try {
				startdate = req.getParameter("startdate").trim();
				if(startdate.length()==0){
					startdate = "2016-08-31";
					errorMsgs.add("日期不得為空白");
					}
			} catch (ClassCastException e) {
				startdate = "2016-08-31";
				errorMsgs.add("起始日期輸入格式錯誤");
			}
			String enddate;
			try {
				enddate = req.getParameter("enddate").trim();
				if(enddate.length()==0){
					enddate = "2016-08-31";
					errorMsgs.add("日期不得為空白");
					}
			} catch (ClassCastException e) {
				enddate = "2016-08-31";
				errorMsgs.add("結束日期輸入格式錯誤");
			}
			
			Double discount;
			try {
				discount = new Double(req.getParameter("discount").trim());

			} catch (ClassCastException e) {
				discount = 0.0;
				errorMsgs.add("請輸入數字，如: 9.5 ");
			}

			PdVO pdVO = new PdVO();
			pdVO.setPd_name(pd_name);
			pdVO.setPd_desc(pd_desc);
			pdVO.setStartdate(java.sql.Date.valueOf(startdate));
			pdVO.setEnddate(java.sql.Date.valueOf(enddate));
			pdVO.setDiscount(discount);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("pdVO", pdVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/pd/addPd.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			PdService pdService = new PdService();
			pdService.addPd(pd_name,pd_desc,startdate,enddate,discount);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			String url = "/pd/listAllPd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("新增資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/pd/listAllPd.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("getOnePd_For_Update".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			Integer pd_no = new Integer(req.getParameter("pd_no"));

			/***************************2.開始查詢資料***************************************/
			PdService pdService = new PdService();
			PdVO pdVO = pdService.getOnePd(pd_no);
			/***************************3.查詢完成,準備轉交(Send the Success view)***********/
			req.setAttribute("pdVO", pdVO);
			String url = "/pd/update_pd_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("新增資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/pd/listAllPd.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("update_Pd".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數***************************************/
			Integer pd_no = new Integer(req.getParameter("pd_no").trim());
			String pd_name;
			try {
				pd_name = req.getParameter("pd_name").trim();
				if(pd_name.length()==0){
					pd_name = "請重新輸入";
					errorMsgs.add("出版社名稱不得為空白");
					}
			} catch (ClassCastException e) {
				pd_name = "請重新輸入";
				errorMsgs.add("");
			}
			String pd_desc;
			try {
				pd_desc = req.getParameter("pd_desc").trim();
				if(pd_desc.length()==0){
					pd_desc = "請重新輸入";
					errorMsgs.add("出版社電話不得為空白");
					}
			} catch (ClassCastException e) {
				pd_desc = "請重新輸入";
				errorMsgs.add("");
			}
			String startdate;
			try {
				startdate = req.getParameter("startdate").trim();
				if(startdate.length()==0){
					startdate = "2016-08-31";
					errorMsgs.add("日期不得為空白");
					}
			} catch (ClassCastException e) {
				startdate = "2016-08-31";
				errorMsgs.add("起始日期輸入格式錯誤");
			}
			String enddate;
			try {
				enddate = req.getParameter("enddate").trim();
				if(enddate.length()==0){
					enddate = "2016-08-31";
					errorMsgs.add("日期不得為空白");
					}
			} catch (ClassCastException e) {
				enddate = "2016-08-31";
				errorMsgs.add("結束日期輸入格式錯誤");
			}
			
			Double discount;
			try {
				discount = new Double(req.getParameter("discount").trim());

			} catch (ClassCastException e) {
				discount = 0.0;
				errorMsgs.add("請輸入數字，如: 9.5 ");
			}

			PdVO pdVO = new PdVO();
			pdVO.setPd_no(pd_no);
			pdVO.setPd_name(pd_name);
			pdVO.setPd_desc(pd_desc);
			pdVO.setStartdate(java.sql.Date.valueOf(startdate));
			pdVO.setEnddate(java.sql.Date.valueOf(enddate));
			pdVO.setDiscount(discount);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("pdVO", pdVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/pd/addPd.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始修改資料***************************************/
			PdService pdSvc = new PdService();
			pdSvc.updatePd(pd_no,pd_name,pd_desc,startdate,enddate,discount);
			
			/***************************3.修改完成,準備轉交(Send the Success view)***********/
			String url = "/pd/listAllPd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/pd/listAllPd.jsp");
			failureView.forward(req, res);
			}
		}
	}
}
