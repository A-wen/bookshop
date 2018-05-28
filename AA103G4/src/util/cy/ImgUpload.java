package util.cy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.order_info.controller.Order_infoServlet;



@WebServlet("/Front-End/event-info/imgupload.do")
@MultipartConfig
public class ImgUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ImgUpload.class);
	final String UP_DIR = "/uploaded/temp"; //上傳用目錄   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String action = request.getParameter("action");
		Map<String,String> result = new HashMap<>();
		//處理上傳檔案
		//1. 上傳活動宣傳圖片
		if("uploadEventImg".equals(action))
		{
			ServletContext context = getServletContext();
			Part file = request.getPart("upImg");
			StringBuilder fileName = new StringBuilder();
			//取名
			fileName.append(UUIDGenerator.getUUID());
			//使用者亂改的話還是不準
			String fileType = file.getHeader("content-type");
			if("image/jpeg".equals(fileType)){ //圖片為JPG類型
				fileName.append(".jpg");
			}
			if("image/png".equals(fileType)){ //圖片為PNG類型
				fileName.append(".png");
			}
			String real_dir = context.getRealPath(UP_DIR);
			logger.info("檔案路徑"+real_dir);
			FileObject fo = new FileObject(real_dir);
			try{
				fo.writeFile(file.getInputStream(),fileName.toString());
				result.put("result", "success");
				result.put("msg", fileName.toString());
			}catch(IOException e){
				result.put("result", "fail");
				result.put("msg", e.toString());
			}
		}
		
		//處理要回傳的訊息
		Gson gson = new Gson();
		String json = gson.toJson(result); 
		PrintWriter out = response.getWriter();
		
		logger.info("上傳檔案資訊："+json);
		out.print(json);
	}

}


