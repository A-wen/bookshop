package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cou.model.CouponVO;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import com.mail.tool.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.order_info.controller.Order_infoServlet;
import com.md5.message.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(MemServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		ServletOutputStream out = response.getOutputStream();

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8"); // 處理中文檔名
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		MD5Util md5 = new MD5Util();
		
		if (action.equals("memlogin")) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			/** 1.接收請求參數，使用者登入輸入的值，如果等同於資料庫裡面的帳號及密碼，將傳至首頁，失敗則重導致登入頁面 **/
			try {
				String mem_mail = request.getParameter("mem_mail").trim();
				String mem_psw = request.getParameter("mem_psw").trim();
				if (mem_mail == null || mem_mail.trim().length() == 0) {
					errorMsgs.add("會員帳號必填");
				}
				if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.add("會員密碼必填");
				}
				MemVO memVO = new MemVO();
				memVO.setMem_mail(mem_mail);
				memVO.setMem_psw(mem_psw);
				/** 將錯誤的VO傳回登入頁面並利用for-each顯示錯誤資訊 **/
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/login.jsp");
					failureView.forward(request, response);
					return;
				}

				MemService memservice = new MemService();

				/** 此行為會員輸入密碼的參數直接加密去比對資料庫 **/
				memVO = memservice.selectLogin(mem_mail, md5.getMD5(mem_psw.getBytes()));
				// System.out.println("會員輸入密碼:"+md5.getMD5(mem_psw.getBytes()));
				// System.out.println("比對資料庫密碼:"+memVO.getMemPsw());
				if (memVO == null) {
					errorMsgs.add("帳號或密碼有誤，請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/login.jsp");
					failureView.forward(request, response);
				}

				if (mem_mail.equals(memVO.getMem_mail()) && md5.getMD5(mem_psw.getBytes()).equals(memVO.getMem_psw())) {

					session.setAttribute("memVO", memVO);
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location"); // *工作2:
																	// 看看有無來源網頁
																	// (-->如有來源網頁:則重導至來源網頁)
							response.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}
					String url = request.getContextPath()+"/Front-End/index.jsp";
					response.sendRedirect(url);
//					String url = "/Front-End/index.jsp";
//					RequestDispatcher successView = request.getRequestDispatcher(url); // 確定資料庫有此帳號及密碼才讓他登入，傳至首頁
//					successView.forward(request, response);
				} else
					response.sendRedirect(request.getContextPath() + "/Front-End/member/login.jsp");

			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "<font color='red'>帳號密碼不能為空</font>");
				RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/login.jsp");
				failureView.forward(request, response);
			}
		}
		/** 登出 **/
		if (action.equals("logout")) {
			session.removeAttribute("memVO");
			response.sendRedirect(request.getContextPath() + "/Front-End/index.jsp");
		}
		/******** ↓↓↓↓↓↓註冊hidden按鈕↓↓↓↓↓↓ ********/
		if ("register".equals(action)) {

			// List<String> errorMsgs = new LinkedList<>();
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// String requestURL = request.getParameter("requestURL");
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
//				Integer mem_no = Integer.parseInt(request.getParameter("mem_no").trim());
				String mem_name = request.getParameter("mem_name").trim();
				String mem_nic = request.getParameter("mem_nic").trim();
				String mem_tel = request.getParameter("mem_tel").trim();
				String mem_mail = request.getParameter("mem_mail").trim();
				String mem_psw = request.getParameter("mem_psw").trim();
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6,10}$";

				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.put("<font color=red>提示訊息:</font>", "姓名必填");
				}
				if (mem_nic == null || mem_nic.trim().length() == 0) {
					errorMsgs.put("<font color=red>提示訊息!:</font>", "暱稱必填");
				}
				if (mem_tel == null || mem_tel.trim().length() == 0) {
					errorMsgs.put("<font color=red>提示訊息!!</font>", "電話號碼請輸入");
				}
				if (mem_mail == null || mem_mail.trim().length() == 0) {
					errorMsgs.put("<font color=red>提示訊息!!!</font>", "帳號必填");
				} else if (mem_psw == null || mem_psw.trim().length() == 0) {
					errorMsgs.put("<font color=red>*</font>", "密碼必填");
				}
				if (!mem_psw.trim().matches(enameReg)) {
					errorMsgs.put("<font color=red>提示訊息!!!!</font>", "密碼: 只能是中、英文字母、數字和_ , 且長度必需在6到10之間");
				}

				MemVO memVO = new MemVO();
//				memVO.setMemNo(mem_no);
				memVO.setMem_name(mem_name);
				memVO.setMem_nic(mem_nic);
				memVO.setMem_tel(mem_tel);
				memVO.setMem_mail(mem_mail);
				memVO.setMem_psw(mem_psw);
				Part part = null;
				InputStream in = null;
				byte[] mem_photo = null;
				part = request.getPart("mem_photo");
				in = part.getInputStream();
				mem_photo = new byte[in.available()];
				in.read(mem_photo);
				if (part == null || part.getSize() == 0) {
					InputStream is = getServletContext().getResourceAsStream("image/user1.png");
					mem_photo = new byte[is.available()];
					is.read(mem_photo);
//					System.err.println(mem_photo.length);
					is.close();
				}
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("memVO", memVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/register.jsp");
					failureView.forward(request, response);
					return;
				}

				/***************************
				 * 2.開始新增資料
				 ***************************************/
				MemService memSvc = new MemService();
				String safe_psw = md5.getMD5(mem_psw.getBytes());
				memVO = memSvc.addMember(mem_name, mem_nic, mem_tel, mem_mail, safe_psw, mem_photo);
				
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				session.setAttribute("memVO", memVO); //此時沒有會員編號
				String url = request.getContextPath()+"/Front-End/index.jsp";
				response.sendRedirect(url);
				return;
				/* 用forward會讓網址變成http://localhost:8080/AA103G4/member/MemServlet.do
				String url = "/Front-End/index.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後,轉交回送登入畫面。
				successView.forward(request, response);  
				*/

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("", e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/register.jsp");
				failureView.forward(request, response);
			}

		}
		/** ↓↓↓↓↓↓下中斷點結束跳出程式區塊，測試查看是否直接跳過註冊隱藏按鈕↓↓↓↓↓↓ **/

		/*** ↓↓↓↓↓↓修改會員資料按鈕↓↓↓↓↓↓ ***/
		if ("updateMember".equals(action)) {
			/*** ↑↑↑↑↑↑測試request.getParameter("action")是否進入判斷式↑↑↑↑↑↑ ***/
			Integer mem_no = Integer.parseInt(request.getParameter("mem_no").trim());
			byte[] mem_photo = null;
			MemVO memVO = null;
			Part part = request.getPart("mem_photo");
			InputStream in = part.getInputStream();
			mem_photo = new byte[in.available()];
			in.read(mem_photo);
			in.close();
			String mem_name = request.getParameter("mem_name").trim();
			String mem_tel = request.getParameter("mem_tel").trim();
			String mem_psw = request.getParameter("mem_psw").trim();
			String mem_nic = request.getParameter("mem_nic").trim();
			String mem_mail = request.getParameter("mem_mail").trim();
			MemService memSrc2 = new MemService();
			MemVO memVO2 = memSrc2.getOneMember(mem_no);
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
//				System.out.println(mem_tel);
//				System.out.println(mem_name);
				if (mem_tel == null || mem_tel.length() == 0) {
					mem_tel = memVO2.getMem_tel();
				}
				if (mem_psw == null || mem_psw.length() == 0) {
					mem_psw = memVO2.getMem_psw();
				} else {
					mem_psw = md5.getMD5(mem_psw.getBytes());
				}
				if (part == null || part.getSize() == 0) {
					mem_photo = memVO2.getMem_photo();
				}
				memVO = new MemVO();
				memVO.setMem_tel(mem_tel);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_photo(mem_photo);
				memVO.setMem_no(mem_no);
				memVO.setMem_mail(mem_mail);
				memVO.setMem_nic(mem_nic);
				memVO.setMem_name(mem_name);

				/***************************
				 * 2.開始修改資料
				 ***************************************/
				MemService memSvc = new MemService();
				memVO = memSvc.updateMember(mem_name, mem_nic, mem_mail, mem_tel, mem_psw, mem_photo, mem_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 ***********/
				session.setAttribute("memVO", memVO);
				String url = "/Front-End/index.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後,轉交回送出修改的來源網頁
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				String url = "/Front-End/member/updateMem.jsp";
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
		}

		if (action.equals("getOne_For_Mem")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				Integer mem_no = new Integer(request.getParameter("mem_no").trim());

				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMember(mem_no);

				if (memVO == null || mem_no.equals("")) {
					errorMsgs.add("輸入資料錯誤，請重新輸入");
					String url = "/Back-End/memberInfo/selectMem.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
				}
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				request.setAttribute("memVO", memVO);
				String url = "/Back-End/memberInfo/listOneMem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		/***********************
		 * @author Jin
		 * @param:mem_mail 此為忘記密碼修改
		 *************************/
		if (action.equals("ResetMemPsw")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			/** 1.接收請求參數 - 輸入格式的錯誤處理 **/
			String mem_mail = request.getParameter("mem_mail").trim();
			String mem_psw = request.getParameter("mem_psw").trim();

			if (mem_psw == null || mem_psw.trim().length() == 0) {
				errorMsgs.add("密碼必填");
			}
			MemVO memVO = new MemVO();
			memVO.setMem_psw(mem_psw);
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/updatePsw.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}
			/** 2.修改新的密碼 **/
			MemService memSvc = new MemService();
			memVO = memSvc.ResetPsw(md5.getMD5(mem_psw.getBytes()), mem_mail);

			/**** 3.修改完成,準備轉交 ******/
			session.setAttribute("memVO", memVO);
			String url = "/Front-End/member/login.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}

		/***********************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 * 
		 * @author Jin
		 * @param:mem_mail 此為發信件
		 *************************/
		if (action.equals("forgetMemberPsw")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String mem_mail = request.getParameter("mem_mail").trim();
			/***********************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 * 
			 * @param:mem_mail
			 *************************/
			if (mem_mail == null || mem_mail.trim().length() == 0) {
				errorMsgs.add("帳號一定要輸入");
			}
			MemVO memVO = new MemVO();
			memVO.setMem_mail(mem_mail);
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request.getRequestDispatcher("/Front-End/member/forgetPsw.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}
			/***************************
			 * 2.開始查詢資料
			 ****************************************/
			MemService memSvc = new MemService();
			memVO = memSvc.checkMemMailRepeat(mem_mail);
			
			//先產生一組新密碼
			String newPW = util.cy.UUIDGenerator.getUUID();
			memSvc.ResetPsw(md5.getMD5(newPW.getBytes()), mem_mail);
			logger.info("加密後新密碼:\n"+ md5.getMD5(newPW.getBytes()));
			
			//信件標題
			String subject = "修改密碼通知Email";
			
			//產生信件內文
			StringBuilder sb = new StringBuilder();
			sb.append("<html>您好:請點擊下方的連結<br>");
			sb.append("<a href=\"");
			sb.append(request.getScheme()+":/"+request.getServerName()+":"+request.getServerPort()); 
			sb.append(request.getContextPath()+"/Front-End/member/resetPW.do?action=reset&key="+newPW);
			sb.append("\" > 密碼重設連結 </a></html>" );
			logger.info("內容測試:\n"+sb.toString());
			//寄送信件
			util.cy.MailSender mailSvc = new util.cy.MailSender();
			mailSvc.sendMail(mem_mail, subject, sb.toString());

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
//			session.setAttribute("memVO", memVO);
			String url2 = "/Front-End/member/SendMail.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url2);
			successView.forward(request, response);
		}
		//從忘記密碼信件進入
		if (action.equals("reset")) {
			MemService memSvc = new MemService();
			String psw = request.getParameter("key");
			MemVO memVO = memSvc.getByPsw(md5.getMD5(psw.getBytes()));
			if(memVO!=null){
				request.setAttribute("memVO", memVO);
				RequestDispatcher successView = request.getRequestDispatcher("updatePsw.jsp");
				successView.forward(request, response);
				return;
			}else{
				response.setContentType("text/html; charset=Utf-8");
				response.getWriter().println("是假的！");
				return;
			}
		}
		/*****
		 * 
		 * @method: Ajax檢查會員註冊時，資料庫資料是否重複。
		 * @param:mem_mail
		 *
		 *****/
		if (action.equals("checkRegisteredMail")) {
			String mem_mail = request.getParameter("mem_mail").trim();
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.checkMemMailRepeat(mem_mail);
			JsonObject obj = new JsonObject();
			if (memVO == null || !request.getParameter("mem_mail").equals(memVO.getMem_mail())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "帳號不存在，可註冊");
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
			obj.addProperty("result", "true");
			obj.addProperty("message", "帳號已存在，請重新更換帳號");
//			System.out.println(obj);
			out.write(obj.toString());
			out.flush();
			out.close();
			return;
		}
		/*****
		 * 
		 * @method: Ajax檢查會員註冊時，資料庫資料是否重複。
		 * @param:mem_nic
		 * 
		 *****/
		if (action.equals("checkRegisteredNic")) {
			String mem_nic = request.getParameter("mem_nic").trim();
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.checkMemNicRepeat(mem_nic);
			JsonObject obj = new JsonObject();
			if (memVO == null || !request.getParameter("mem_nic").equals(memVO.getMem_nic())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "暱稱不存在，可註冊");
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
			obj.addProperty("result", "true");
			obj.addProperty("message", "暱稱已存在，請重新更換暱稱");
			System.out.println(obj);
//			out.write(obj.toString());
			out.flush();
			out.close();
			return;
		}
		/*****
		 * 
		 * @method: Ajax檢查會員註冊時，資料庫資料是否重複。
		 * @param:mem_tel
		 *
		 *****/
		if (action.equals("checkRegisteredPhone")) {
			String mem_tel = request.getParameter("mem_tel").trim();
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.checkMemPhoneRepeat(mem_tel);
			JsonObject obj = new JsonObject();
			if (memVO == null || !request.getParameter("mem_tel").equals(memVO.getMem_tel())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "電話不存在，可為修改號碼");
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
			obj.addProperty("result", "true");
			obj.addProperty("message", "電話已存在，請重新更換電話");
//			System.out.println(obj);
			out.write(obj.toString());
			out.flush();
			out.close();
			return;
		}
		/*****
		 * 
		 * @method: Ajax檢查會員忘記密碼時比對帳號信箱，資料庫資料是否重複。
		 * @param:mem_tel
		 *
		 *****/
		if (action.equals("checkForgetMail")) {
			String mem_mail = request.getParameter("mem_mail").trim();
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.checkMemMailRepeat(mem_mail);
			JsonObject obj = new JsonObject();
			if (memVO == null || !request.getParameter("mem_mail").equals(memVO.getMem_mail())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "帳號不存在，請重新輸入");
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
			obj.addProperty("result", "true");
			obj.addProperty("message", "帳號比對成功");
//			System.out.println(obj);
			out.write(obj.toString());
			out.flush();
			out.close();
			return;
		}

		if (action.equals("finyByMemNo")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			Integer mem_no = Integer.parseInt(request.getParameter("mem_no").trim());
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMember(mem_no);
			JsonObject obj = new JsonObject();
			if (memVO == null || !mem_no.equals(memVO.getMem_no())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "查無此人");
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			} else {
				obj.addProperty("result", true);
				obj.addProperty("memName", memVO.getMem_name());
				obj.addProperty("memNic", memVO.getMem_nic());
				obj.addProperty("memMail", memVO.getMem_mail());
				obj.addProperty("memTel", memVO.getMem_tel());
				obj.addProperty("memNo", memVO.getMem_no());
//				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}
		//會員查詢自己的消費金
		if ("listCoupons_ByMemno_A".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer mem_no = new Integer(request.getParameter("mem_no"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				Set<CouponVO> set = memSvc.getCouponsByMemno(mem_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("listCoupons_ByMemno", set);    // 資料庫取出的set物件,存入request

				String url = "/Front-End/cou/listCoupons_ByMemno.jsp";
				        

				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		//結帳時讀取會員資料用
		if (action.equals("getInfo")) {
			MemVO memVO = (MemVO)session.getAttribute("memVO");
			if (memVO==null){return;}
			Integer mem_no = memVO.getMem_no();
			MemService memSvc = new MemService();
			memVO = memSvc.getUserInfo(mem_no);
			JsonObject obj = new JsonObject();
			response.setContentType("application/json;charset=UTF-8");
			if (memVO == null || !mem_no.equals(memVO.getMem_no())) {
				obj.addProperty("result", "false");
				obj.addProperty("message", "查無此人");
			} else {
				obj.addProperty("result", true);
				obj.addProperty("memName", memVO.getMem_name());
				obj.addProperty("memTel", memVO.getMem_tel());
			}
			out.write(obj.toString());
			out.flush();
			out.close();
			return;
		}
	}
}
