package com.pm.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.pm.model.*;
import com.pd.model.*;
import com.book.model.*;


public class PmServlet extends  HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
		
		if ("insert_Pm".equals(action)) { // 來自/dept/listAllDept.jsp的請求
		
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer pd_no = new Integer(req.getParameter("pd_no"));
					Integer book_no = new Integer(req.getParameter("book_no"));
		
					PmVO pmVO = new PmVO();
					pmVO.setPd_no(pd_no);
					pmVO.setBook_no(book_no);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("pmVO", pmVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/pm/addPm.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始新增資料***************************************/
					PmService pmSvc = new PmService();
					pmSvc.addPm(pd_no,book_no);
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					String url = "/pm/listAllPm.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理***********************************/
				} catch (Exception e) {
					errorMsgs.add("新增資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pm/listAllPm.jsp");
					failureView.forward(req, res);
				}
			}
		
		if ("insert_many_Pm".equals(action)) { // 來自/dept/listAllDept.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer pd_no = new Integer(req.getParameter("pd_no"));
				String[] book_no = req.getParameterValues("book_no");
				/***************************2.開始新增資料***************************************/
				PmService pmSvc = new PmService();
				for(int i=0;i<book_no.length;i++){
				pmSvc.addPm(pd_no,new Integer(book_no[i]));
				}
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/pm/listAllPm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pm/listAllPm.jsp");
				failureView.forward(req, res);
			}
		}
		
	if ("delete_Pm".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String requestURL = req.getParameter("requestURL");
		try {
			/***************************1.接收請求參數***************************************/
			Integer pd_no = new Integer(req.getParameter("pd_no"));
			Integer book_no = new Integer(req.getParameter("book_no"));
			
			/***************************2.開始刪除資料***************************************/
			PmService pmSvc = new PmService();
			pmSvc.deletePm(pd_no,book_no);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			if(requestURL.equals("/pm/listBookByPm.jsp")){
				List<PmVO> list = pmSvc.getBooksByPm(pd_no);
				req.setAttribute("listBookByPm", list);
			}
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/pm/listAllPm.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("getBookByPm".equals(action)) { // 來自/dept/listAllDept.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			Integer pd_no = new Integer(req.getParameter("pd_no"));
	
			/***************************2.開始新增資料***************************************/
			PmService pmSvc = new PmService();
			List<PmVO> list = pmSvc.getBooksByPm(pd_no);
			if (list.size() ==0 ) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			req.setAttribute("listBookByPm", list);
			String url = "/pm/listBookByPm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("新增資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/pm/listAllPm.jsp");
			failureView.forward(req, res);
		}
	}

	}
}
