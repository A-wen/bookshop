package com.event_info.model;

//import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.event_status.model.Event_StatusVO;
import com.s_gro_info.model.S_gro_infoVO;

public class Event_InfoService {
	
	private Event_InfoDAO_Interface DAO;
	
	public Event_InfoService(){
		DAO = new Event_InfoDAO();
	}
	
	/**
	 * 新增讀書會活動 (傳入VO值建立)
	 * @param e_No 活動編號
	 * @param g_No 讀書會編號
	 * @param e_Name 活動名稱
	 * @param e_Status 活動狀態
	 * @param e_Desc 活動描述
	 * @param e_Date 活動日期
	 * @param e_Loc 活動地標
	 * @param e_Addr 活動地址
	 * @param e_Limit 活動人數限制
	 * @param e_Img 檔案名稱
	 * @return 寫入是否成功
	 */
	public boolean insertVO(Integer g_No,String e_Name,Integer e_Status,String e_Intro,
			String e_Desc,Timestamp e_Date,String e_Loc,String e_Addr,Integer e_Limit,String e_Img){	
		
		Event_InfoVO eventVO = new Event_InfoVO();
		S_gro_infoVO groupVO = new S_gro_infoVO();
		Event_StatusVO statusVO = new Event_StatusVO();
		groupVO.setS_gro_no(g_No);
		eventVO.setS_gro_info(groupVO);
		eventVO.setE_Name(e_Name);
		statusVO.setS_No(e_Status);
		eventVO.setEvent_Status(statusVO);
		eventVO.setE_Intro(e_Intro);
		eventVO.setE_Desc(e_Desc);
		eventVO.setE_Date(e_Date);
		eventVO.setE_Loc(e_Loc);
		eventVO.setE_Addr(e_Addr);
		eventVO.setE_Limit(e_Limit);
		eventVO.setE_Img(e_Img);
		return DAO.insert(eventVO);
	}
	
	/**
	 * 傳入Event_InfoVO，使用裡面的資料建立活動
	 * @param vo Event_InfoVO
	 * @return 是否成功
	 */
//	public boolean insertVO(Event_InfoVO vo){
//		return DAO.insert(vo);
//	}
	
	/**
	 * 使用讀書會編號更新內容(傳入讀書會資訊)
	 * @param e_No 活動編號
	 * @param g_No 讀書會編號
	 * @param e_Name 活動名稱
	 * @param e_Status 活動狀態
	 * @param e_Desc 活動描述
	 * @param e_Date 活動日期
	 * @param e_Loc 活動地標
	 * @param e_Addr 活動地址
	 * @return 影響列數
	 */
	public boolean updateVO(Integer e_No,String e_Name,Integer e_Status,String e_Intro,
			String e_Desc,Timestamp e_Date,String e_Loc,String e_Addr,Integer e_Limit,String e_Img,String oriImg,String fileLoc){
		
		Event_InfoVO eventVO = findByPK(e_No);
		eventVO.setE_Name(e_Name);
		eventVO.getEvent_Status().setS_No(e_Status);
		eventVO.setE_Intro(e_Intro);
		eventVO.setE_Desc(e_Desc);
		eventVO.setE_Date(e_Date);
		eventVO.setE_Loc(e_Loc);
		eventVO.setE_Addr(e_Addr);
		eventVO.setE_Limit(e_Limit);
		System.out.println("長度："+e_Img.length());
		if(e_Img.length()!=0){
			eventVO.setE_Img(e_Img);
			return DAO.update(eventVO,oriImg,fileLoc);
		}else{
			eventVO.setE_Img(oriImg);
			return DAO.update(eventVO);
		}

	}
	
	/**
	 * 使用讀書會編號刪除活動(實際上是更改狀態)
	 * @param e_No
	 * @return 影響的列數
	 */
	public boolean deleteVO(Integer e_No){
		return DAO.delete(e_No);
	}
	
	/**
	 * 使用活動編號搜索讀書會活動
	 * @param e_No 讀書會編號
	 * @return 讀書會活動
	 */
	public Event_InfoVO findByPK(Integer e_No){
		return DAO.findByPK(e_No);
	}
	
	/**
	 * 取得全部讀書會的活動
	 * @return 全部讀書會活動的物件
	 */
	public List<Event_InfoVO> getAll(){
		return DAO.getAll();
	}
	
	/**
	 * 取得某讀書會的全部活動(不包含已被隱藏的活動)
	 * @param g_No 讀書會編號
	 * @return 讀書會活動清單
	 */
	public List<Event_InfoVO> getByG_No(Integer g_No){
		return DAO.getByG_No(g_No);
	}
	
	/**
	 * 取得某會員所參加的全部活動(不包含已被隱藏的活動)
	 * @param m_No 會員編號
	 * @return 會員參加的活動清單
	 */
	public List<Event_InfoVO> geyByM_No(Integer m_No){
		return DAO.getByM_No(m_No);
	}
	
	public List<Event_InfoVO> getByKeyword(Integer g_No, String keyword){
		return DAO.getByKeyword(g_No, keyword);
	}

	public List<Event_InfoVO> getByNDay(Integer g_No, Integer days){
		return DAO.getByNDay(g_No, days);
	}
	
	public Long eventCount(){
		return DAO.eventCount();
	}
	
	public List<Event_InfoVO> getByPage(Integer startNum,Integer rows){
		return DAO.getByPage(startNum, rows);
	}
}
