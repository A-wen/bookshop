package com.event_member.contorl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.mem.model.MemVO;
import com.event_info.controller.EventInfoServlet;
import com.event_member.model.Event_MemberService;


@WebServlet("/Front-End/event-info/join")
public class EventMemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(EventMemServlet.class);
	
	private final int CANCEL = 0;
	private final int GOING = 1;
	private final int MAYBE = 2;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		Event_MemberService serv = new Event_MemberService();
		response.setContentType("application/json;charset=UTF-8");
		Map<String,String> result = new HashMap<>();
		if(memVO==null){
			result.put("result", "fail");
			result.put("msg", "尚未登入");
		}
		if(memVO!=null){
			int memno = memVO.getMem_no();
			int action = Integer.parseInt(request.getParameter("action"));
			int event = Integer.parseInt(request.getParameter("event"));
			logger.info("memno:"+memno+",action:"+request.getParameter("action")+",event:"+request.getParameter("event"));
			boolean dbAction = false;
			switch (action){
				case GOING:
					int dbResult = serv.insert(event, memno, GOING);
					if(dbResult==1){
						result.put("result", "success");
						result.put("status", "1");
						result.put("msg", "報名成功");
						dbAction = true;
					}
					if(dbResult==0){
						result.put("msg", "活動人數已滿");
					}
					
					break;
				case MAYBE:
					if(serv.update(event, memno, MAYBE)==1){
						result.put("result", "success");
						result.put("status", "2");
						result.put("msg", "已將您列入可能會參加的清單中");
						dbAction = true;
					}
					break;
				case CANCEL:
					if(serv.delete(event, memno)==1){
						result.put("result", "success");
						result.put("status", "3");
						result.put("msg", "已將您由清單中移除");
						dbAction = true;
					}
					break;
			}
			if(!dbAction){
				result.put("result", "fail");
				if(action!=GOING){
					result.put("msg", "操作失敗，請聯絡客服人員!");
				}
			}
		}
		//讓執行緒暫停1秒，模擬網路傳輸
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.info("當機了!"+e.toString());
		}
		//輸出回應
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(result); 
		logger.info(json);
		out.print(json);
	}

}
