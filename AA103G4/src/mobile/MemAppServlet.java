package mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/MemAppServlet.do")
public class MemAppServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
			MemberAppService memberSvc = new MemberAppService();
			String action = jsonObject.get("action").getAsString();
			System.out.println("action: " + action);	
		 
			if ("checkMember".equals(action)) {
				String mem_mail = jsonObject.get("mem_mail").getAsString();
				String mem_psw = jsonObject.get("mem_psw").getAsString();
			
				String mem_no = memberSvc.findByAcctPwd(mem_mail, mem_psw);
				
				writeText(response, mem_no);
			
			}else if ("getMemImage".equals(action)){
				OutputStream os = response.getOutputStream();
				String mem_no = jsonObject.get("mem_no").getAsString();
				int imageSize = jsonObject.get("imageSize").getAsInt();
				byte[] image = memberSvc.getMemImage(mem_no);
				if (image != null) {
					image = ImageUtil.shrink(image, imageSize);
					response.setContentType("image/jpeg");
					response.setContentLength(image.length);
					os.write(image);
					os.close();
				}
					
			}else if("loginCheck".equals(action)){
				MemAppVO memAppVO = new MemAppVO();
				String mem_mail = jsonObject.get("mem_mail").getAsString();
				String mem_psw = jsonObject.get("mem_psw").getAsString();
			
				String feedback = memberSvc.loginCheck(memAppVO);
				
				writeText(response, feedback);

			}		
//	
//		MemAppDAO memAppDAO = new MemAppDAO();
//		String memberJson = jsonObject.get("memVO").getAsString();
//		MemAppVO memAppVO = gson.fromJson(memberJson, MemAppVO.class);//閫�����
//		int count = 0;
//
//		
//		if("loginCheck".equals(action)){
//			String feedback = memAppDAO.loginCheck(memAppVO);
//			 System.out.println("outText: loginCheck" );//test
//			writeText(response, feedback);
//			
//		}

	}

	
//DAO
	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		 System.out.println("outText: " + outText);
		out.print(outText);
	}
	

}


//@WebServlet("/MemAppServlet.do")
//public class MemAppServlet extends HttpServlet{
//
//	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doGet(req, res);
//	}	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		BufferedReader br = req.getReader();
//		StringBuilder jsonIn = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			jsonIn.append(line);
//		}
//		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
//		MemberAppService memberSvc = new MemberAppService();
//		String action = jsonObject.get("action").getAsString();
//		System.out.println("action: " + action);
//
//		if ("checkMember".equals(action)) {
//			String mem_mail = jsonObject.get("mem_mail").getAsString();
//			String mem_psw = jsonObject.get("mem_psw").getAsString();
//		
//			String mem_no = memberSvc.findByAcctPwd(mem_mail, mem_psw);
//			
//			writeText(res, mem_no);}		
//		}		
//		
//			private void writeText(HttpServletResponse res, String outText) throws IOException {
//				res.setContentType(CONTENT_TYPE);
//				PrintWriter out = res.getWriter();
//				out.print(outText);
//			}
//}
