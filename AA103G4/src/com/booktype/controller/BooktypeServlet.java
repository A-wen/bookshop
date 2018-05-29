package com.booktype.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.book.model.*;
import com.booktype.model.*;

public class BooktypeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String queryurl = "/Back-End/booktype/booktype.jsp?action=query";
		String addurl = "/Back-End/booktype/booktype.jsp?action=add";
		String updateurl = "/Back-End/booktype/booktype.jsp?action=update";
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("delete_Booktype".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer type_no = new Integer(req.getParameter("type_no"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				BooktypeService booktypeSvc = new BooktypeService();
				booktypeSvc.deleteBooktype(type_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				req.setAttribute("deleteBooktype", "deleteBooktype");
				RequestDispatcher successView = req.getRequestDispatcher(queryurl);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				req.setAttribute("deletefail", "deletefail");
				RequestDispatcher failureView = req.getRequestDispatcher(queryurl);
				failureView.forward(req, res);
			}
		}
		if ("insert_Booktype".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("addMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 ***************************************/
				String type_name;
				try {
					type_name = req.getParameter("type_name").trim();
					if (type_name.length() == 0) {
						type_name = "";
						errorMsgs.add("書籍類別名稱不得為空白");
					}
				} catch (NullPointerException e) {
					type_name = "";
					errorMsgs.add("書籍類別名稱不得為空白");
				}
				BooktypeVO booktypeVO = new BooktypeVO();
				booktypeVO.setType_name(type_name);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("insertbooktypeVO", booktypeVO); 
					RequestDispatcher failureView = req.getRequestDispatcher(addurl);
					failureView.forward(req, res);
					return;
				}
				/**************************** 2.開始新增資料 ***************************************/
				BooktypeService booktypeSvc = new BooktypeService();
				booktypeSvc.addBooktype(type_name);
				/**************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("addBooktype", "addBooktype");
				RequestDispatcher successView = req.getRequestDispatcher(queryurl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(addurl);
				failureView.forward(req, res);
			}
		}

		if ("getOneBooktype_For_Update".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 ***************************************/
				Integer type_no = new Integer(req.getParameter("type_no"));

				/**************************** 2.開始查詢資料 ***************************************/
				BooktypeService booktypeSvc = new BooktypeService();
				BooktypeVO booktypeVO = booktypeSvc.getOneBooktype(type_no);
				/**************************** 3.查詢完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("updateBooktypeVO", booktypeVO);
				RequestDispatcher successView = req.getRequestDispatcher(updateurl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("查詢資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(updateurl);
				failureView.forward(req, res);
			}
		}

		if ("update_Booktype".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 ***************************************/
				Integer type_no = new Integer(req.getParameter("type_no"));
				String type_name;
				try {
					type_name = req.getParameter("type_name").trim();
					if (type_name.length() == 0) {
						type_name = "請重新輸入";
						errorMsgs.add("書籍類別名稱不得為空白");
					}
				} catch (ClassCastException e) {
					type_name = "請重新輸入";
					errorMsgs.add("");
				}
				BooktypeVO booktypeVO = new BooktypeVO();
				booktypeVO.setType_no(type_no);
				booktypeVO.setType_name(type_name);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("updatebooktypeVO", booktypeVO);
					RequestDispatcher failureView = req.getRequestDispatcher(updateurl);
					failureView.forward(req, res);
					return;
				}

				/**************************** 2.開始修改資料 ***************************************/
				BooktypeService booktypeSvc = new BooktypeService();
				booktypeSvc.updateBooktype(type_no, type_name);

				/*************************** * 3.修改完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("updateBooktype", "updateBooktype");
				RequestDispatcher successView = req.getRequestDispatcher(queryurl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(updateurl);
				failureView.forward(req, res);
			}
		}

	}

}
