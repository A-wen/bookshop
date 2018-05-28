package com.event_info.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.cy.DBConFactory;
import util.cy.DBConnect;
import util.cy.FileObject;
import util.cy.ImgUpload;

public class Event_InfoDAO implements Event_InfoDAO_Interface {
	
	private static Logger logger = Logger.getLogger(Event_InfoDAO.class);
	
	private final String GET_ALL_STMT = "FROM Event_InfoVO ORDER BY e_No desc";
	private final String GET_BY_G_NO_STMT = "FROM Event_InfoVO WHERE g_No=:gNo and event_Status.s_No!=5 ORDER BY e_No desc";
	

	@Override
	public boolean insert(Event_InfoVO event_InfoVO) {
		boolean result = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(event_InfoVO);
			tx.commit();
			result=true;
		}catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}

	@Override
	public boolean update(Event_InfoVO event_InfoVO) {
		boolean result = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			Transaction tx = session.beginTransaction();
			session.update(event_InfoVO);
			tx.commit();
			result=true;
		}catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}
	
	
	@Override
	public boolean update(Event_InfoVO event_InfoVO,String oriImg,String fileLoc) {
		boolean result = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			Transaction tx = session.beginTransaction();
			session.update(event_InfoVO);
			tx.commit();
			// XXX 移動檔案的例外處理
			try {
				FileObject newFile = new FileObject(fileLoc+"uploaded/temp");
				String g_No = event_InfoVO.getS_gro_info().getS_gro_no().toString();
				newFile.moveTempFile(event_InfoVO.getE_Img(), fileLoc+"Front-End/event-info/eventImg/"+g_No);
				logger.info("原檔案路徑:"+fileLoc+"uploaded/temp");
				logger.info("新檔案路徑:"+fileLoc+"Front-End/event-info/eventImg/"+g_No);
//				FileObject oldFile = new FileObject(fileLoc+"uploaded/temp/"+g_No);
//				oldFile.delFile(event_InfoVO.getE_Img());
			} catch (IOException ex) {
				session.getTransaction().rollback();
				throw new RuntimeException();
			}
			result=true;
		}catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}

	@Override
	public boolean delete(Integer e_No) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Event_InfoVO findByPK(Integer e_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Event_InfoVO eventInfoVO = (Event_InfoVO)session.get(Event_InfoVO.class, e_No);
		tx.commit();
		return eventInfoVO;
	}

	@Override
	public List<Event_InfoVO> getAll() {
		List<Event_InfoVO> eventList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			eventList = query.list();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return eventList;
	}

	@Override
	public List<Event_InfoVO> getByG_No(Integer g_No) {
		List<Event_InfoVO> eventList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_BY_G_NO_STMT);
			query.setParameter("gNo", g_No);
			eventList = query.list();
			logger.info("讀書會"+g_No+"活動數量："+eventList.size());
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return eventList;
	}

	@Override
	public List<Event_InfoVO> getByKeyword(Integer g_No, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event_InfoVO> getByM_No(Integer m_No) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event_InfoVO> getByNDay(Integer g_No, Integer days) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long eventCount(){
		Long count = (long)0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			Transaction tx = session.beginTransaction();
			Query query =  session.createQuery("select count(*) as count from Event_InfoVO");
			count = (Long)query.list().get(0);
		}catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return count;
	}
	
	public List<Event_InfoVO> getByPage(Integer startNum,Integer rows){
		List<Event_InfoVO> eventList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			query.setFirstResult(startNum);
			query.setMaxResults(rows);
			eventList = query.list();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return eventList;
	}

}
