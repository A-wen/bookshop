package com.event_info.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.event_info.model.Event_InfoService;
import com.event_info.model.Event_InfoVO;
import com.event_member.model.Event_MemberService;
import com.event_member.model.Event_MemberVO;
import com.mem.model.MemVO;
import com.order_info.controller.Order_infoServlet;
import com.s_gro_info.model.S_gro_infoService;
import com.s_gro_info.model.S_gro_infoVO;

import util.cy.FileObject;

@WebServlet(
		urlPatterns ={
				"/Front-End/event-info/eventinfo.do",
				"/Front-End/event-info/edit",
				"/Front-End/event-info/list",
				"/Front-End/event-info/event/*"
		})

public class EventInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EventInfoServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//xxx 資料檢查
		
		//檢查是否登入,如果登入，將登入指標設成true,並且設定mem_no(會員編號)
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		boolean isLogin = (memVO!=null)?true:false;
		int mem_no =0 ;
		if(isLogin){
			mem_no = memVO.getMem_no();
		}
//		logger.info("是否登入:"+isLogin);
		
		//抓parameter act判斷要做的動作
		String action = request.getParameter("act");
		//有送act，而且act的值是list(列出清單)
		if(action!=null&&"list".equals(action)){
			List <Event_InfoVO> events = null;  //後面寫入request的活動清單集合
			Event_InfoService serv = new Event_InfoService(); //取讀書會清單的服務
			String g_no = request.getParameter("gno");
			//沒有帶讀書會編號，列出所有讀書會活動
			if(g_no==null){
				//由session取全部活動數量，沒有的話在query
				Integer eventCount = (Integer)session.getAttribute("eventCount");
				if(eventCount==null){
					eventCount = serv.eventCount().intValue();
					session.setAttribute("eventCount",eventCount);
				}
				//由參數計算分頁數量。如沒帶參數，就把rows設成9
				String rows = request.getParameter("rows");
				int totalPages=0;
				if(rows==null){ //沒有帶每頁要顯示的數量，把rows設成9
					rows = "9";
				}
				request.setAttribute("rows", rows);
				totalPages = eventCount/Integer.parseInt(rows);
//				logger.info("eventCount:"+eventCount);
//				logger.info("rows:"+rows);
//				logger.info("totalPages:"+totalPages);
				//計算總頁數
				if((eventCount % Integer.parseInt(rows))>1){totalPages+=1;}
				request.setAttribute("pages", totalPages); //把總頁數寫到request
				//設定要取的範圍(BY頁數*每頁顯示數量)
				String page = request.getParameter("page");
				//如沒帶頁數，就把頁數設成1
				if(page==null){page = "1";}
//				logger.info("page:"+page);
//				logger.info("Integer.parseInt(page)-1:"+(Integer.parseInt(page)-1));
//				logger.info("Integer.parseInt(rows):"+Integer.parseInt(rows));
				int startNum = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
//				logger.info("啟始:"+startNum);
				events = serv.getByPage(startNum, Integer.parseInt(rows));
				request.setAttribute("currentPage", page); //把總頁數寫到request
			}
			//有帶讀書會編號，列出該讀書會活動
			if(g_no!=null){
				Integer g_No=0;
				try{  //這邊是做無法轉換成數字的錯誤處理
					g_No = Integer.parseInt(request.getParameter("gno"));
					events = serv.getByG_No(g_No);
					if(events==null){
						//用編號找不到活動時，一樣丟無法轉換數字的錯誤，讓他導到404
						throw new NumberFormatException();
					}
				}catch(NumberFormatException ex){
					RequestDispatcher errorView = request.getRequestDispatcher("/ErrorPage/404err.jsp");
					errorView.forward(request, response);
					return;
				}
			}
			request.setAttribute("events", events);
			RequestDispatcher dispatcher = request.getRequestDispatcher("elist.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//抓parameter是create的，用此編號來當作建立讀書會用的編號
//		action = request.getParameter("create");
		if (action!=null && "create".equals(action)){
			//設定下個頁面要用的標題與動作設定
			HashMap<String,String> pageStr = new HashMap<>();
			pageStr.put("title","新增讀書會活動");
			pageStr.put("action","insert");
			request.setAttribute("pageStr", pageStr);
			//檢查權限(用會員編號去找讀書會清單)
			S_gro_infoService serv = new S_gro_infoService();
			List<S_gro_infoVO> groupList = serv.getByMenager(mem_no);
			request.setAttribute("groupList", groupList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("editevent.jsp");
			dispatcher.forward(request, response);
			return; 
		}
		
		//使用活動編號抓讀書會活動資料
		String arg = request.getPathInfo().substring(1); //使用前置路徑對應，會取出/xxx。然後再從第一個字元往後切，即取出XXX
		if((arg.length()==0)){ //沒有輸入活動編號
			RequestDispatcher errorView = request.getRequestDispatcher("/ErrorPage/404err.jsp");
			errorView.forward(request, response);
			return; 
		}
		Integer e_No=null;
		try{
			e_No = Integer.parseInt(arg);
			Event_InfoService infoServ = new Event_InfoService();
			Event_InfoVO vo = infoServ.findByPK(e_No);
			if (vo==null){
				throw new NullPointerException();
			}
			Event_MemberService memServ = new Event_MemberService();
			Map<String,Integer> count = memServ.getEventMemberCount(e_No);
			if(isLogin){
				Event_MemberVO event_MemberVO = memServ.findByPK(e_No,mem_no);
				if(event_MemberVO!=null)
					request.setAttribute("event_MemVO", event_MemberVO);
			}
			request.setAttribute("event_InfoVO",vo);
			request.setAttribute("event_Count", count);
			RequestDispatcher successView = request.getRequestDispatcher("/Front-End/event-info/eventinfo.jsp");
			successView.forward(request, response);
		}catch(NumberFormatException | NullPointerException e){
			RequestDispatcher errorView = request.getRequestDispatcher("/ErrorPage/404err.jsp");
			errorView.forward(request, response);
			return; 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String act = request.getParameter("action");
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		boolean isLogin = (memVO!=null)?true:false;
		int mem_no =0 ;
		if(isLogin){
			mem_no = memVO.getMem_no();
		}
		final String CONTEXT_DIR = getServletContext().getRealPath("/");
		//執行動作判斷
		//列出所有讀書會活動
		if("listEvent".equals(act)){
			Event_InfoService serv = new Event_InfoService();
			List <Event_InfoVO> events = serv.getAll();
			request.setAttribute("events", events);
			RequestDispatcher dispatcher = request.getRequestDispatcher("eventlist.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//列出某圖書會的所有活動
		if("groupEvent".equals(act)){
			Integer g_No = Integer.parseInt(request.getParameter("g_No"));
			Event_InfoService serv = new Event_InfoService();
			List <Event_InfoVO> events = serv.getByG_No(g_No);
			request.setAttribute("events", events);
			RequestDispatcher dispatcher = request.getRequestDispatcher("elist.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//開啟編輯頁面新增活動
		if("addEvent".equals(act)){
			//設定下個頁面要用的標題與動作設定
			HashMap<String,String> pageStr = new HashMap<>();
			pageStr.put("title","新增讀書會活動");
			pageStr.put("action","insert");
			request.setAttribute("pageStr", pageStr);
			RequestDispatcher dispatcher = request.getRequestDispatcher("editevent.jsp");
			dispatcher.forward(request, response);
		}
		//開啟編輯頁面編輯活動
		if("editEvent".equals(act)){
			Event_InfoService infoServ = new Event_InfoService();
			Integer e_No = Integer.parseInt(request.getParameter("e_No")); 
			//找要編輯的資料內容，放到request內
			Event_InfoVO vo = infoServ.findByPK(e_No);
			request.setAttribute("Event_InfoVO", vo);
			//檢查權限(用會員編號去找讀書會清單)
			S_gro_infoService serv = new S_gro_infoService();
			List<S_gro_infoVO> groupList = serv.getByMenager(mem_no);
			request.setAttribute("groupList", groupList);
			//設定下個頁面要用的標題與動作設定
			HashMap<String,String> pageStr = new HashMap<>();
			pageStr.put("title","編輯讀書會活動");
			pageStr.put("action","update");
			String imgSrc = "eventImg/"+vo.getS_gro_info().getS_gro_no()+"/"+vo.getE_Img();
			pageStr.put("imgSrc",imgSrc);
			request.setAttribute("pageStr", pageStr);
			//轉送給活動編輯的JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("editevent.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("test_editevent.jsp");
		}
		//新增資料到DB
		if("insert".equals(act)){
			Event_InfoService serv = new Event_InfoService();
			// XXX 還沒做讀書會編號讀取
			Integer g_No = Integer.parseInt(request.getParameter("g_No"));
			String e_Name = request.getParameter("e_Name");
			Integer e_Status = Integer.parseInt(request.getParameter("e_Status"));
			String e_Intro = request.getParameter("e_Intro");
			String e_Desc = request.getParameter("e_Desc");
			String e_DataFromPost = request.getParameter("eventDate");
			//將傳進來的日期字串格式化後轉成Timestamp
			Timestamp e_Date = null;
	        try { 
	        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定格式化的方法
	            e_Date = new Timestamp(dateFormat.parse(e_DataFromPost).getTime()); //轉成TimeStamp
	        } catch (Exception e) { 
	        	//xxx 錯誤處理
	            e.printStackTrace();  
	        }
			String e_Loc = request.getParameter("e_Loc");
			String e_Addr= request.getParameter("e_Addr");
			Integer e_Limit= Integer.parseInt(request.getParameter("e_Limit"));
			String e_Img = request.getParameter("e_Img");
			if(serv.insertVO(g_No, e_Name, e_Status, e_Intro, e_Desc, e_Date, e_Loc, e_Addr,e_Limit,e_Img)){
				// XXX 移動檔案的例外處理
				FileObject fo = new FileObject(CONTEXT_DIR+"/uploaded/temp");
				fo.moveTempFile(e_Img, CONTEXT_DIR+"Front-End/event-info/eventImg/"+g_No);
				List <Event_InfoVO> events = serv.getByG_No(g_No);
				request.setAttribute("events", events);
				RequestDispatcher dispatcher = request.getRequestDispatcher("eventlist.jsp");
				dispatcher.forward(request, response);
				return;
			}else{
				response.getWriter().append("error");
			}
		}
		//更新DB資料(沒有做圖片沒更新的狀況！)
		if("update".equals(act)){
			Event_InfoService serv = new Event_InfoService();
			Integer e_No= Integer.parseInt(request.getParameter("e_No"));
			Integer g_No = Integer.parseInt(request.getParameter("g_No"));
			String e_Name = request.getParameter("e_Name");
			Integer e_Status = Integer.parseInt(request.getParameter("e_Status"));
			String e_Intro = request.getParameter("e_Intro");
			String e_Desc = request.getParameter("e_Desc");
			String e_DataFromPost = request.getParameter("eventDate");
			Timestamp e_Date = null;
	        try { 
	        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //設定格式化的方法
	            e_Date = new Timestamp(dateFormat.parse(e_DataFromPost).getTime()); //轉成TimeStamp
	        } catch (Exception e) { 
	            e.printStackTrace();  
	        }
			String e_Loc = request.getParameter("e_Loc");
			String e_Addr= request.getParameter("e_Addr");
			Integer e_Limit= Integer.parseInt(request.getParameter("e_Limit"));
			String e_Img = request.getParameter("e_Img");
			String oriImg = request.getParameter("ori_e_Img");
			logger.info("e_Img："+e_Img);
			if (serv.updateVO(e_No, e_Name, e_Status, e_Intro, e_Desc, e_Date, e_Loc, e_Addr, e_Limit, e_Img,oriImg,CONTEXT_DIR)){			
				List <Event_InfoVO> events = serv.getByG_No(g_No);
				request.setAttribute("events", events);
				RequestDispatcher dispatcher = request.getRequestDispatcher("eventlist.jsp");
				dispatcher.forward(request, response);
			}else{
				response.getWriter().append("error");
			}			
		}
		
		
	}

}
