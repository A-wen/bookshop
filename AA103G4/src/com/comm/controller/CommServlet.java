package com.comm.controller;

import java.io.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.comm.model.*;

public class CommServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		// 來自select_page.jsp的請求 // 來自 dept/listAllDept.jsp的請求
		
		
		if ("getCommByMem".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				CommService commSvc = new CommService();
				List<CommVO> list = commSvc.getCommByMem(mem_no);
				if(list.size()==0){
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("listCommByMem", list);
				String url = "/comm/listCommByMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,
																				// 成功轉交
																				// 回到
																				// /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getCommByBook".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer book_no = new Integer(req.getParameter("book_no"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				CommService commSvc = new CommService();
				List<CommVO> list = commSvc.getCommByBook(book_no);
				if(list.size()==0){
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("listCommByBook", list);
				String url = "/comm/listCommByBook.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,
																				// 成功轉交
																				// 回到
																				// /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete_Comm".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer comm_no = new Integer(req.getParameter("comm_no"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				CommService commSvc = new CommService();
				CommVO commVO = commSvc.getOneComm(comm_no);
				commSvc.deleteComm(comm_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				if(requestURL.equals("/comm/listCommByMem.jsp")){
					List<CommVO> list = commSvc.getCommByMem(commVO.getMem_no());
					if(list.size()!=0){
						req.setAttribute("listCommByMem", list);
					}else{
						requestURL ="/comm/listAllComm.jsp";
					}
				}
				if(requestURL.equals("/comm/listCommByBook.jsp")){
					List<CommVO> list = commSvc.getCommByBook(commVO.getBook_no());
					if(list.size()!=0){
						req.setAttribute("listCommByBook", list);
					}else{
						requestURL ="/comm/listAllComm.jsp";
					}
				}
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,
																				// 成功轉交
																				// 回到
																				// /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/comm/listAllComm.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_Comm".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************  * 1.接收請求參數  ***************************************/
				Integer book_no= new Integer(req.getParameter("book_no").trim());
				Integer mem_no= new Integer(req.getParameter("mem_no").trim());
				String comm_desc;
				try {
					comm_desc = req.getParameter("comm_desc").trim();
					if (comm_desc.length() == 0) {
						comm_desc = "請重新輸入";
						errorMsgs.add("評論內容不得為空白");
					}
				} catch (ClassCastException e) {
					comm_desc = "請重新輸入";
					errorMsgs.add("");
				}
				Integer comm_level = new Integer(req.getParameter("comm_level").trim());

				

				CommVO commVO = new CommVO();
				commVO.setBook_no(book_no);
				commVO.setMem_no(mem_no);
				commVO.setComm_desc(comm_desc);
				commVO.setComm_level(comm_level);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("commVO", commVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/comm/addComm.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************
				 * 2.開始新增資料
				 ***************************************/
				CommService commSvc = new CommService();
				commSvc.addComm(book_no,mem_no,comm_desc,comm_level);
				/************************** 3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/comm/listAllComm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,
																				// 成功轉交
																				// 回到
																				// /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/comm/listAllComm.jsp");
				failureView.forward(req, res);
			}
		}



	}

}
