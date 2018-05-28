package util.cy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBConnect {
	public Connection getCon();
	public ResultSet query(Connection con,String sql) throws SQLException;
	public ResultSet query(Connection con,String sql,Integer parameter) throws SQLException;
	public ResultSet query(Connection con,String sql,Integer parameter1,Integer parameter2) throws SQLException;
	public ResultSet query(Connection con,String sql,Integer parameter1,String parameter2)throws SQLException;
	public ResultSet queryKeyword(Connection con,String sql,String parameter1,String keyword) throws SQLException;
	
	
}
