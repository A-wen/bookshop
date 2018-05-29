package mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
//import org.apache.tomcat.util.codec.binary.Base64;

@SuppressWarnings("serial")
@WebServlet("/GroupMobileServlet")
public class GroupMobileServlet extends HttpServlet{

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();  //HttpServletRequest.getReader取得一個BufferedReader
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine())!=null){
			jsonIn.append(line);
		}
		System.out.println(jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		GroupDAO dao = new GroupDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: "+ action);
//		
		if(action.equals("getAll")){
			List<GroupVO> groups = dao.getAll();
			writeText(response, gson.toJson(groups));
		}
		//自己加
//		if(action.equals("getAll")){
//			GroupVO groups = dao.getAllByG_Name("輕鬆學會Java");
//			writeText(response, gson.toJson(groups));
//		}
	///////////////////////////////////////////////////////////////////////////////////		
			
//		}else if (action.equals("getImage")){
//			OutputStream os = response.getOutputStream();
//			int id = jsonObject.get("id").getAsInt();
//			int imageSize = jsonObject.get("imageSize").getAsInt();
//			byte[] image = spot_interface.getImage(id);
//			if(image != null){
//				image = ImageUtil.shrink(image, imageSize);
//				response.setContentType("image/jpeg");
//				response.setContentLength(image.length);
//			}
//			os.write(image);
//		}else if(action.equals("spotInsert")|| action.equals("spotUpdate")){
//			String spotJson = jsonObject.get("spot").getAsString();
//			SpotVO spotVO = gson.fromJson(spotJson, SpotVO.class);
//			String imageBase64 = jsonObject.get("imageBase64").getAsString();
//			byte[] image = Base64.decodeBase64(imageBase64);
//			int count = 0;
//			if (action.equals("spotInsert")){
//				count = spot_interface.insert(spotVO, image);
//			}else if (action.equals("spotUpdate")){
//				count = spot_interface.update(spotVO, image);
//			}
//			writeText(response, String.valueOf(count));
//		}else if (action.equals("spotDelete")){
//			String spotJson = jsonObject.get("spot").getAsString();
//			SpotVO spotVO = gson.fromJson(spotJson, SpotVO.class);
//			int count = spot_interface.delete(spotVO.getId());
//			writeText(response, String.valueOf(count));
//		}else if (action.equals("findById")){
//			int id = jsonObject.get("id").getAsInt();
//			SpotVO spotVO = spot_interface.findById(id);
//			writeText(response,gson.toJson(spotVO));
//		}else{
//			writeText(response, "");
//		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
//		Spot_interface spot_interface = new SpotDAO();
//		List<SpotVO> spots = spot_interface.getAll();
//		writeText(response, new Gson().toJson(spots));
		GroupDAO dao = new GroupDAO();
//		List<GroupVO> groups = dao.getAll();
//		writeText(response, new Gson().toJson(groups));
		
		
		//自己加
		//GroupDAO dao = new GroupDAO(); xx
//		GroupVO groups2 = dao.getAllByG_Name("快樂學習HTML"); //jason
//		writeText(response, new Gson().toJson(groups2));	//jason
		
		GroupVO groups2 = dao.getAllByGNo(1); 				//test
		writeText(response, new Gson().toJson(groups2));	//test
		
	}

	private void writeText(HttpServletResponse res, String outText) 
			throws IOException{
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		 System.out.println("outText: " + outText);
		out.print(outText);
	}
	
}

