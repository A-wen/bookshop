package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.competence.model.CompetenceService;
import com.competence.model.CompetenceVO;
import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.fun.model.FunService;
import com.fun.model.FunVO;
import com.google.gson.JsonObject;
import com.mail.tool.EmpMailService;
import com.mail.tool.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;

//@WebServlet (urlPatterns ={
//		"/emp/EmployeeServlet.do"})
public class EmployeeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletOutputStream out = response.getOutputStream();

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ServletContext context = getServletContext();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8"); 
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (action.equals("empLogin")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			/** 1.請求參數  **/
			try {
				String emp_acc = request.getParameter("emp_acc").trim();
				String emp_psw = request.getParameter("emp_psw").trim();
				if (emp_acc == null || emp_acc.trim().length() == 0) {
					errorMsgs.add("帳號請輸入");
				}
				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("密碼請輸入");
				}
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmpAcc(emp_acc);
				employeeVO.setEmpPsw(emp_psw);
				/** 錯誤處理 **/
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/empLogin.jsp");
					failureView.forward(request, response);
					return;
				}

				EmployeeService employeeService = new EmployeeService();
				employeeVO = employeeService.selectLogin(emp_acc, emp_psw);
				if (employeeVO == null) {
					errorMsgs.add("帳號密碼有誤");
				}
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("employeeVO", employeeVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/empLogin.jsp");
					failureView.forward(request, response);
					return;
				}
				if (emp_acc.equals(employeeVO.getEmpAcc()) && emp_psw.equals(employeeVO.getEmpPsw())) {
					session.setAttribute("employeeVO", employeeVO);
					CompetenceService competenceSvc = new CompetenceService();
					List<CompetenceVO> list = competenceSvc.getEmpCompetece(employeeVO.getEmpno());
					session.setAttribute("funlist", list);
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location");
							response.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}

					String url = "/Back-End/index.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); 
					successView.forward(request, response);
				} else
					response.sendRedirect("/empLogin.jsp");

			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "<font color='red'>登入失敗</font>");
				RequestDispatcher failureView = request.getRequestDispatcher("/empLogin.jsp");
				failureView.forward(request, response);
			}
		}
		/** 登出 **/
		if ("logout".equals(action)) {
			session.removeAttribute("employeeVO");
			response.sendRedirect(request.getContextPath()+"/empLogin.jsp");
		}

		/** 新增員工  **/
		if ("addEmpRegistered".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			// String requestURL = request.getParameter("requestURL");
			try {
				/***********************
				 * 1.接收請求參數
				 *************************/
				String emp_name = request.getParameter("emp_name").trim();
				String emp_acc = request.getParameter("emp_acc").trim();
				String emp_psw = String.valueOf(((int) Math.random() * 2000 + 1) + 2000);

				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("帳號有誤");
				}
				if (emp_acc == null || emp_acc.trim().length() == 0) {
					errorMsgs.add("密碼有誤");
				}
				String subject = "歡迎加入Book41";
				StringBuilder messageText = new StringBuilder();
				messageText.append("<html><body>歡迎加入Book41，"+emp_name+"<BR><BR><BR>");
				messageText.append("你的臨時密碼為："+ emp_psw+"<BR><BR>");
				messageText.append("請盡快由<a href=\"");
				messageText.append(request.getScheme()+"://");
				messageText.append(request.getServerName()+":"+request.getServerPort());
				messageText.append(request.getContextPath()+"/empLogin.jsp\">此連結</a>");
				messageText.append("登入後台，修改密碼！</body></html>");
				EmpMailService empMailSvc = new EmpMailService();
				empMailSvc.sendMail(emp_acc, subject, messageText.toString());
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmpName(emp_name);
				employeeVO.setEmpAcc(emp_acc);
				employeeVO.setEmpPsw(emp_psw);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("employeeVO", employeeVO); 
					RequestDispatcher failureView = request.getRequestDispatcher("/Back-End/manager/empAccount.jsp?action=add");
					failureView.forward(request, response);
					return; 
				}
				/***************************
				 * 2.新增
				 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.addEmp(emp_name, emp_acc, emp_psw);

				/***************************
				 * 3.轉交(Send the Success view)
				 ***********/
				request.setAttribute("employeeVOED", employeeVO);
				request.setAttribute("addEmployee", "XXX");
				String url = "/Back-End/manager/empAccount.jsp?action=finshed";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);

			} catch (Exception e) {
				String url = "/Back-End/manager/empAccount.jsp?action=add";
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}
		}

		if (action.equals("updateEmp")) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.請求參數
				 *************************/
				String emp_name = request.getParameter("emp_name").trim();
				String emp_psw = request.getParameter("emp_psw").trim();

				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("密碼有誤");
				}

				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmpName(emp_name);
				employeeVO.setEmpPsw(emp_psw);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("employeeVO", employeeVO); 
					RequestDispatcher failureView = request.getRequestDispatcher("/Back-End/manager/empAccount.jsp?action=update");
					failureView.forward(request, response);
					return; 
				}

				/***************************
				 * 2.修改
				 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.updateEmp(emp_name, emp_psw);

				/****************************
				 * 3.轉交(Send the Success view)
				 ***********/
				request.setAttribute("sweet", "修改完成");
				request.setAttribute("employeeVO", employeeVO);
				String url = "/Back-End/manager/empAccount.jsp?action=update";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);

			} catch (Exception e) {
				String url = "/Back-End/manager/empAccount.jsp?action=update";
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(url);
				failureView.forward(request, response);
			}

		}

		if (action.equals("getOne_For_Emp")) {

			try {
				/***************************
				 * 1.單查
				 ****************************************/
				Integer emp_no = new Integer(request.getParameter("emp_no"));

				/***************************
				 * 2.找到員工的權限
				 ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(emp_no);
				List<Integer> comlist = new CompetenceService().getEmpCompeteceFunNo(emp_no);

				/***************************
				 * 3.轉交(Send the Success view)
				 ************/
				request.setAttribute("comlist", comlist);
				request.setAttribute("employeeVO", employeeVO);
				String url = "/Back-End/manager/listOneEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if (action.equals("getOne_For_Update")) {
			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				Integer emp_no = new Integer(request.getParameter("emp_no"));

				/***************************
				 * 2.查出單一員工
				 ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(emp_no);

				/***************************
				 * 3.轉交(Send the Success view)
				 ************/
				request.setAttribute("employeeVO", employeeVO);
				String url = "/Back-End/manager/updateEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		/** 刪除 **/
		if (action.equals("delete")) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.請求參數
				 ***************************************/
				Integer emp_no = new Integer(request.getParameter("emp_no"));
				/***************************
				 * 2.刪除所有權限
				 ***************************************/
				CompetenceService comSvc = new CompetenceService();
				comSvc.deleteEmpCompetece(emp_no);
				/***************************
				 * 3.轉交(Send the Success view)
				 ************/
				request.setAttribute("deleteALL", "刪除完畢");
				String url = "/Back-End/manager/listAllEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); 
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/Back-End/manager/listAllEmp.jsp");
				failureView.forward(request, response);
			}
		}
		
		if(action.equals("deleteEmp")){
			Integer emp_no = new Integer(request.getParameter("emp_no"));
			CompetenceService comSvc = new CompetenceService();
			
			comSvc.deleteEmpCompetece(emp_no);
			new EmployeeService().deleteEmp(emp_no);
			
			
			request.setAttribute("deleteEmployee", "刪除完畢");
			String url = "/Back-End/manager/listAllEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}

		if (action.equals("後台會員管理")) {
			String url = "/Back-End/memberInfo/selectMem.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if (action.equals("單一查詢員工")) {
			String url = "/Back-End/manager/selectEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if (action.equals("單一查詢員工")) {
			String url = "/Back-End/manager/selectEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if (action.equals("帳號管理")) {
			String url = "/Back-End/manager/updateEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if (action.equals("後台使用者權限管理")) {
			String url = "/Back-End/manager/listAllEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if (action.equals("UpdateCompetence")) {
			/***************************
			 * 1.接收請求參數
			 ***************************************/
			String[] list = request.getParameterValues("competence");
			Integer emp_no = new Integer(request.getParameter("emp_no"));
			/***************************
			 * 2.先刪後新增
			 ***************************************/
			new CompetenceService().addCompetence(emp_no, list);
			/***************************
			 * 3.轉交(Send the Success view)
			 ************/
			request.setAttribute("sweet","SweetAlert");
			
			String url = "/Back-End/manager/listAllEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);

		}
		
		if (action.equals("finyByEmpNo")) {
			Integer emp_no = Integer.parseInt(request.getParameter("emp_no").trim());
			CompetenceService competenceSvc = new CompetenceService();
			List<CompetenceVO> byEmpNoList = competenceSvc.getEmpCompetece(emp_no);
			request.setAttribute("byEmpNoList", byEmpNoList);
			String url = "/Back-End/manager/SelectResult.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}

		if (action.equals("UpdateEmpPersonCompetence")) {
			/***************************
			 * 1.接收請求參數
			 ***************************************/
			String[] list = request.getParameterValues("competence");
			Integer emp_no = new Integer(request.getParameter("emp_no"));
			/***************************
			 * 2.新增權限
			 ***************************************/
			new CompetenceService().addCompetence(emp_no, list);
			/***************************
			 * 3.轉交(Send the Success view)
			 ************/
			request.setAttribute("sweet","SweetAlert");
			
			String url = "/Back-End/manager/listAllEmp.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);

		}
	}
}
// }
