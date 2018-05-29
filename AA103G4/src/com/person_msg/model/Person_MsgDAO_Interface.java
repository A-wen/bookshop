package com.person_msg.model;

//import java.util.List;
//import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public interface Person_MsgDAO_Interface {

	/**
	 * 建立新的對話記錄
	 * @param person_MsgVO 對話物件
	 * @return 操作是否成功
	 */
	public boolean insert(Person_MsgVO person_MsgVO);
	
	/**
	 * 
	 * 實作時必須注意是將從這兩者最新到先前尚未刪除(封存)的項目一起更改狀態
	 * 需要更改的對象1. A發給B的，更改S_STATUS到0 2.B發給A的，更改R_STATUS到0
	 * @param Person_MsgVO 目標訊息。會更改此訊息前，所有的訊息
	 * @return 影響的訊息數量
	 */
	/**
	 * 讓發起者(A)可以刪除(封存)與聊天對象(B)的訊息
	 * 要操作DB兩次  
	 * 1. A寄給B的訊息(PM_Send A to PM_Recv B)，更改S_STATUS到0
	 * 2. B寄給A的訊息(PM_Send B to PM_Recv A)，更改R_STATUS到0
	 * @param PM_Send 發起者A
	 * @param PM_Recv 聊天對象B
	 * @return
	 */
	public int archive(Integer PM_Send,Integer PM_Recv);
	
	/**
	 * 傳入使用者編號，回傳與此使用者有尚未刪除對話的對象清單
	 * @param member
	 * @return 對話目標的使用者編號清單
	 */
	public Set<Integer> getByUser(Integer member);
	
	/**
	 * 輸入發信者A與聊天對象B的使用者編號，回傳兩者互相發送的訊息(須排除STATUS=0)
	 * @param PM_Send 發起者A的使用者編號
	 * @param PM_Recv 聊天對象B的使用者編號
	 * @return List<Person_MsgVO> 該兩者互相發送的訊息
	 */
	public Stack<Person_MsgVO> getByConv(Integer PM_Send,Integer PM_Recv);
	
}
