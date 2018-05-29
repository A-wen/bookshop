package jdbc.util;

import java.util.*;

public class jdbcUtil_CompositeQuery_S_gro_dis2 {
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("dis_ar_no".equals(columnName) || "s_gro_no".equals(columnName) || "mem_no".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("title".equals(columnName) || "dis_con".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("ht_date".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("dis_ar_no", new String[] { "3" });
		map.put("s_gro_no", new String[] { "3" });
		map.put("mem_no", new String[] { "102" });
		map.put("title", new String[] { "Java" });
		map.put("dis_con", new String[] { "Java" });
		map.put("ht_date", new String[] { "1985-11-17" });
		map.put("s_gro_sta", new String[] { "正常" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from s_gro_dis "
				          + jdbcUtil_CompositeQuery_S_gro_dis2.get_WhereCondition(map)
				          + "order by dis_ar_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
