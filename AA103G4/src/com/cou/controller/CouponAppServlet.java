package com.cou.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cou.model.CouponAppService;
import com.cou.model.CouponService;
import com.cou.model.CouponVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/CouponAppServlet.do")
public class CouponAppServlet extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		CouponAppService couponSvc = new CouponAppService();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);
		
		if ("getCoupon".equals(action)) {
			System.out.println(jsonObject.get("mem_no").getAsString());
			List<CouponVO> coupon = couponSvc.getAllCoupons(jsonObject.get("mem_no").getAsInt());
			System.out.println(coupon.size());
			writeText(res, gson.toJson(coupon));
		}

}
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
	}
}
