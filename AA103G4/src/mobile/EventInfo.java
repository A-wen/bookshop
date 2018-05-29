package mobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event_info.model.Event_InfoService;
import com.event_info.model.Event_InfoVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import util.cy.FileObject;

/**
 * Servlet implementation class EventInfo
 */
@WebServlet(urlPatterns ={
				"/eventinfo/post/event",
				"/eventinfo/get/event"
			})

public class EventInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8"); //設定回應type為json
		Event_InfoService serv = new Event_InfoService();
		Gson gson = new Gson();
		String result = null;
		//Map<String,String> result = new HashMap<>();
		
		//要get的東西
		String act = request.getParameter("rq");
		if("event".equals(act)){ //取得單項活動內容
			Integer e_No = Integer.parseInt(request.getParameter("event"));
			Event_InfoVO vo = serv.findByPK(e_No);
			//路徑地址
			final String CONTEXT_DIR = getServletContext().getRealPath("Front-End/event-info/eventImg/"+vo.getS_gro_info().getCs_no());
			//FileObject fo = new fo(CONTEXT_DIR+"Front-End/event-info/eventImg/"+vo.getG_No());
			System.out.println(CONTEXT_DIR);
			result = gson.toJson(vo);
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		if("list".equals(act)){ //取得活動清單
			List<Event_InfoVO> allEvent = serv.getAll();
			result = gson.toJson(allEvent);
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		if("img".equals(act)){ //取活動宣傳圖
			Integer e_No = Integer.parseInt(request.getParameter("event"));
			Event_InfoVO vo = serv.findByPK(e_No);
			int imageSize = Integer.parseInt(request.getParameter("imageSize"));
			System.out.println(vo.getE_Img());
			final String CONTEXT_DIR = getServletContext().getRealPath("Front-End/event-info/eventImg/"+vo.getS_gro_info().getCs_no());
			String path = CONTEXT_DIR+"/"+vo.getE_Img();
			byte[] img = Files.readAllBytes(new File(path).toPath());
			img = ImageUtil.shrink(img, imageSize);
			response.setContentType("image/jpeg");
			response.setContentLength(img.length);
			
		}
			
		

		
		
		
//		jsonStr = 
//		System.out.println("Object to JSON: " + jsonStr); //這行輸出到console

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//路徑地址
		final String CONTEXT_DIR = getServletContext().getRealPath("/");
		
		//最後要回傳用的
		Gson resuleJSON = new Gson();
		Map<String,String> result = new HashMap<>();
		
		
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
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		//轉成JsonObject後，就可以用Key來取Value
		//1. 先取action
		String action = jsonObject.get("action").getAsString();
		//新增(spotInsert)
		if ("spotInsert".equals(action)){
			//從JsonObject抓出spot這個key裡面的值
			String jsonInSpot = jsonObject.get("spot").getAsString();
			//再把這個字串用formJson轉成要存的VO
			Event_InfoVO vo = gson.fromJson(jsonInSpot, Event_InfoVO.class);
			System.out.println("\n從json抓出來的VO\n"+vo);
			//處理圖片
			//把Base64字串從JsonObject中抓出來
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			/*
			 * 1. Java8有Base64的處理類別 
			 * 2. 用Base64.getDecoder() , 再用decode(字串)來解碼 
			 * 3. 字串不能有換行符號的樣子..
			 */
			
			String real_dir = getServletContext().getRealPath("/uploaded/temp");
			//Base64轉圖方法在FileObject.base64ToImg
			FileObject fo = new FileObject(real_dir);
			vo.setE_Img(fo.base64ToImg(imageBase64, "jpg"));
			//活動時間先暫時用目前日期，需要確認Android時間字串產生方法
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//					"EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
//			Date date = simpleDateFormat.parse(orderObj.getString("date"));
			vo.setE_Date(new Timestamp(Calendar.getInstance().getTime().getTime()));
			Event_InfoService eventServ = new Event_InfoService();
			Boolean insertResult;
//			try{
//				if (eventServ.insertVO(vo)){
//					FileObject fo2 = new FileObject(real_dir);
//					fo2.moveTempFile(vo.getE_Img(), CONTEXT_DIR+"Front-End/event-info/eventImg/"+vo.getG_No());
//					result.put("result", "success");
//				}
//			}catch(Exception e){
//				result.put("result", "fail");
//				result.put("msg", e.toString());
//			}
		}
	
		//回傳結果
		response.setContentType("application/json;charset=UTF-8");
		String jsonStr = resuleJSON.toJson(result);
		PrintWriter out = response.getWriter();
		System.out.println("\n最後回傳的的JSON字串：\n"+jsonStr);
		out.print(jsonStr);

		
	}

}
