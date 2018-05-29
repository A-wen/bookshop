/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package util;

import java.util.*;

public class jdbcUtil_CompositeQuery_Books {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("book_no".equals(columnName) || "type_no".equals(columnName) || "comp_no".equals(columnName))// 用於其他
			aCondition = columnName + "=" + value;
		else if ("book_name".equals(columnName) || "book_author".equals(columnName))  // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet(); //從map拿出key集合
		StringBuffer whereCondition = new StringBuffer(); //最後要組合成的複合查詢字串
		int count = 0;
		StringBuffer lastCondition = new StringBuffer(")");
		for (String key : keys) { //拿出key集合中每一把key
			String value = map.get(key)[0]; //用key去拿出傳進來的map中，該key對應到的value
			if (value != null && value.trim().length() != 0	&& !compareKey(key)) {
				//取出來的key不是null,長度不是0,而且key不是action,rownum,orderby
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}	
			if (value != null && value.trim().length() != 0	&&compareKey(key)){
				if("ORDERBY".equals(key.toUpperCase())){
					lastCondition.insert(0,"ORDER BY " + value.trim());
				}
				if("ROWNUM".equals(key.toUpperCase())){
					lastCondition.append(" WHERE RN BETWEEN "+ value.trim());
				}
			}
		}
		whereCondition.append(lastCondition.toString());
		return whereCondition.toString();
	}
	

	
	

	private static boolean compareKey(String key) {
		String compareStr = "ACTION,ROWNUM,ORDERBY,PASS,FROM";
		if( compareStr.contains(key.toUpperCase()))
			return true;
		else
			return false;
		}

}
