package util.cy;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JNDIConnect extends DBConnect_abstract {

	private static Context ctx=null;
	private static DataSource ds = null;
	
	public JNDIConnect(){
		if(ctx==null){
			try {
				ctx = new javax.naming.InitialContext();
				ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DevDB");
			} catch (NamingException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
			}
		}
	}
	
	@Override
	public Connection getCon() {
		Connection con=null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return con;
	}

}
