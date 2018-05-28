package com.temp_cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mem.model.MemVO;
import com.shopping_cart.model.Shopping_CartVO;
import com.temp_cart.model.Temp_CartService;



@WebServlet(urlPatterns ={
				"/Front-End/Cart/update.do",
				"/Front-End/Cart/cart"
			})
public class TempCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 1. cookies與DB同步機制後刪除機制  2. 抓session內的會員資料  3. error handle
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//從session取memVO確認有無登入 
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		String action = request.getParameter("act");
		
		//之後要回傳的json物件
		Map<String,String> result = new HashMap<>();
		result.put("action",action);

		if (memVO==null){ //尚未登入,使用cookies儲存欲購買商品
			System.out.println("還沒登入");
			if ("add".equals(action)){
				//建立cookies物件陣列(沒辦法取特定cookie)
				Cookie[] cookies = request.getCookies();
				Cookie cart = null;
				String[] item = request.getParameter("item").split(":");
				String bNo = item[0];
				Integer bQty = Integer.parseInt(item[1]);	
				// gson物件，用來建立或讀取json
				Gson gson = new Gson();
				boolean isFindCookie = false; //沒辦法直接取cookie,所以設個指標確認是否有取到
				if (cookies != null) {
					for (Cookie cookie : cookies) { //迭代搜索cookies陣列
						if (cookie.getName().equals("cart")) {
							isFindCookie = true; //當cookies中有一個叫cart的cookie時，指標更改為true
							cart = cookie;
							//設定後面轉回json時要用的型態
							Type collectionType = new TypeToken<Map<String,Integer>>() {
								}.getType();
							Map<String,Integer> books = gson.fromJson(cookie.getValue(),collectionType);
							/*
							 * 這段測試是否有正確轉回Map<>
							System.out.println("cookieStr:"+cookie.getValue());
							System.out.println("start");
							for (Map.Entry<String, String> entry : books.entrySet()){
								System.out.println("key: "+entry.getKey() + " ,value: " + (String)(entry.getValue()));
							}
							System.out.println("end");
							* 測試結束
							*/
							if(books.containsKey(bNo)){ //傳入的購買書籍編號已存在於cookie
								books.put(bNo, books.get(item[0])+bQty); 
							}else{
								books.put(bNo, bQty);
							}
							cart.setValue(gson.toJson(books));
							result.put("result", "success");
							result.put("message", "新增成功");
						}
					}
				}
				if(!isFindCookie){ //購物車 cookie不存在
					cart = new Cookie("cart","");
					Map<String,Integer> book = new HashMap<>();
					book.put(item[0],Integer.parseInt(item[1]));
					gson = new Gson();
					String json = gson.toJson(book); 
					cart.setValue(json);
					result.put("result", "success");
					result.put("message", "新增成功");
				}
			response.addCookie(cart);
			}
		}else{ //已登入
			Integer member = memVO.getMem_no();
			Temp_CartService serv = new Temp_CartService();
			
			/* 當沒有給動作，預設前往購物車(中間會經過過濾器強制登入)
			 * 因為註冊了/Front-End/Cart/cart這個網址。所以從這個網址進來，一定是已登入狀況
			 * 而從此網址進入不會帶其他參數時讓他前往購物車編輯畫面
			 */
			if(action==null){ 
//				HttpSession session = request.getSession();
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) { //迭代搜索cookies陣列
					if (cookie.getName().equals("cart")) {
						Gson gson = new Gson();
						Type collectionType = new TypeToken<Map<String,Integer>>() {
							}.getType();
						Map<String,Integer> books = gson.fromJson(cookie.getValue(),collectionType);
						try {
							serv.syncCart(member, books);
				            cookie.setValue("");
				            cookie.setPath("/");
				            cookie.setMaxAge(0);
			
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				List<Shopping_CartVO> list = serv.getAllShoppingCartVO(member);
				request.setAttribute("itemList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Front-End/cart/editCart.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			//新增到購物車
			if ("add".equals(action)){
				System.out.println(request.getParameter("item"));
				String[] item = URLDecoder.decode(request.getParameter("item"), "UTF-8").split(":");
				try {
					serv.addItem(member, Integer.parseInt(item[0]), Integer.parseInt(item[1]));
					result.put("result", "success");
					result.put("message", "新增成功");
				} catch (SQLException e) {
					result.put("result", "fail");
					result.put("msg", e.getMessage());
				}
			}
			//編輯商品數量
			if ("update".equals(action)){
				String[] item = URLDecoder.decode(request.getParameter("item"), "UTF-8").split(":");
				//要確認如何取
				try {
					serv.updateItem(member, Integer.parseInt(item[0]), Integer.parseInt(item[1]));
					result.put("result", "success");
					result.put("message", "編輯成功");
				} catch (SQLException e) {
					result.put("result", "fail");
					result.put("message", "編輯失敗");
					result.put("msg", e.getMessage());
				}
			}
			//刪除商品
			if ("del".equals(action)){
				String[] items = URLDecoder.decode(request.getParameter("item"), "UTF-8").split(":");
				try {
					Integer[] books = new Integer[items.length];
					for (int i=0;i<items.length;i++){
						books[i] = Integer.parseInt(items[i]);
					}
					System.out.println("執行！");
					String rows = String.valueOf(serv.delItems(member, books));
					System.out.println(rows);
					result.put("result", "success");
					result.put("message", "資料庫操作成功");
					result.put("effect_rows",rows);
				} catch (NumberFormatException e){
					result.put("result", "fail");
					result.put("msg", "書號格式錯誤:"+e.getMessage());
				} catch (SQLException e) {
					result.put("result", "fail");
					result.put("msg", "資料庫操作錯誤:"+e.getMessage());
				}
			}
			//刪除會員購物車內全部書籍
			if("delAll".equals(action)){
				try{
					String rows = String.valueOf(serv.delAll(member));
					result.put("result", "success");
					result.put("message", "刪除成功");
					result.put("effect rows",rows);
				} catch (SQLException e) {
					result.put("result", "fail");
					result.put("msg", "資料庫操作錯誤:"+e.getMessage());
				}
			}
		}

		//處理要回傳的訊息
		Gson gson = new Gson();
		String json = gson.toJson(result); 
		//讓執行緒暫停1秒，模擬網路傳輸
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(json);
		out.print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
