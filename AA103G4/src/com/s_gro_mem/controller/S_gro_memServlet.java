package com.s_gro_mem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_gro_au.model.S_gro_auService;
import com.s_gro_au.model.S_gro_auVO;
import com.s_gro_info.model.S_gro_infoService;
import com.s_gro_info.model.S_gro_infoVO;
import com.s_gro_mem.model.*;

import jdbc.util.MailService;
import util.cy.MailSender;

public class S_gro_memServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession xxx = req.getSession();
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("s_gro_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入讀書會編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer s_gro_no = null;
				try {
					s_gro_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("讀書會編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				S_gro_memService s_gro_memSvc = new S_gro_memService();
				S_gro_memVO s_gro_memVO = s_gro_memSvc.getOneS_gro_mem(s_gro_no);
				if (s_gro_memVO == null) {
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
				req.setAttribute("s_gro_memVO", s_gro_memVO); // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_mem/listOneS_gro_mem.jsp";
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
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no"));
				
				/***************************2.開始查詢資料****************************************/
				S_gro_memService s_gro_memSvc = new S_gro_memService();
				S_gro_memVO s_gro_memVO = s_gro_memSvc.getOneS_gro_mem(s_gro_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("s_gro_memVO", s_gro_memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_mem/update_s_gro_mem_input.jsp";
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
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
//			
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());
//				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
//				Integer au_no = new Integer(req.getParameter("au_no").trim());
//				
//				S_gro_memVO s_gro_memVO = new S_gro_memVO();
//				s_gro_memVO.setS_gro_no(s_gro_no);
//				s_gro_memVO.setMem_no(mem_no);
//				s_gro_memVO.setAu_no(au_no);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("s_gro_infoVO", s_gro_memVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/s_gro_mem/update_s_gro_mem_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				S_gro_memService s_gro_memSvc = new S_gro_memService();
//				s_gro_memVO = s_gro_memSvc.updateS_gro_mem(s_gro_no, mem_no, au_no);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("s_gro_memVO", s_gro_memVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				
//				String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/s_gro_mem/update_s_gro_mem_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
//		if ("delete_gro".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer s_gro_no = new Integer(req.getParameter("s_gro_no"));
//				Integer mem_no = new Integer(req.getParameter("mem_no"));
////				System.out.println("111111111111111");
//				/***************************2.開始刪除資料***************************************/
//				S_gro_memService s_gro_memSvc = new S_gro_memService();
////				S_gro_memVO s_gro_memVO = s_gro_memSvc.getOneS_gro_mem(s_gro_no);
//				s_gro_memSvc.delete(s_gro_no, mem_no);
////				System.out.println("22222222222222");
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/												
//				String url = requestURL;
////				if(requestURL.equals("/s_gro_mem/listOneS_gro_mem.jsp")){
////					url = "/s_gro_mem/listAllS_gro_mem.jsp";
////				}
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
		
//		-------------------------------------------------------------------------------------------------------------------

		if ("ask_fr_in".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
//			String requestURL = req.getParameter("requestURL"); 
			
			try {
								
				Integer mem_no= new Integer(req.getParameter("mem_no").trim());
				Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());
				String s_gro_sta = req.getParameter("s_gro_sta").trim();
//				System.out.println("mem_no"+mem_no);
//				System.out.println("s_gro_no"+s_gro_no);
//				System.out.println("11111111");
				
				if(!("正常".equals(s_gro_sta))){				
					errorMsgs.add("讀書會已被關閉或凍結，請選擇其它讀書會");				
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front-End/s_gro/HomePageforClub.jsp");
					failureView.forward(req, res);
					return;
				}
				
				S_gro_memService s_gro_memService = new S_gro_memService();
				s_gro_memService.addS_gro_mem(s_gro_no, mem_no);
				
				String url = "/Front-End/s_gro/HomePageforClub.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Front-End/s_gro/HomePageforClub.jsp");
				failureView.forward(req, res);
			}
		}
		
//		-------------------------------------------------------------------------------------------------------------------
		if ("club_no".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			
			List<String> clubMsgs = new LinkedList<String>();
			req.setAttribute("clubMsgs", clubMsgs);
			
			String messageText ="您已退出讀書會";
			
			try {
				Integer mem_no= new Integer(req.getParameter("mem_no").trim());
				Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());
				String requestURL = req.getParameter("requestURL"); 
				S_gro_memService s_gro_memService = new S_gro_memService();
				s_gro_memService.delete(s_gro_no, mem_no);
				
				clubMsgs.add(messageText);
//				System.out.println("clubMsgs="+clubMsgs);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
//				System.out.println("成功no");
												
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/Front-End/HomePageforClub.jsp");
				failureView.forward(req, res);
			}
		}
		
// 		-------------------------------------------------------------------------------------------------------------------

			if ("club_ok".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			
			
			try {
				Integer mem_no= new Integer(req.getParameter("mem_no").trim());
				Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());
				Integer au_no= new Integer(req.getParameter("au_no").trim());
//				 System.out.println(mem_no);
//				 System.out.println(s_gro_no);
//				 System.out.println(au_no);
				String requestURL = req.getParameter("requestURL"); 
				
				S_gro_memService s_gro_memService = new S_gro_memService();
			
				s_gro_memService.updateS_gro_mem(s_gro_no, mem_no, au_no);
//				 System.out.println("3333333333");
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
//				System.out.println("成功ok");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/Front-End/HomePageforClub.jsp");
				failureView.forward(req, res);
			}
		}
			
//			-------------------------------------------------------------------------------------------------------------------

			if ("club_mem".equals(action)) { // 來自addEmp.jsp的請求  
				
					List<String> errorMsgs = new LinkedList<String>();
				

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						S_gro_infoService s_gro_infoSvc = new S_gro_infoService(); 
						List<S_gro_infoVO> groList =s_gro_infoSvc.getAll();
						req.setAttribute("groList", groList);
						S_gro_auService s_gro_auSvc = new S_gro_auService();
						List<S_gro_auVO> auList = s_gro_auSvc.getAll();
						req.setAttribute("auList", auList);
						String url = "/Front-End/s_gro/listAllS_gro_mem.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);				
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher( "/Front-End/HomePage.jsp");
						failureView.forward(req, res);
					}
				}
			
			if("mem_report".equals(action))	{
				{

					List<String> errorMsgs3 = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs3", errorMsgs3);
					
					List<String> mem_report1 = new LinkedList<String>();
//					System.out.println("mem_report1="+mem_report1);
					
					String requestURL = req.getParameter("requestURL");

					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						
						Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());			
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						if(!errorMsgs3.isEmpty()){
							RequestDispatcher failureView = req
									.getRequestDispatcher("/Front-End/HomePage_ClubManage.jsp");
							failureView.forward(req, res);
							return;
						}
						String Back_end_mail = "java.aa103@gmail.com";
//						String Back_end_mail = "a15721c@gmail.com";
						
						/****************************發送電子郵件*********************************************/			
						
						MailService mailscv = new MailService();
						MailSender mailSender = new MailSender();
						String subject = "讀書會成員檢舉讀書會成員通知";    
					    String messageText = "被檢舉讀書會成員編號: " + mem_no + "\n所屬讀書會編號:" + s_gro_no;
					    mailSender.sendMail(Back_end_mail, subject, messageText);
//						mailscv.sendMail(Back_end_mail, subject, messageText);						
//						System.out.println(Back_end_mail);
						
						mem_report1.add(messageText);
//						System.out.println("mem_report1="+mem_report1);
						req.setAttribute("mem_report1", mem_report1);
						
//						req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 資料庫取出的empVO物件,存入req
//						String url = "/Front-End/HomePage_ClubManage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 成功轉交login.jsp
						successView.forward(req, res);
						/***************************其他可能的錯誤處理**********************************/
					}
					catch (Exception e) {
						errorMsgs3.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Front-End/HomePage_ClubManage.jsp");
						failureView.forward(req, res);
					}		
				}
			}			
	}
}
