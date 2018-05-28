package com.company.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.book.model.*;
import com.company.model.*;

public class CompanyServlet extends  HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String queryurl = "/Back-End/company/company.jsp?action=query";
		String addurl = "/Back-End/company/company.jsp?action=add";
		String updateurl = "/Back-End/company/company.jsp?action=update";
		
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
		
	if("getOneCompany".equals(action)){
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try{
			/*************************** 1.接收請求參數 ****************************************/
			Integer comp_no = new Integer(req.getParameter("comp_no"));
			/*************************** 2.開始查詢資料 ****************************************/
			CompanyService CompanySvc = new CompanyService();
			CompanyVO companyVO = CompanySvc.getOneCompany(comp_no);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("companyVO", companyVO); 
			
			String url = "/company/listOneCompany.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
		
	if ("listBooks_ByCompany_A".equals(action) || "listBooks_ByCompany_B".equals(action)) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer comp_no = new Integer(req.getParameter("comp_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			CompanyService companySvc = new CompanyService();
			List<BookVO> list = companySvc.getBookByCompany(comp_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listBooks_ByCompany", list);    // 資料庫取出的set物件,存入request

			String url = null;
			if ("listBooks_ByCompany_A".equals(action))
				url = "/company/listBooks_ByCompany.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
			else if ("listBooks_ByCompany_B".equals(action))
				url = "/company/listAllCompany.jsp";              // 成功轉交 dept/listAllDept.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	
	if ("delete_Company".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			Integer comp_no = new Integer(req.getParameter("comp_no"));
			
			/***************************2.開始刪除資料***************************************/
			CompanyService companySvc = new CompanyService();
			companySvc.deleteCompany(comp_no);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			req.setAttribute("deleteCompany", "deleteCompany");
			RequestDispatcher successView = req.getRequestDispatcher(queryurl);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			req.setAttribute("deletefail", "deletefail");
			RequestDispatcher failureView = req.getRequestDispatcher(queryurl);
			failureView.forward(req, res);
		}
	}
	if ("insert_Company".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("addMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			String comp_name;
			try {
				comp_name = req.getParameter("comp_name").trim();
				if(comp_name.length()==0){
					comp_name = "";
					errorMsgs.add("出版社名稱不得為空白");
					}
			} catch (ClassCastException e) {
				comp_name = "";
				errorMsgs.add("請重新輸入");
			}
			String comp_tel;
			try {
				comp_tel = req.getParameter("comp_tel").trim();
				if(comp_tel.length()==0){
					comp_tel = "";
					errorMsgs.add("出版社電話不得為空白");
					}
			} catch (ClassCastException e) {
				comp_tel = "";
				errorMsgs.add("請重新輸入");
			}
			String comp_add;
			try {
				comp_add = req.getParameter("comp_add").trim();
				if(comp_add.length()==0){
					comp_add = "";
					errorMsgs.add("出版社地址不得為空白");
					}
			} catch (ClassCastException e) {
				comp_add = "";
				errorMsgs.add("請重新輸入");
			}
			String comp_number;
			try {
				comp_number = req.getParameter("comp_number").trim();
				if(comp_number.length()==0){
					comp_number = "";
					errorMsgs.add("出版社統編不得為空白");
					}
			} catch (ClassCastException e) {
				comp_number = "";
				errorMsgs.add("請重新輸入");
			}
			String comp_contact;
			try {
				comp_contact = req.getParameter("comp_contact").trim();
				if(comp_contact.length()==0){
					comp_contact = "";
					errorMsgs.add("聯絡人不得為空白");
					}
			} catch (ClassCastException e) {
				comp_contact = "";
				errorMsgs.add("請重新輸入");
			}
			String comp_email;
			try {
				comp_email = req.getParameter("comp_email").trim();
				if(comp_email.length()==0){
					comp_email = "";
					errorMsgs.add("聯絡人E-mail不得為空白");
					}
			} catch (ClassCastException e) {
				comp_email = "";
				errorMsgs.add("請重新輸入");
			}
			
			

			CompanyVO companyVO = new CompanyVO();
			companyVO.setComp_name(comp_name);
			companyVO.setComp_tel(comp_tel);
			companyVO.setComp_add(comp_add);
			companyVO.setComp_number(comp_number);
			companyVO.setComp_contact(comp_contact);
			companyVO.setComp_email(comp_email);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("insertcompanyVO", companyVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(addurl);
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			CompanyService companyService = new CompanyService();
			companyService.addCompany(comp_name,comp_tel,comp_add,comp_number,comp_contact,comp_email);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			req.setAttribute("addCompany", "addCompany");
			RequestDispatcher successView = req.getRequestDispatcher(queryurl);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("新增資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher(queryurl);
			failureView.forward(req, res);
		}
	}
	
	if ("getOneCompany_For_Update".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("updateMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數***************************************/
			Integer comp_no= new Integer(req.getParameter("comp_no"));

			/***************************2.開始新增資料***************************************/
			CompanyService companySvc = new CompanyService();
			CompanyVO companyVO = companySvc.getOneCompany(comp_no);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			req.setAttribute("updatecompanyVO", companyVO);
			RequestDispatcher successView = req.getRequestDispatcher(updateurl);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("新增資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher(queryurl);
			failureView.forward(req, res);
		}
	}
	
	if ("update_Company".equals(action)) { // 來自/dept/listAllDept.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("updateMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數***************************************/
			Integer comp_no = new Integer(req.getParameter("comp_no").trim());
			String comp_name;
			try {
				comp_name = req.getParameter("comp_name").trim();
				if(comp_name.length()==0){
					comp_name = "請重新輸入";
					errorMsgs.add("出版社名稱不得為空白");
					}
			} catch (ClassCastException e) {
				comp_name = "請重新輸入";
				errorMsgs.add("");
			}
			String comp_tel;
			try {
				comp_tel = req.getParameter("comp_tel").trim();
				if(comp_tel.length()==0){
					comp_tel = "請重新輸入";
					errorMsgs.add("出版社電話不得為空白");
					}
			} catch (ClassCastException e) {
				comp_tel = "請重新輸入";
				errorMsgs.add("");
			}
			String comp_add;
			try {
				comp_add = req.getParameter("comp_add").trim();
				if(comp_add.length()==0){
					comp_add = "請重新輸入";
					errorMsgs.add("出版社地址不得為空白");
					}
			} catch (ClassCastException e) {
				comp_add = "請重新輸入";
				errorMsgs.add("");
			}
			String comp_number;
			try {
				comp_number = req.getParameter("comp_number").trim();
				if(comp_number.length()==0){
					comp_number = "請重新輸入";
					errorMsgs.add("出版社統編不得為空白");
					}
			} catch (ClassCastException e) {
				comp_number = "請重新輸入";
				errorMsgs.add("");
			}
			String comp_contact;
			try {
				comp_contact = req.getParameter("comp_contact").trim();
				if(comp_contact.length()==0){
					comp_contact = "請重新輸入";
					errorMsgs.add("聯絡人不得為空白");
					}
			} catch (ClassCastException e) {
				comp_contact = "請重新輸入";
				errorMsgs.add("");
			}
			String comp_email;
			try {
				comp_email = req.getParameter("comp_email").trim();
				if(comp_email.length()==0){
					comp_email = "請重新輸入";
					errorMsgs.add("聯絡人E-mail不得為空白");
					}
			} catch (ClassCastException e) {
				comp_email = "請重新輸入";
				errorMsgs.add("");
			}
			

			CompanyVO companyVO = new CompanyVO();
			companyVO.setComp_no(comp_no);
			companyVO.setComp_name(comp_name);
			companyVO.setComp_tel(comp_tel);
			companyVO.setComp_add(comp_add);
			companyVO.setComp_number(comp_number);
			companyVO.setComp_contact(comp_contact);
			companyVO.setComp_email(comp_email);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("updatecompanyVO", companyVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(updateurl);
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始修改資料***************************************/
			CompanyService companySvc = new CompanyService();
			companySvc.updateCompany(comp_no,comp_name,comp_tel,comp_add,comp_number,comp_contact,comp_email);
			
			/***************************3.修改完成,準備轉交(Send the Success view)***********/
			req.setAttribute("updateCompany", "updateCompany");
			RequestDispatcher successView = req.getRequestDispatcher(queryurl);// 修改成功後, 成功轉交 回到 /dept/listAllDept.jsp
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理***********************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher(queryurl);
			failureView.forward(req, res);
		}
	}
//	if("getCompanyByName".equals(action)){
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		/**************************** 1.接收請求參數**************************************/
//		String comp_name = new String(req.getParameter("comp_name").trim());
//		if(comp_name.length()<2){
//			errorMsgs.add("出版社搜尋不得少於兩個字");
//			}
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("comp_name", comp_name); // 含有輸入格式錯誤的empVO物件,也存入req
//			RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
//			failureView.forward(req, res);
//			return;
//		}
//		/**************************** 2.開始查詢資料***************************************/
//		CompanyService companySvc = new CompanyService();
//		List<CompanyVO> list = companySvc.getCompanyByName(comp_name);
//		System.out.println(list.size());
//		if (list.size() == 0) {
//			errorMsgs.add("查無資料");
//		}
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("comp_name", comp_name); // 含有輸入格式錯誤的empVO物件,也存入req
//			RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
//			failureView.forward(req, res);
//			return;
//		}
//		/**************************** 3.查詢完成,準備轉交(Send the Success view)***********/
//		req.setAttribute("listCompanyByName",list);
//		String url = "/company/listCompanyByName.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
//		successView.forward(req, res);
//	}
//	
	}
	
}
