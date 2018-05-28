package mobile;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.s_book.model.S_bookService;
import com.s_book.model.S_bookVO;
import com.s_gro_dis.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookAppService;
import com.book.model.BookVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


@WebServlet("/S_gro_disAppServlet.do")
public class S_gro_disAppServlet extends HttpServlet{

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null){
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		
		S_gro_disAppDAO_interface memDao = new S_gro_disAppDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);
		
//		BookAppService bookSvc = new BookAppService();
//		String action = jsonObject.get("action").getAsString();
//		System.out.println("action: "+ action);
		
		//讀書會討論S_gro_dis只要取出該讀書會編號的所有討論
		
		
//		if ("getImage".equals(action)) {
//			OutputStream os = res.getOutputStream();
//			Integer book_no = jsonObject.get("book_no").getAsInt();
//			int imageSize = jsonObject.get("imageSize").getAsInt();
//			byte[] image = bookSvc.getBookImg(book_no);
//			if (image != null) {
//				image = ImageUtil.shrink(image, imageSize);
//				res.setContentType("image/jpeg");
//				res.setContentLength(image.length);
//				os.write(image);
//				os.close();
//			}
//		
//	}else if ("getOneBook".equals(action)){
//		System.out.println(jsonObject.get("book_no").getAsInt());
//		BookVO book = bookSvc.getOneBook(jsonObject.get("book_no").getAsInt());
//		writeText(res, gson.toJson(book));
//	}
//} 
		 if (action.equals("getAll")) {
			   int dis_ar_no = jsonObject.get("dis_ar_no").getAsInt();
			   List<S_gro_disAppVO> s_gro_disAppVOs = memDao.getClub(dis_ar_no);
			   writeText(response, gson.toJson(s_gro_disAppVOs));
			  } else {
			   writeText(response, "");
			  }
			 
	 if (action.equals("getClub")) {
		 System.out.println(123456);
		   int dis_ar_no = jsonObject.get("dis_ar_no").getAsInt();
		   List<S_gro_disAppVO> s_gro_disAppVOs = memDao.getClub(dis_ar_no);
		   System.out.println(s_gro_disAppVOs);
		   writeText(response, gson.toJson(s_gro_disAppVOs));
		  } else {
		   writeText(response, "");
		  }
		 }
//		if ("getC".equals(action)){
//		System.out.println(jsonObject.get("dis_ar_no").getAsInt());
//		S_gro_disAppVO s_gro_disAppVO = s_gro_disAppVOSvc.getOneDis(jsonObject.get("dis_ar_no").getAsInt());
//		writeText(res, gson.toJson(s_gro_disAppVO));
//	}
//}
	

	private void writeText(HttpServletResponse   response, String outText) throws IOException {
		  response.setContentType(CONTENT_TYPE);
		PrintWriter out =  response.getWriter();
		out.print(outText);
	}
}
