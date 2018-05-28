package com.order_info_view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mem.model.MemVO;
import com.order_info_view.model.Order_Info_ViewService;
import com.order_info_view.model.Order_Info_ViewVO;
import com.order_item_view.model.Order_Item_ViewService;
import com.order_item_view.model.Order_Item_ViewVO;

@WebServlet(urlPatterns ={
		"/api/OrderInfo",
		"/api/OrderInfo/*"
	})
public class OrderInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8"); //設定回應type為json
		HttpSession session = request.getSession();
		Gson resultGson = new Gson();
		Map<String,String> result = new HashMap<>();
		//從session取memVO確認有無登入 
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		//尚未登入
		if(memVO==null){
			result.put("result", "fail");
			result.put("msg", "尚未登入");
		}
		//已登入
		if(memVO!=null){
			String arg = request.getPathInfo();
			if(arg==null){ //沒帶/xxx的get
				Order_Info_ViewService serv = new Order_Info_ViewService();
				List<Order_Info_ViewVO> orderList = serv.getByMemNo(memVO.getMem_no());
				Gson queryObject = new Gson();
				result.put("result", "success");
				result.put("list", queryObject.toJson(orderList));
			}
			if(arg!=null){ //帶/xxx的get
				// xxx 會員編號檢查，不能顯示不屬於該會員的訂單明細
				try{
					Integer oId = Integer.parseInt(arg.substring(1));
					Order_Item_ViewService serv = new Order_Item_ViewService();
					List<Order_Item_ViewVO> orderInfo = serv.getByOId(oId);
					Gson queryObject = new Gson();
					result.put("result", "success");
					result.put("list", queryObject.toJson(orderInfo));
				}catch(NumberFormatException e){
					result.put("result", "fail");
					result.put("msg", "訂單編號格式錯誤");
				}
			}
		}
		String jsonStr = resultGson.toJson(result);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
