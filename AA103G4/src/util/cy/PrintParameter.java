package util.cy;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class PrintParameter {
	public static void print(HttpServletRequest req,Enumeration<String> names){
		while (names.hasMoreElements()){
			String paraName = names.nextElement();
			req.getParameter(paraName);
//			String paraValues[] = req.getParameterValues(paraName);
//			if(paraValues!=null){
//				for(int i=0;i<=paraValues.length;i++){
//					System.out.println(paraName+"["+i+"]"+paraValues[i]);
//				}
//			}

		}
	}
}
