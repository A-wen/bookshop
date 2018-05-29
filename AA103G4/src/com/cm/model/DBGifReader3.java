package com.cm.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cm.model.*;

/***************************************/
public class DBGifReader3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		byte[] bytes = null;
		Integer cm_no = Integer.parseInt(request.getParameter("cm_no"));
		CmService CmSvc = new CmService();
		CmVO cmVO = CmSvc.getOneCm(cm_no);
		bytes = cmVO.getCmPic();
		out.write(bytes);
	}
}