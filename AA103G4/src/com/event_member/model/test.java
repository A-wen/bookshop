package com.event_member.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cy/testeventmember")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Event_MemberService serv = new Event_MemberService();
		
		//查詢該會員某活動的報名狀況
		//Event_MemberVO vo = serv.findByPK(2, 105);
		//response.getWriter().append(vo.getMemVO().getMem_name());
//		//查詢活動會員名單
//		List<Event_MemberVO> members = serv.getMemberList(2);
//		for(Event_MemberVO mem:members){
//			String eventStr = "活動名稱： "+mem.getEvent_InfoVO().getE_Name();
//			String memName = "<BR>會員名稱： "+mem.getMemVO().getMem_nic();
//			response.getWriter().append(eventStr).append(memName);
//		}
//		//查詢活動報名狀況
//		Map<String, Integer> statusCount = serv.getEventMemberCount(2);
//		String str1 = "Going: "+statusCount.get("Going");
//		String str2 = "Maybe: "+statusCount.get("Maybe");
//		response.getWriter().append(str1).append(str2);
		//查詢會員報名參加的活動
		List<Event_MemberVO> members = serv.getEventList(101);
		for(Event_MemberVO mem:members){
			String eventStr = "活動名稱： "+mem.getEvent_InfoVO().getE_Name();
			String memName = "<BR>會員名稱： "+mem.getMemVO().getMem_nic();
			response.getWriter().append(eventStr).append(memName);
		}
		
		
		
	}



}
