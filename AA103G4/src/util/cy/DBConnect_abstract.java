package util.cy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBConnect_abstract implements DBConnect {
	
	@Override
	public ResultSet query(Connection con,String sql) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	@Override
	public ResultSet query(Connection con,String sql,Integer parameter) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,parameter);
		return pstmt.executeQuery();
	}
	
	@Override
	public ResultSet query(Connection con,String sql,Integer parameter1,Integer parameter2)throws SQLException{
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,parameter1);
		pstmt.setInt(2,parameter2);
		return pstmt.executeQuery();
	}
	
	@Override
	public ResultSet query(Connection con,String sql,Integer parameter1,String parameter2)throws SQLException{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,parameter1);
			pstmt.setString(2,"%" + parameter2 + "%");
			return pstmt.executeQuery();
	}

	@Override
	public ResultSet queryKeyword(Connection con,String sql,String parameter1,String parameter2)throws SQLException{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,parameter1);
			pstmt.setString(2,"%" + parameter2 + "%");
			return pstmt.executeQuery();
	}

	
}
