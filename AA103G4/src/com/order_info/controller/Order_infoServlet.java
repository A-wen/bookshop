package com.order_info.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.delivery_status.model.Delivery_StatusVO;
import com.google.gson.Gson;
import com.mem.model.MemVO;
import com.order_info.model.Order_InfoService;
import com.order_info.model.Order_InfoVO;
import com.order_item.model.Order_ItemVO;
import com.order_status.model.Order_StatusVO;
import com.paymeny_method.model.Payment_MethodVO;
import com.shopping_cart.model.Shopping_CartVO;
import com.temp_cart.model.Temp_CartService;

import org.apache.log4j.Logger;


@WebServlet("/Front-End/cart/checkout")
public class Order_infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Order_infoServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json;charset=UTF-8"); //設定回應type為json
//		Gson resultGson = new Gson();
//		Map<String,String> result = new HashMap<>();
//		String arg = request.getPathInfo();
//		if(arg==null){ //沒帶/xxx的get
//			Order_InfoService serv = new Order_InfoService();
//			List<Order_InfoVO> orderList = serv.getAll();
//			String[] orderArray = new String[orderList.size()];
//			
//			Gson queryObject = new Gson();
//			for(Order_InfoVO item:orderList){
//				System.out.println(item.getO_Id()+" "+item.getMemVO().getMem_name());
//			}
//			result.put("result", "success");
//			
////			result.put("list", queryObject.toJson(orderList)); 不能直接轉，會有無限迴圈
//		}
//		String jsonStr = resultGson.toJson(result);
//		PrintWriter out = response.getWriter();
//		out.print(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		Integer mem_no = memVO.getMem_no();
		String action = request.getParameter("act");
		//進結帳畫面
		if("toPayment".equals(action)){
			//使用會員編號查他購物車內容，塞到request內。再轉結帳頁
			Temp_CartService serv = new Temp_CartService();
			List<Shopping_CartVO> cart = serv.getAllShoppingCartVO(mem_no);
			request.setAttribute("cart", cart);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Front-End/cart/payment.jsp");
			dispatcher.forward(request, response);
		}
		//處理結帳
		if("toCheckout".equals(action)){
			//抓取寄送資料
			String d_name = request.getParameter("d_name");
			String tel = request.getParameter("tel");
			String addr = request.getParameter("addr");
			Integer p_code = Integer.parseInt(request.getParameter("p_code"));
			//建立購買清單敘述
			StringBuilder sb = new StringBuilder();
			//建立訂單物件
			Order_InfoVO orderInfo = new Order_InfoVO();
			orderInfo.setMemVO(memVO);
			orderInfo.setPaymentMethod(new Payment_MethodVO(p_code));
			orderInfo.setO_Date(new Timestamp(System.currentTimeMillis()));
			orderInfo.setD_Name(d_name);
			orderInfo.setAddr(addr);
			orderInfo.setTel(tel);
			orderInfo.setDeliveryStatus(new Delivery_StatusVO());
			orderInfo.setOrderStatus(new Order_StatusVO(10));
			Integer total = 0;
			//建立訂單明細物件
			Temp_CartService tcServ = new Temp_CartService();
			List<Shopping_CartVO> cart = tcServ.getAllShoppingCartVO(mem_no);
			Set<Order_ItemVO> orderItemSet = new LinkedHashSet<>();
			orderInfo.setOrderItems(orderItemSet);
			Order_ItemVO orderItemVO = null;
			//取出JOIN好的訂單項目，加到Set內
			sb.append("<style>");
			sb.append("th, td {border:1px solid #aaa;padding: 5px; text-align:center;}");
			sb.append("table {border:2px solid black;}td:first-of-type {text-align:left;}");
			sb.append("#total {text-align:center;font-size: 1.5em;font-weight: bold;color: #FF5722;}");
			sb.append("</style>");
			sb.append("<table><tr><th>書名</th><th>數量</th><th>價格</th></tr>");
			for(Shopping_CartVO item:cart){ 
				orderItemVO = new Order_ItemVO();
				orderItemVO.setBook_no(item.getB_No());  
				orderItemVO.setO_amount(item.getB_Qty());
				//當優惠價=0時，改用定價
				Integer price = (item.getP_Price()!=0)?item.getP_Price():item.getB_Price();
				logger.debug("商品價格"+price);
				Integer subtotal = item.getB_Qty() * price;
				orderItemVO.setOrd_subtotal(subtotal);
				total += subtotal;
				orderItemVO.setOrderInfo(orderInfo);
				orderItemSet.add(orderItemVO);
				sb.append("<tr><td>"+item.getB_Name()+"</td>");
				sb.append("<td>"+item.getB_Qty()+"</td>");
				sb.append("<td>NT$ "+price+"</td></tr>");
			}
			sb.append("<tr><th>總價</th><td colspan=\"2\" id=\"total\">");
			sb.append("NT$ "+total+"</td></tr></table>");
			orderInfo.setO_Sum(total);
			logger.debug("訂單資訊："+orderInfo.getOrderInfo());
			Order_InfoService orderInfoServ = new Order_InfoService();
			//建立訂單
			if(orderInfoServ.createOrder(orderInfo)){
				logger.debug("訂單新增成功，刪除暫存購物車" );
				int delRows;
				StringBuilder sb2 = new StringBuilder();
				sb2.append("您好，以下為交易內容：</BR></BR>");
				sb2.append("訂單編號："+orderInfo.getO_Id()+"</BR>");
				sb2.append("收件者："+orderInfo.getD_Name()+"</BR>");
				sb2.append("聯絡電話："+orderInfo.getTel()+"</BR>");
				sb2.append("收件地址："+orderInfo.getAddr()+"</BR>");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String createTime = sdf.format(orderInfo.getO_Date());
				sb2.append("訂單成立時間："+createTime+"</BR>");
				sb2.append("訂單金額："+orderInfo.getO_Sum()+"</BR>");
				sb2.append("訂單明細：</BR></BR>");
				sb2.append(sb);
				try {
					delRows = tcServ.delAll(mem_no);
					logger.debug("刪除"+delRows+"紀錄");
					logger.debug(sb2.toString());
					util.cy.MailSender mail = new util.cy.MailSender();
					mail.sendMail(orderInfo.getMemVO().getMem_mail(), "訂單成立通知信", sb2.toString());
					RequestDispatcher successView = request.getRequestDispatcher("form.jsp");
					successView.forward(request, response);
					return;
				} catch (SQLException e) {
					logger.error("刪除失敗，原因:"+e.toString());
					return;
				}
			}else{
				logger.debug("訂單新增失敗" );
				return;
			}
		}
	}

}
