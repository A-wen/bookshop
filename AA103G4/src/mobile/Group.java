package mobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event_info.model.Event_InfoVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class Group
 */
@WebServlet("/group/get")
public class Group extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		GroupDAO dao = new GroupDAO();
		Gson gson = new Gson();
		String result = null;
		String act = request.getParameter("rq");
		if("list".equals(act)){ //取清單
			List<GroupVO> allEvent = dao.getAll();
			result = gson.toJson(allEvent);
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		
		if("group".equals(act)){ //取明細
			Integer gno = Integer.parseInt(request.getParameter("gno"));
			GroupVO vo = dao.getAllByGNo(gno);
			result = gson.toJson(vo);
			PrintWriter out = response.getWriter();
			out.print(result);
		}	
//		if("group".equals(act)){ //取明細
//			Integer gno = Integer.parseInt(request.getParameter("gno"));
//			GroupVO vo = dao.findByPK(gno);
//			result = gson.toJson(vo);
//			PrintWriter out = response.getWriter();
//			out.print(result);
//		}	
		//自己寫
		if("group".equals(act)){ //取g_Name明細
			String g_Name = request.getParameter("g_Name");
			GroupVO vo = dao.getAllByG_Name(g_Name);
			//List<GroupVO> vo = dao.getAllByG_Name(g_Name);
			result = gson.toJson(vo);
			PrintWriter out = response.getWriter();
			out.print(result);
		}	// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
