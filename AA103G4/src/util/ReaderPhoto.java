package util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mem.model.*;
import com.order_info.controller.Order_infoServlet;



/***************************************/
public class ReaderPhoto extends HttpServlet {

	private static Logger logger = Logger.getLogger(ReaderPhoto.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
					doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/****************/
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		byte[] bytes = null;
		Integer mem_no = Integer.parseInt(request.getParameter("mem_no"));
		MemService memService = new MemService();
		MemVO memVO = memService.getOneMember(mem_no);
		bytes = memVO.getMem_photo();
		logger.info("會員編號："+mem_no+",檔案長度："+bytes.length);
		out.write(bytes);
	}

}
