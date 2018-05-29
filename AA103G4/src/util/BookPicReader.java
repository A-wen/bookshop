package util;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.book.model.BookService;

@WebServlet(
		urlPatterns ={
				"/img",
		})

public class BookPicReader extends HttpServlet {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DevDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	Connection con = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
        req.setCharacterEncoding("UTF-8");
        ServletOutputStream out = res.getOutputStream();
		try {
			Integer book_no = new Integer(req.getParameter("book_no"));
			BookService bookSvc = new BookService();
			out.write(bookSvc.getOneBook(book_no).getBook_pic());
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			con = ds.getConnection();
		}catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
