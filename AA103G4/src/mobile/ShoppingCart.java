package mobile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shopping_cart.model.Shopping_CartVO;
import com.temp_cart.model.Temp_CartService;

@WebServlet(urlPatterns ={
		"/api/ShoppingCart/post"
	})
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text;charset=UTF-8"); //設定回應type為json\
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//準備回傳的
		response.setContentType("application/json;charset=UTF-8"); //設定回應type為json
		Temp_CartService serv = new Temp_CartService();
		Gson objectToJson = new Gson();
		Map<String,String> result = new HashMap<>();
//		String result = null;
		
		/*
		//收POST上來的
		request.setCharacterEncoding("UTF-8");
		BufferedReader br = request.getReader(); //1.讀char (可讀中文) 2.讀原始內容
		//把POST上來的東西轉成字串
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("POS上來的的JSON字串：\n"+jsonIn); //測試用
		
		//把讀進來的東西轉成JsonObject (
		Gson jsonToJava = new Gson();
		JsonObject jsonObject = jsonToJava.fromJson(jsonIn.toString(),JsonObject.class);
		//準備判斷動作
		String action = jsonObject.get("action").getAsString();
		//要定上傳的物件型別
		 * 
		 */
		
		//測試用 開始
		String action = "delAll";
		Integer mem_no = 101; 
		//測試用 結束
		if(("list".equals(action))&(mem_no!=0)){ //要購物車清單
			List<Shopping_CartVO> list = serv.getAllShoppingCartVO(mem_no);
			String jsonlist = objectToJson.toJson(list);
			PrintWriter out = response.getWriter();
			System.out.println("\n1.最後回傳的的JSON字串：\n"+jsonlist);
			out.print(jsonlist);
			return;
		}
		/**
		 * 新增商品到購物車
		 * 需要帶會員編號mem_no,商品編號 book_no, 商品數量b_amount
		 * 如會員購物車內已有該商品，則會把商品數量累加
		 */
		if(("add".equals(action))&(mem_no!=0)){
			//測試Hard Code開始
			Integer book_no = 1002;
			Integer b_amount = 10;
			//測試Hard Code結束
			try {
				if(serv.addItem(mem_no, book_no, b_amount)){
					//這一段有點問題，還要想錯誤處裡(寫成功但查詢失敗)
					List<Shopping_CartVO> list = serv.getAllShoppingCartVO(mem_no);
					String jsonlist = objectToJson.toJson(list);
					result.put("action", "add");
					result.put("result", "success");
					result.put("newlist", jsonlist);
				}
			} catch (SQLException e) {
				result.put("result", "fail");
				result.put("msg", e.getMessage());
			}
		}
		/**
		 * 刪除購物車商品
		 * 需要帶會員編號mem_no,商品編號陣列  book[]
		 * 如只有一本時可用{1001}
		 */
		if(("del".equals(action))&(mem_no!=0)){
			//測試Hard Code開始
			Integer[] book = {1002};
			Integer book_no = 1002;
			int rows=0;
			//測試Hard Code結束
			try{
				rows = serv.delItems(mem_no, book);
				List<Shopping_CartVO> list = serv.getAllShoppingCartVO(mem_no);
				String jsonlist = objectToJson.toJson(list);
				result.put("action", "del");
				result.put("result", "success");
				result.put("effectRows",Integer.toString(rows));
				result.put("newlist", jsonlist);
			} catch(SQLException e){
				result.put("result", "fail");
				result.put("msg", e.getMessage());
			}
		}
		
		/**
		 * 清空會員購物車
		 * 需要帶會員編號mem_no
		 */
		if(("delAll".equals(action))&(mem_no!=0)){
			int rows=0;
			try{
				rows = serv.delAll(mem_no);
				List<Shopping_CartVO> list = serv.getAllShoppingCartVO(mem_no);
				String jsonlist = objectToJson.toJson(list);
				result.put("action", "delAll");
				result.put("result", "success");
				result.put("effectRows",Integer.toString(rows));
				result.put("newlist", jsonlist);
			} catch(SQLException e){
				result.put("result", "fail");
				result.put("msg", e.getMessage());
			}
		}
			
		
		String jsonStr = objectToJson.toJson(result);
		PrintWriter out = response.getWriter();
		System.out.println("\n2.最後回傳的的JSON字串：\n"+jsonStr);
		out.print(jsonStr);
	}

}
