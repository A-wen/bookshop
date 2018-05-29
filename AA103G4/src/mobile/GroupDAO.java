package mobile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.event_info.model.Event_InfoVO;
import com.s_gro_info.model.S_gro_infoVO;
import com.shopping_cart.model.Shopping_CartVO;
import com.track.model.TrackVO;

import util.cy.DBConFactory;
import util.cy.DBConnect;

public class GroupDAO {

//	private static final String SELECT_BY_G_NO_STMT = 
//			"SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.LE_MEM_NO,M.MEM_NIC "
//		   +"FROM S_GRO_INFO G LEFT JOIN MEM M ON M.MEM_NO=G.LE_MEM_NO WHERE S_GRO_STA='正常' AND G.S_GRO_NO = ?";
//	private static final String SELECT_ALL_STMT = 
//			"SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.LE_MEM_NO,M.MEM_NIC "
//		   +"FROM S_GRO_INFO G LEFT JOIN MEM M ON M.MEM_NO=G.LE_MEM_NO WHERE S_GRO_STA='正常'"; 
//	private static final String FIND_BY_G_NAME_STMT ="SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.LE_MEM_NO,M.MEM_NIC WHERE G.S_GRO_NAME = ?";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SELECT_BY_G_NO_STMT = //依讀書會編號得出所有細項
			"SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.LE_MEM_NO,M.MEM_NIC "
		   +"FROM S_GRO_INFO G LEFT JOIN MEM M ON M.MEM_NO=G.LE_MEM_NO WHERE S_GRO_STA='正常' AND G.S_GRO_NO = ?";
	private static final String SELECT_ALL_STMT = 
			"SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.MEM_NO,M.MEM_NIC "
		   +"FROM S_GRO_INFO G LEFT JOIN MEM M ON M.MEM_NO=G.MEM_NO WHERE S_GRO_STA='正常'"; 
	private static final String FIND_BY_G_NAME_STMT ="SELECT G.S_GRO_NO,G.S_GRO_NAME,G.S_CON,G.MEM_NO,M.MEM_NIC FROM S_GRO_INFO G LEFT JOIN MEM M ON M.MEM_NO=G.MEM_NO WHERE G.S_GRO_NAME = ?";
	//連線設定
	private DBConnect dbCon;
	
	/**
	 * 預設建構子，使用JNDI連線
	 */
	public GroupDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	public GroupVO getAllByGNo(Integer g_No){
		GroupVO vo = null;
		try(Connection con = dbCon.getCon();
			ResultSet rs = dbCon.query(con, SELECT_BY_G_NO_STMT,g_No)){
			System.out.println(SELECT_BY_G_NO_STMT);
				while(rs.next()){
					vo = setVO(rs);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();}		
		return vo;
	}

//	public GroupVO findByPK(Integer g_No){
//		GroupVO vo = null;
//		try(Connection con = dbCon.getCon();
//			ResultSet rs = dbCon.query(con, SELECT_BY_G_NO_STMT,g_No)){
//			System.out.println(SELECT_BY_G_NO_STMT);
//				while(rs.next()){
//					vo = setVO(rs);
//				}
//			}catch (SQLException e) {
//				e.printStackTrace();
//			} catch (IOException e) {e.printStackTrace();}		
//		return vo;
//	}

	public List<GroupVO> getAll(){
		List<GroupVO> groupList = new ArrayList<>();
		GroupVO vo = null;
		try(Connection con = dbCon.getCon();
				ResultSet rs = dbCon.query(con, SELECT_ALL_STMT)){
					while(rs.next()){
						vo = setVO(rs);
						groupList.add(vo);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {e.printStackTrace();}		
		return groupList;
	}

	private GroupVO setVO(ResultSet rs) throws SQLException, IOException {
		GroupVO vo = new GroupVO();
		vo.setG_No(rs.getInt(1));
		vo.setG_Name(rs.getString(2));
		vo.setG_Intro(rs.getString(3));
		vo.setLead_No(rs.getInt(4));
		vo.setLead_Name(rs.getString(5));
		return vo;
	
	}

	public GroupVO getAllByG_Name(String g_Name) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//List<GroupVO> groupList = new ArrayList<>();
		GroupVO vo = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_G_NAME_STMT);

			pstmt.setString(1, g_Name);

			rs = pstmt.executeQuery();

			vo = new GroupVO();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				vo.setG_No(rs.getInt(1));
				vo.setG_Name(rs.getString(2));
				vo.setG_Intro(rs.getString(3));
				vo.setLead_No(rs.getInt(4));
				vo.setLead_Name(rs.getString(5));
				//groupList.add(vo);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return vo;
	}
}
