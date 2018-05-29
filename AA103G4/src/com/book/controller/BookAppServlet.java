package com.book.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookAppService;
import com.book.model.BookVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/BookAppServlet.do")
public class BookAppServlet extends HttpServlet{

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null){
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		BookAppService bookSvc = new BookAppService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: "+ action);
		
		if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			Integer book_no = jsonObject.get("book_no").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = bookSvc.getBookImg(book_no);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
				os.write(image);
				os.close();
			}
		
	}else if ("getOneBook".equals(action)){
		System.out.println(jsonObject.get("book_no").getAsInt());
		BookVO book = bookSvc.getOneBook(jsonObject.get("book_no").getAsInt());
		writeText(res, gson.toJson(book));
	}else if ("getTopBook".equals(action)){
		System.out.println(jsonObject.get("type_no").getAsString());
		List<BookVO> books = bookSvc.getBooksByType(jsonObject.get("type_no").getAsInt());
		System.out.println(books.size());
		writeText(res, gson.toJson(books));
	}
		
}
	

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
	}
}
