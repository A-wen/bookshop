package com.s_gro_info.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.s_book.model.*;
import com.s_gro_dis.model.*;
import com.s_gro_cs.model.*;
import com.s_gro_info.model.*;
import com.s_gro_mem.model.*;

import jdbc.util.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
maxFileSize = 5 * 1024 * 1024, 
maxRequestSize = 5 * 5 * 1024 * 1024)

public class S_gro_infoServlet extends HttpServlet{
	
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
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				S_gro_infoVO s_gro_infoVO = s_gro_infoSvc.getOneS_gro_info(s_gro_no);
				if (s_gro_infoVO == null) {
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
				req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_info/listOneS_gro_info.jsp";
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
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				S_gro_infoVO s_gro_infoVO = s_gro_infoSvc.getOneS_gro_info(s_gro_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("s_gro_infoVO", s_gro_infoVO);         // 資料庫取出的empVO物件,存入req
				String url = "/s_gro_info/update_s_gro_info_input.jsp";
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
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());
				String s_gro_name = req.getParameter("s_gro_name").trim();
				String s_con = req.getParameter("s_con").trim();
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
				
				java.sql.Date cre_date = null;
				try {
					cre_date = java.sql.Date.valueOf(req.getParameter("cre_date").trim());
				} catch (IllegalArgumentException e) {
					cre_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String s_gro_sta = req.getParameter("s_gro_sta").trim();
				
				S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();
				s_gro_infoVO.setS_gro_no(s_gro_no);
				s_gro_infoVO.setS_gro_name(s_gro_name);
				s_gro_infoVO.setS_con(s_con);
				s_gro_infoVO.setMem_no(mem_no);
				s_gro_infoVO.setCs_no(cs_no);
				s_gro_infoVO.setCre_date(cre_date);
				s_gro_infoVO.setS_gro_sta(s_gro_sta);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_info/update_s_gro_info_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				s_gro_infoVO = s_gro_infoSvc.updateS_gro_info(s_gro_no, s_gro_name, s_con, mem_no, cs_no, 
					           cre_date, s_gro_sta);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				S_gro_csService s_gro_csSvc = new S_gro_csService();
				if(requestURL.equals("/s_gro_cs/listS_gro_infos_ByCs_no.jsp") || requestURL.equals("/s_gro_cs/listAllS_gro_cs.jsp"))
					req.setAttribute("listS_gro_infos_ByCs_no",s_gro_csSvc.getS_gro_infosByCs_no(cs_no)); // 資料庫取出的list物件,存入request

				req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				if(requestURL.equals("/s_gro_info/listS_gro_infos_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<S_gro_infoVO> list  = s_gro_infoSvc.getAll(map);
					req.setAttribute("listS_gro_infos_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
//				System.out.println("url="+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_info/update_s_gro_info_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			List<String> newClubMsgs = new LinkedList<String>();
			String messageText ="您已成功新增讀書會";
			
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("newClubMsgs", newClubMsgs);

//			String requestURL = req.getParameter("requestURL");
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String s_gro_name = req.getParameter("s_gro_name").trim();
				String s_con = req.getParameter("s_con").trim();
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
				
				java.sql.Date cre_date = null;
				try {
					cre_date = java.sql.Date.valueOf(req.getParameter("cre_date").trim());
//					System.out.println(cre_date);
					
					//cre_date = java.sql.Date.valueOf(req.getParameter("cre_date").trim());
				} catch (IllegalArgumentException e) {
					cre_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String s_gro_sta = req.getParameter("s_gro_sta").trim();
				
				S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();
				s_gro_infoVO.setS_gro_name(s_gro_name);
				s_gro_infoVO.setS_con(s_con);
				s_gro_infoVO.setMem_no(mem_no);
				s_gro_infoVO.setCs_no(cs_no);
				s_gro_infoVO.setCre_date(cre_date);
				s_gro_infoVO.setS_gro_sta(s_gro_sta);				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/s_gro_info/addS_gro_info.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				s_gro_infoVO = s_gro_infoSvc.addS_gro_info(s_gro_name, s_con, mem_no, cs_no, 
						cre_date, s_gro_sta);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Front-End/s_gro/HomePageforClub.jsp";
				
				newClubMsgs.add(messageText);
//				System.out.println("newClubMsgs="+newClubMsgs);
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/s_gro_info/addS_gro_info.jsp");
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
				Integer s_gro_no = new Integer(req.getParameter("s_gro_no"));
				
				/***************************2.開始刪除資料***************************************/
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				S_gro_infoVO s_gro_infoVO = s_gro_infoSvc.getOneS_gro_info(s_gro_no);
				s_gro_infoSvc.deleteS_gro_info(s_gro_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/											
				S_gro_csService s_gro_csSvc = new S_gro_csService();
				if(requestURL.equals("/s_gro_cs/listS_gro_infos_ByCs_no.jsp") || requestURL.equals("/s_gro_cs/listAllS_gro_cs.jsp"))
					req.setAttribute("listS_gro_infos_ByCs_no",s_gro_csSvc.getS_gro_infosByCs_no(s_gro_infoVO.getCs_no())); // 資料庫取出的list物件,存入request
				
				
				if(requestURL.equals("/s_gro_info/listS_gro_infos_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<S_gro_infoVO> list  = s_gro_infoSvc.getAll(map);
					req.setAttribute("listS_gro_infos_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				if(requestURL.equals("/s_gro_info/listOneS_gro_info.jsp")){
					url = "/s_gro_info/listAllS_gro_info.jsp";
				}
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
		
		if ("listS_gro_infos_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				List<S_gro_infoVO> list  = s_gro_infoSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listS_gro_infos_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/s_gro/listS_gro_infos_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("gro_manage".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String url = "/Front-End/s_gro/HomePage_ClubManage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("gro".equals(action)) { // 來自addEmp.jsp的請求  
			int x=0;
			int sta=0;
 			List<String> errorMsgs = new LinkedList<String>();
 			Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());
 			String s_gro_sta = req.getParameter("s_gro_sta").trim();
// 			System.out.println("s_gro_sta:"+s_gro_sta);
 			
 			Integer id=  (Integer)xxx.getAttribute("idd");//get mem_no
 			/*--------------------------------------------*/
// 			Integer id= 105;
 			if ("正常".equals(s_gro_sta)){
 				sta=1;
 			}//set club status
 			
 			/*--------------------------------------------*/
				xxx.setAttribute("gro_id", s_gro_no);//saving s_gro_info_no
//				System.out.println("gro_id:"+s_gro_no);
				
				S_gro_memService ss = new S_gro_memService();
				List<S_gro_memVO> abc = ss.findMemJoin(id);
				//System.out.println("s_gro_no:"+s_gro_no);
//				System.out.println("member_id="+id);
				
				S_gro_infoService s_gro_infoService = new S_gro_infoService();
				S_gro_infoVO gro_ctr = s_gro_infoService.getByNo(s_gro_no);
				Integer xxid = gro_ctr.getMem_no();//get club leader_mem_no
				/*--------------------------------------------*/
//				System.out.println("leader_id="+xxid);
				/*--------------------------------------------*/
				 if(xxid -  id ==0){
					 x=1;//owner study group(club leader)
				 }
				 
//				for(int i =0;i<abc.size();i++){
//					System.out.println("abc:"+abc.get(i).getS_gro_no());
//					
//					 if(s_gro_no - abc.get(i).getS_gro_no() ==0 ){
//						 x=1;
//						 System.out.println(x);//member --> club member
//					 }		 
//				}
				
				xxx.setAttribute("club_sta", x);
//				System.out.println("club_sta:"+x);
				
				xxx.setAttribute("status", sta);				
//	 			System.out.println("status:"+sta);
				if(x==1){
		 			try {
		 				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		 				String url = "/Front-End/s_gro/leader_club.jsp";
		 				RequestDispatcher successView = req.getRequestDispatcher(url); 
		 				successView.forward(req, res);				
		 				
		 				/***************************其他可能的錯誤處理**********************************/
		 			} catch (Exception e) {
		 				errorMsgs.add(e.getMessage());
		 				RequestDispatcher failureView = req
		 						.getRequestDispatcher( "/Front-End/HomePageforClub.jsp");
		 				failureView.forward(req, res);
		 			}
				}else{
					try {
     				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
     				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/s_gro/member_club.jsp"); 
     				successView.forward(req, res);				
     				
     				/***************************其他可能的錯誤處理**********************************/
     			} catch (Exception e) {
     				errorMsgs.add(e.getMessage());
     				RequestDispatcher failureView = req
     						.getRequestDispatcher("/Front-End/HomePage4ClubYet.jsp");
     				failureView.forward(req, res);
     			}
			}	
 		}
		
//		-------------------------------------------------------------------------------------------------------------------


if ("updateclub".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();					
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			try {
				
				req.setCharacterEncoding("UTF-8");
				String s_gro_name = new String(req.getParameter("s_gro_name").trim());
//				System.out.println(s_gro_name);				
				 
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());				
				String s_con = req.getParameter("s_con").trim();
				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
				String s_gro_sta = req.getParameter("s_gro_sta").trim();							 
				
				S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();
				s_gro_infoVO.setS_gro_no(s_gro_no);
				s_gro_infoVO.setS_gro_name(s_gro_name);
				s_gro_infoVO.setS_con(s_con);
				s_gro_infoVO.setMem_no(mem_no);
				s_gro_infoVO.setCs_no(cs_no);
				s_gro_infoVO.setS_gro_sta(s_gro_sta);
				
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				
				s_gro_infoVO = s_gro_infoSvc.updateGroup(s_gro_no, s_gro_name, s_con, mem_no, cs_no, s_gro_sta);
				
				
//				String url = "/Front-End/s_gro/HomePageforClub.jsp";
				String url = requestURL;
//				System.out.print("url:"+url);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/Front-Fnd/HomePageforClub.jsp");
				failureView.forward(req, res);
			}
		}

//-------------------------------------------------------------------------------------------------------------------
		if ("closedclub".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();			
			
			List<String> closedClubMsgs = new LinkedList<String>();
			String messageText ="您已成功關閉讀書會";
			
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("closedClubMsgs", closedClubMsgs);
			
//			String requestURL = req.getParameter("requestURL");
			try {
				
				req.setCharacterEncoding("UTF-8");
				String s_gro_name = new String(req.getParameter("s_gro_name").trim());
//				System.out.println(s_gro_name);				
				 
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer s_gro_no= new Integer(req.getParameter("s_gro_no").trim());				
				String s_con = req.getParameter("s_con").trim();
				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
				String s_gro_sta = req.getParameter("s_gro_sta").trim();							 
				
				S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();
				s_gro_infoVO.setS_gro_no(s_gro_no);
				s_gro_infoVO.setS_gro_name(s_gro_name);
				s_gro_infoVO.setS_con(s_con);
				s_gro_infoVO.setMem_no(mem_no);
				s_gro_infoVO.setCs_no(cs_no);
				s_gro_infoVO.setS_gro_sta(s_gro_sta);
				
				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
				
				s_gro_infoVO = s_gro_infoSvc.updateGroup(s_gro_no, s_gro_name, s_con, mem_no, cs_no, s_gro_sta);
				
				closedClubMsgs.add(messageText);
//				System.out.println("closedClubMsgs="+closedClubMsgs);
				
				String url = "/Front-End/s_gro/HomePageforClub.jsp";
//				String url = requestURL;
//				System.out.print("url:"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/Front-Fnd/HomePageforClub.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("insertclub".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL");
//			
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				
//				req.setCharacterEncoding("UTF-8");
//				String s_gro_name = req.getParameter("s_gro_name").trim();
//				String s_con = req.getParameter("s_con").trim();				
//				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
//				Integer cs_no = new Integer(req.getParameter("cs_no").trim());
//				
//				java.sql.Date cre_date = null;																				
//				String s_gro_sta = req.getParameter("s_gro_sta").trim();
//								
//				S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();
//				s_gro_infoVO.setS_gro_name(s_gro_name);
//				s_gro_infoVO.setS_con(s_con);
//				s_gro_infoVO.setMem_no(mem_no);
//				s_gro_infoVO.setCs_no(cs_no);
//				s_gro_infoVO.setCre_date(cre_date);
//				s_gro_infoVO.setS_gro_sta(s_gro_sta);				
//			
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher(requestURL);
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				System.out.println("1111111111");
//				S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
//				s_gro_infoVO = s_gro_infoSvc.addS_gro_info(s_gro_name, s_con, mem_no, cs_no, 
//						cre_date, s_gro_sta);
//								
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}
		
		if ("club".equals(action)) { // 來自addEmp.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String url = "/Front-End/s_gro/listAllS_gro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher( "/select_page.jsp");
				failureView.forward(req, res);
			}
		}
				if("club_report".equals(action))	{
					{

						List<String> errorMsgs3 = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs3", errorMsgs3);
						
						List<String> club_report1 = new LinkedList<String>();
//						System.out.println("club_report1="+club_report1);
						
						String requestURL = req.getParameter("requestURL");

						try {
							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							
							Integer s_gro_no = new Integer(req.getParameter("s_gro_no").trim());
							String s_gro_name = req.getParameter("s_gro_name").trim();				
							Integer mem_no = new Integer(req.getParameter("mem_no").trim());
							Integer cs_no = new Integer(req.getParameter("cs_no").trim());
							
							if(!errorMsgs3.isEmpty()){
								RequestDispatcher failureView = req
										.getRequestDispatcher("/Front-End/HomePage_ClubManage.jsp");
								failureView.forward(req, res);
								return;
							}
							
							String Back_end_mail = "a15721c@gmail.com";
							
							/****************************搜尋後，發送電子郵件*********************************************/			
							
							MailService mailscv = new MailService();
							
							String subject = "讀書會成員檢舉讀書會通知";
						      
						    String messageText = "被檢舉讀書會編號: " + s_gro_no + "\n讀書會名稱:" + s_gro_name + 
						    		"\n發起人會員ID:" + mem_no + "\n讀書會類型:" + cs_no;
							
							mailscv.sendMail(Back_end_mail, subject, messageText);						
//							System.out.println(Back_end_mail);
							
							club_report1.add(messageText);
//							System.out.println("club_report1="+club_report1);
							req.setAttribute("club_report1", club_report1);
							
//							req.setAttribute("s_gro_infoVO", s_gro_infoVO); // 資料庫取出的empVO物件,存入req
//							String url = "/Front-End/HomePage_ClubManage.jsp";
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
				
				if ("find_club".equals(action)) { // 來自addEmp.jsp的請求  
					int sta=0;

					List<String> errorMsgs = new LinkedList<String>();			
					Integer mem_no= new Integer(req.getParameter("mem_no").trim());
//					String s_gro_sta = req.getParameter("s_gro_sta").trim();
//		 			System.out.println("s_gro_sta:"+s_gro_sta);
		 			
//		 			if(list7.isEmpty()){
//		 				clubmem = 0;
//		 			}else{
//		 				clubmem = 1;
//		 			}
		 			
//		 			if ("正常".equals(s_gro_sta)){
//		 				sta=1;
//		 			}//set club status
//		 			xxx.setAttribute("status", sta);				
//		 			System.out.println("status:"+sta);														
					try {
						/***************************2.開始查詢資料****************************************/

						S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
						List<S_gro_infoVO> list  = s_gro_infoSvc.getNoJoinClub(mem_no);
						
						/***********************3.查詢完成,準備轉交(Send the Success view)*************************/
						req.setAttribute("find_club", list);
//						System.out.println("list="+list.size());
						String url = "/Front-End/s_gro/FindListClub.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);			
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher( "/select_page.jsp");
						failureView.forward(req, res);
					}
				}
				
				if ("listS_gro_mem_ByS_gro_no_A".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ****************************************/
						Integer s_gro_no = new Integer(req.getParameter("s_gro_no"));

						/*************************** 2.開始查詢資料 ****************************************/
						S_gro_infoService s_gro_infoSvc = new S_gro_infoService();
						Set<S_gro_memVO> set = s_gro_infoSvc.getS_gro_memByS_gro_no(s_gro_no);

						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("listS_gro_mem_ByS_gro_no", set);    // 資料庫取出的set物件,存入request
						
						String url = "/Front-End/s_gro/listS_gro_mem_ByS_gro_no.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp						

						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 ***********************************/
					} catch (Exception e) {
						throw new ServletException(e);
					}
				}				
	}
}
