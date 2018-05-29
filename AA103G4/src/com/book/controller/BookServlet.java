package com.book.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.booktype.model.BooktypeService;
import com.company.model.CompanyService;
import com.google.gson.JsonObject;
import com.book.model.*;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		if(req.getParameter("action")!=null){
			doPost(req,res);
		}else{
		
		Integer book_no = null;
		String no = req.getPathInfo().substring(1);
		
//		String no = null;
//		try{
//			no = req.getPathInfo().substring(1);
//		}catch(NullPointerException e){
//			doPost(req,res);
//		}
		if( no != null){
			if ((no.length() == 0)) {
				RequestDispatcher errorView = req.getRequestDispatcher("/ErrorPage/404err.jsp");
				errorView.forward(req, res);
				return;
			}
			try {
				book_no = Integer.parseInt(no);
				BookService bookServ = new BookService();
				BookVO bookVO = bookServ.getOneBook(book_no);
				if (bookVO == null) {
					throw new NullPointerException();
				}
				req.setAttribute("bookVO", bookVO);
				RequestDispatcher successView = req.getRequestDispatcher("/Front-End/book/bookpage.jsp");
				successView.forward(req, res);
			} catch (NumberFormatException | NullPointerException e) {
				RequestDispatcher errorView = req.getRequestDispatcher("/ErrorPage/404err.jsp");
				errorView.forward(req, res);
				return;
			}
		}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneBook".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("book_no");
				if (str == null || (str.trim()).length() != 4) {
					errorMsgs.add("書籍編號需為4碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("book_no", str);
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer book_no = null;
				try {
					book_no = new Integer(str);
				} catch (NumberFormatException e) {
					errorMsgs.add("書籍編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("book_no", book_no); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/**************************** 2.開始查詢資料 *****************************************/
				BookService bookSvc = new BookService();
				BookVO bookVO = bookSvc.getOneBook(book_no);
				if (bookVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/**************************** 3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("bookVO", bookVO); // 資料庫取出的empVO物件,存入req
				String url = "/book/listOneBook.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/**************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneBook_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer book_no = new Integer(req.getParameter("book_no"));

				/**************************** 2.開始查詢資料 ****************************************/
				BookService bookSvc = new BookService();
				BookVO bookVO = bookSvc.getOneBook(book_no);

				/**************************** 3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("updateBookVO", bookVO); // 資料庫取出的bookVO物件,存入req
				String url = "/Back-End/book/book.jsp?action=update";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}

		if ("update_Book".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("updateMsgs", errorMsgs);

			try {
				/**************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer book_no = new Integer(req.getParameter("book_no").trim());
				String book_name = new String(req.getParameter("book_name").trim());
				if (book_name.length() == 0) {
					errorMsgs.add("書名不得為空白");
				}
				Integer book_price =null;
				try {
					book_price = new Integer(req.getParameter("book_price").trim());
				} catch (NumberFormatException e) {
					book_price = 9999;
					errorMsgs.add("價格格式錯誤");
				}
				Integer type_no = new Integer(req.getParameter("type_no").trim());

				Integer comp_no = new Integer(req.getParameter("comp_no").trim());

				Integer book_qty = null;
				try {
					book_qty = new Integer(req.getParameter("book_qty").trim());
					if (book_qty <= 0) {
						book_qty = 9999;
						errorMsgs.add("庫存量不得為零或負數");
					}
				} catch (NumberFormatException e) {
					book_qty = 9999;
					errorMsgs.add("庫存量格式錯誤");
				}
				String isbn;
				try {
					isbn = new String(req.getParameter("isbn").trim());
					if (isbn.length() != 13) {
						errorMsgs.add("ISBN須為13碼");
					}
				} catch (ClassCastException e) {
					isbn = "";
					errorMsgs.add("ISBN格式錯誤");
				}
				String book_author;
				try {
					book_author = new String(req.getParameter("book_author").trim());
					if (book_author.length() == 0) {
						errorMsgs.add("作者不得為空白");
					}
				} catch (ClassCastException e) {
					book_author = "";
					errorMsgs.add("");
				}
				byte[] book_pic;
				Part part = req.getPart("book_pic");
				InputStream fin = part.getInputStream();
				book_pic = new byte[fin.available()];

				fin.read(book_pic);
				fin.close();
				String book_desc = new String(req.getParameter("book_desc").trim());

				Integer saleable;
				try {
					saleable = new Integer(req.getParameter("saleable").trim());
					if (saleable != 0 && saleable != 1) {
						errorMsgs.add("銷售狀況不得為0或1之外的值");
					}
				} catch (ClassCastException e) {
					saleable = 1;
					errorMsgs.add("銷售狀況格式錯誤");
				}

				BookVO bookVO = new BookVO();
				bookVO.setBook_no(book_no);
				bookVO.setBook_name(book_name);
				bookVO.setBook_price(book_price);
				bookVO.setType_no(type_no);
				bookVO.setComp_no(comp_no);
				bookVO.setBook_qty(book_qty);
				bookVO.setIsbn(isbn);
				bookVO.setBook_author(book_author);
				bookVO.setBook_pic(book_pic);
				bookVO.setBook_desc(book_desc);
				bookVO.setSaleable(saleable);
				// ---------------------------------------------------------------------------------------------------
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("updateBookVO", bookVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/book/book.jsp?action=update");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/**************************** 2.開始修改資料 *****************************************/
				BookService bookSvc = new BookService();
				if (part.getSize() > 0) {
					bookVO = bookSvc.updateBook(book_no, book_name, book_price, type_no, comp_no, book_qty, isbn,
							book_author, book_pic, book_desc, saleable);
				} else {
					bookVO = bookSvc.updateBookNoPic(book_no, book_name, book_price, type_no, comp_no, book_qty, isbn,
							book_author, book_desc, saleable);
				}

				/**************************** 3.修改完成,準備轉交(Send the Success view) *************/
				BooktypeService booktypeSvc = new BooktypeService();
//				if (requestURL.equals("/booktype/listBooks_ByBooktype.jsp")
//						|| requestURL.equals("/booktype/listAllBooktype.jsp"))
//					req.setAttribute("listBooks_ByBooktype", booktypeSvc.getBookByBooktype(type_no));
//				// 資料庫取出的list物件,存入request
//				CompanyService companySvc = new CompanyService();
//				if (requestURL.equals("/company/listBooks_ByCompany.jsp")
//						|| requestURL.equals("/company/listAllCompany.jsp"))
//					req.setAttribute("listBooks_ByCompany", companySvc.getBookByCompany(comp_no));
//				// 資料庫取出的list物件,存入request
//				if (requestURL.equals("/book/listBookByName.jsp")) {
//					requestURL = "/book/listAllBook.jsp";
//				}
				req.setAttribute("passBookVO", bookVO);
				RequestDispatcher successView = req.getRequestDispatcher("/Back-End/book/passpage.jsp?pass=updateBook"); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/book/update_book_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert_Book".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("addMsgs", errorMsgs);

			try {
				/*********************** * 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String book_name = new String(req.getParameter("book_name").trim());
				if (book_name.length() == 0) {
					errorMsgs.add("書名不得為空白");
				}
				Integer book_price;
				try {
					book_price = new Integer(req.getParameter("book_price").trim());
				} catch (NumberFormatException e) {
					book_price = 0;
					errorMsgs.add("價格格式錯誤");
				}
				
				Integer type_no;
				try {
					type_no = new Integer(req.getParameter("type_no").trim());
				} catch (NumberFormatException e) {
					type_no = null;
					errorMsgs.add("請選擇類別");
				}
				Integer comp_no;
				try {
					comp_no = new Integer(req.getParameter("comp_no").trim());
				} catch (NumberFormatException e) {
					comp_no = null;
					errorMsgs.add("請選擇出版社");
				}
				
				Integer book_qty;
				try {
					book_qty = new Integer(req.getParameter("book_qty").trim());
					if (book_qty <= 0) {
						errorMsgs.add("庫存量不得為零或負數");
					}
				} catch (NumberFormatException e) {
					book_qty = 0;
					errorMsgs.add("庫存量格式錯誤");
				}
				String isbn;
				try {
					isbn = new String(req.getParameter("isbn").trim());
					if (isbn.length() != 13) {
						errorMsgs.add("ISBN須為13碼");
					}
				} catch (ClassCastException e) {
					isbn = "";
					errorMsgs.add("ISBN格式錯誤");
				}
				String book_author;
				try {
					book_author = new String(req.getParameter("book_author").trim());
					if (book_author.length() == 0) {
						errorMsgs.add("作者不得為空白");
					}
				} catch (ClassCastException e) {
					book_author = "";
					errorMsgs.add("");
				}

				InputStream fin = null;
				Part part = req.getPart("book_pic");
				if (part.getSize() != 0) {
					fin = part.getInputStream();
				}
				// else if (part == null || part.getSize() == 0) {
				// File pic = new
				// File(req.getServletContext().getRealPath("/book/images") +
				// "/back1.gif");
				// fin = new FileInputStream(pic);
				// }
				else if (part == null || part.getSize() == 0) {
					fin = req.getServletContext().getResourceAsStream("/Back-End/book/images/tomcat.gif");
				}
				byte[] book_pic = new byte[fin.available()];
				fin.read(book_pic);
				fin.close();

				String book_desc = new String(req.getParameter("book_desc").trim());

				BookVO bookVO = new BookVO();
				bookVO.setBook_name(book_name);
				bookVO.setBook_price(book_price);
				bookVO.setType_no(type_no);
				bookVO.setComp_no(comp_no);
				bookVO.setBook_qty(book_qty);
				bookVO.setIsbn(isbn);
				bookVO.setBook_author(book_author);
				bookVO.setBook_pic(book_pic);
				bookVO.setBook_desc(book_desc);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("insertbookVO", bookVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/book/book.jsp?action=add");
					failureView.forward(req, res);
					return;
				}

				/**************************** 2.開始新增資料 ***************************************/
				req.setAttribute("passBookVO", bookVO);
				BookService bookSvc = new BookService();
				bookVO = bookSvc.addBook(book_name, book_price, type_no, comp_no, book_qty, isbn, book_author, book_pic,
						book_desc);
				/**************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				RequestDispatcher successView = req.getRequestDispatcher("/Back-End/book/passpage.jsp?pass=addBook");  // 新增成功後轉交listAllBooks.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/book/book.jsp?action=query");
				failureView.forward(req, res);
			}
		}
		if ("delete_Book".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************** 1.接收請求參數**************************************/
				Integer book_no = new Integer(req.getParameter("book_no"));

				/***************************** 2.開始刪除資料 ***************************************/
				BookService bookSvc = new BookService();
				BookVO bookVO = bookSvc.getOneBook(book_no);
//				req.setAttribute("passBookVO",bookVO);
				bookSvc.deleteBook(book_no);

				/***************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				BooktypeService booktypeSvc = new BooktypeService();
//				if (requestURL.equals("/booktype/listBooks_ByBooktype.jsp")
//						|| requestURL.equals("/booktype/listAllBooktype.jsp"))
//					req.setAttribute("listBooks_ByBooktype", booktypeSvc.getBookByBooktype(bookVO.getType_no())); // 資料庫取出的list物件,存入request
//				CompanyService companySvc = new CompanyService();
//				if (requestURL.equals("/company/listBooks_ByCompany.jsp")
//						|| requestURL.equals("/company/listAllCompany.jsp"))
//					req.setAttribute("listBooks_ByCompany", companySvc.getBookByCompany(bookVO.getComp_no()));
//				if (requestURL.equals("/book/listBookByName.jsp")) {
//					requestURL = "/book/listAllBook.jsp";
//				}
//				req.setAttribute("requestURL", requestURL);
				RequestDispatcher successView = req.getRequestDispatcher("/Back-End/book/passpage.jsp?pass=deleteBook"); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back-End/book/passpage.jsp?pass=deletefail");
				failureView.forward(req, res);
			}
		}
		if ("getBookByName".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************** 1.接收請求參數**************************************/
			String book_name = new String(req.getParameter("book_name").trim());
			if (book_name.length() < 2) {
				errorMsgs.add("書名搜尋不得少於兩個字");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("book_name", book_name); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************** 2.開始查詢資料 ***************************************/
			BookService bookSvc = new BookService();
			List<BookVO> list = bookSvc.getBookByName(book_name);
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("book_name", book_name); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************** 3.查詢完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("listBookByname", list);
			String url = "/book/listBookByName.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 查詢成功後,轉交回送出查詢的來源網頁
			successView.forward(req, res);
		}
		

		if ("listBooks_ByCompositeQuery".equals(action)) { // 來自book.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("queryMsgs", errorMsgs);
						
			if(req.getParameter("pass")!=null){
			String pass = new String(req.getParameter("pass").trim());
			
			if ("addBook".equals(pass)) {req.setAttribute("addBook", "addBook");}
			if ("updateBook".equals(pass)) {req.setAttribute("updateBook", "updateBook");}
			if ("deleteBook".equals(pass)) {req.setAttribute("deleteBook", "deleteBook");}
			if ("deletefail".equals(pass)) {req.setAttribute("deletefail", "deletefail");}
			}
			try {
				
                BookVO bookVO = new BookVO();
                if(req.getParameter("book_name")!=null){
                String book_name = new String(req.getParameter("book_name").trim());
                    if (book_name.length() != 0) {
                        bookVO.setBook_name(book_name);
                    }
                }
                if(req.getParameter("book_author")!=null){
                String book_author = new String(req.getParameter("book_author").trim());
                    if (book_author.length() != 0) {
                        bookVO.setBook_author(book_author);
                    }
                }
                if(req.getParameter("type_no")!=null){
                    String type_no = req.getParameter("type_no").trim();
                    if(type_no.length()!=0){
                    bookVO.setType_no(new Integer(type_no));
                    }
                }
                if(req.getParameter("comp_no")!=null){
                    String comp_no = req.getParameter("comp_no").trim();
                    if(comp_no.length()!=0){
                    bookVO.setComp_no(new Integer(comp_no));
                    }
                }
				/**************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
                HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
					session.setAttribute("querybookVO", bookVO); 
				}

				/**************************** 2.開始複合查詢 ***************************************/
				BookService bookSvc = new BookService();
				List<BookVO> list = bookSvc.query(map);
				if(list.size()==0){
					req.setAttribute("notfound", "notfound");
				}
				/**************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				if("Front_End".equals(req.getParameter("from"))){
					req.setAttribute("bookList", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/Front-End/book/booklist.jsp"); // 成功轉交book.jsp
					successView.forward(req, res);
				}
				if("Back_End".equals(req.getParameter("from")) ){
					req.setAttribute("listBooks_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher("/Back-End/book/book.jsp?action=query"); // 成功轉交book.jsp
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				String url ="";
				if("Front_End".equals(req.getParameter("from"))){
					url = "/Front-End/book/booklist.jsp";
				}
				if("Front_End".equals(req.getParameter("from"))){
					url = "/Back-End/book/book.jsp?action=query";
				}
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		/****
		 *param:book_no
		 *@author jin 
		 ****/
		if(action.equals("BookDesc")){
		Integer book_no = new Integer(req.getParameter("book_no"));
		System.out.println(book_no);
		BookService bookSvc = new BookService();
		BookVO bookVO = bookSvc.getOneBook(book_no);
		JsonObject obj = new JsonObject();
		obj.addProperty("desc", bookVO.getBook_desc());
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
		
		}
	}

}
