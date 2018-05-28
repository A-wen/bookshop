package com.event_info.controller;

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
import com.event_info.model.Event_InfoAppService;
import com.event_info.model.Event_InfoVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/Event_InfoAppServlet.do")
public class Event_InfoAppServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		Event_InfoAppService event_InfoSvc = new Event_InfoAppService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if ("getAll".equals(action)) {
			List<Event_InfoVO> event_InfoAppVO = event_InfoSvc.getAll();
			writeText(res, gson.toJson(event_InfoAppVO));
		} else if (action.equals("getImage")) {
			OutputStream os = res.getOutputStream();
			int e_No = jsonObject.get("e_No").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = event_InfoSvc.getImage(e_No);
			if (image != null) {
				image = com.book.controller.ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);
		}
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}

}
