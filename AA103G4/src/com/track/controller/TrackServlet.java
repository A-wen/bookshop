package com.track.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.book.model.BookService;
import com.book.model.BookVO;
import com.comm.model.CommVO;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.track.model.*;

public class TrackServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fromWhere = req.getParameter("from");
		if (fromWhere!=null && "admin".equals(fromWhere)){
			TrackService trackSvc = new TrackService();
			List<TrackVO> trackList = trackSvc.getAllTrack();
			MemService memSvc = new MemService();
			List<MemVO> memList = 	memSvc.getAll();
			BookService bookSvc = new BookService();
			List<BookVO> bookList = bookSvc.getAllBook();
			req.setAttribute("trackList", trackList);
			req.setAttribute("memList", memList);
			req.setAttribute("bookList", bookList);
			RequestDispatcher successView = req.getRequestDispatcher("listAllTrack.jsp");
			successView.forward(req, res);
			return;
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 來自select_page.jsp的請求 // 來自 dept/listAllDept.jsp的請求
		
		
		if ("getTrackByMem".equals(action)) { // 來自/dept/listAllDept.jsp的請求

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
				TrackService trackSvc = new TrackService();
				List<TrackVO> list = trackSvc.getTrackByMem(mem_no);
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
				req.setAttribute("listTrackByMem", list);
				String url = "/track/listTrackByMem.jsp";
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
		
		if ("getTrackByBook".equals(action)) { // 來自/dept/listAllDept.jsp的請求

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
				TrackService trackSvc = new TrackService();
				List<TrackVO> list = trackSvc.getTrackByBook(book_no);
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
				req.setAttribute("listTrackByBook", list);
				String url = "/track/listTrackByBook.jsp";
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
		
		if ("delete_Track".equals(action)) { // 來自/dept/listAllDept.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer book_no = new Integer(req.getParameter("book_no"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				TrackService trackSvc = new TrackService();
				TrackVO trackVO = trackSvc.getOneTrack(mem_no, book_no);
				trackSvc.deleteTrack(mem_no,book_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				if(requestURL.equals("/track/listTrackByMem.jsp")){
					List<TrackVO> list = trackSvc.getTrackByMem(trackVO.getMem_no());
					if(list.size()!=0){
						req.setAttribute("listTrackByMem", list);
					}else{
						requestURL ="/track/listAllTrack.jsp";
					}
				}
				if(requestURL.equals("/track/listTrackByBook.jsp")){
					List<TrackVO> list = trackSvc.getTrackByBook(trackVO.getBook_no());
					if(list.size()!=0){
						req.setAttribute("listTrackByBook", list);
					}else{
						requestURL ="/track/listAllTrack.jsp";
					}
				}
				if (requestURL.equals("/Front-End/member/memberTrackBook.jsp")) {
					req.setAttribute("sweet", "刪除完成");
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req,res);
					return;
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
		
		if ("insert_Track".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************  * 1.接收請求參數  ***************************************/
				Integer mem_no= new Integer(req.getParameter("mem_no").trim());
				Integer book_no= new Integer(req.getParameter("book_no").trim());
				/***************************
				 * 2.開始新增資料
				 ***************************************/
				TrackService trackSvc = new TrackService();
				TrackVO trackVO=trackSvc.getOneTrack(mem_no,book_no);
				try{
				if(trackVO.getMem_no()!=null){
					errorMsgs.add("此筆資料已存在");
				}
				}catch(NullPointerException e){
					trackSvc.addTrack(mem_no,book_no);
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Front-End/book/booklist.jsp");
					failureView.forward(req, res);
					return;
				}
				/************************** 3.刪除完成,準備轉交(Send the Success view)***********/
				req.setAttribute("addTrack", "addTrack");
				String url = "/Front-End/book/booklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,
																				// 成功轉交
																				// 回到
																				// /dept/listAllDept.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/track/listAllTrack.jsp");
				failureView.forward(req, res);
			}
		}
		/*****
		 * 
		 * @method: Ajax檢查會員註冊時，資料庫資料是否重複。
		 * @param:mem_mail
		 *
		 *****/
		if (action.equals("addTrack")) {
			System.out.println("addTrack");
			Integer mem_no = new Integer(req.getParameter("mem_no").trim());
			Integer book_no = new Integer(req.getParameter("book_no").trim());
			TrackService trackSvc = new TrackService();
			TrackVO trackVO = trackSvc.getOneTrack(mem_no,book_no);
			JsonObject obj = new JsonObject();
			if (trackVO == null) {
				trackSvc.addTrack(mem_no,book_no);
				obj.addProperty("result", "success");
				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}else{
				obj.addProperty("result", "fail");
				System.out.println(obj);
				out.write(obj.toString());
				out.flush();
				out.close();
				return;
			}
		}
	}
}

