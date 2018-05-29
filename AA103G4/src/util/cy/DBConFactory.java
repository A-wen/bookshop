package util.cy;

public class DBConFactory {
	public static DBConnect createCon(String type){
		if ("JDBC".equals(type)){
			return new JDBCConnect();
		}
		if ("JNDI".equals(type)){
			return new JNDIConnect();
		}
		return null;
	}
	
}
