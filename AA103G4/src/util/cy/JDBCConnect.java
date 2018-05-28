package util.cy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect extends DBConnect_abstract {

	@Override
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@chihyu.ddns.net:32772:xe", "AA103G4", "7YbUN3zqetWw");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (Exception se) {
			throw new RuntimeException("An error occured. "
					+ se.getMessage());
		}
		return con;
	}
}
