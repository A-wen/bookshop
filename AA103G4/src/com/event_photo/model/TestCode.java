package com.event_photo.model;

import java.util.List;

public class TestCode {

	public static void main(String[] args) {
		Event_PhotoDAO_Interface DAO = new Event_PhotoDAO("JDBC");
		System.out.println("======單個VO=====");
		Event_PhotoVO photoVO = DAO.findByPK(1);
		System.out.println(photoVO);
		System.out.println("======所有VO=====");
		List<Event_PhotoVO> allPhotos = DAO.getAll();
		for(Event_PhotoVO photo:allPhotos){
			System.out.println(photo);
		}
		System.out.println("======特定event的VO=====");
		List<Event_PhotoVO> eventPhotos = DAO.getByE_No(1);
		for(Event_PhotoVO photo:eventPhotos){
			System.out.println(photo);
		}
		System.out.println("======新增VO到DB=====");
		photoVO.setP_Name("1016d2392f6b4efhb408df91483b67f1");
		photoVO.setP_Seq(50);
		System.out.println("影響的列數:"+DAO.insert(photoVO));
		System.out.println("======修改VO到DB=====");
		photoVO.setP_Name("2016d1a92f6b4eb09408df91483b67f1");
		photoVO.setP_Seq(50);
		System.out.println("影響的列數:"+DAO.insert(photoVO));
		System.out.println("=====刪除=====");
		System.out.println("影響的列數:"+DAO.delete(60));
	}

}
