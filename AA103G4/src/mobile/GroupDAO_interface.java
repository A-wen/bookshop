package mobile;
	import java.util.*;
	
	
public interface GroupDAO_interface {
		
		/** 查詢某個 **/
//		public GroupVO findByPK(Integer g_No);
	
	/** 自己測查詢某個 **/
	public GroupVO getAllByG_Name(String g_Name);
//	public GroupVO FIND_BY_G_NAME_STMT(String g_Name);

		/** 查詢該會員追蹤的所有書 **/
//		public List<GroupVO> getFIND_BY_G_NAME_STMT(Integer g_Name);//

		/** 列出所有**/
		public List<GroupVO> getAll();
	
		/** 點擊任一個讀書會進入該讀書會明細，by查詢g_No **/
	public GroupVO getAllByG_No(Integer g_No);
}
