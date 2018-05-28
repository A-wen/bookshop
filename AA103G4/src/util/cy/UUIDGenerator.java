package util.cy;

import java.util.UUID;

public class UUIDGenerator {
	public static String getUUID(){ 
		String s = UUID.randomUUID().toString();
		StringBuilder sb = new StringBuilder();
//		System.out.println(s);
		sb.append(s.substring(0,8));
		sb.append(s.substring(9,13));
		sb.append(s.substring(14,18));
		sb.append(s.substring(19,23));
		sb.append(s.substring(24));
		return sb.toString();
	}
}
