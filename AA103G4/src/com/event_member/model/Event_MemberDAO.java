

package com.event_member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;

import com.event_info.controller.EventInfoServlet;
import com.event_info.model.Event_InfoVO;
import com.mem.model.MemVO;
import com.s_gro_info.model.S_gro_infoVO;

import util.HibernateUtil;
import util.cy.DBConFactory;
import util.cy.DBConnect;

public class Event_MemberDAO implements Event_MemberDAO_Interface {

	private static Logger logger = Logger.getLogger(Event_MemberDAO.class);
	//定義SQL指令
	private static final String SELECT_PK_STMT =
			"FROM Event_MemberVO WHERE event_InfoVO.e_No=:eNo and memVO.mem_no=:mNo";
	private static final String SELECT_EVENT_S_MEMBER_STMT =
			"FROM Event_MemberVO WHERE event_InfoVO.e_No=:eNo";
	private static final String SELECT_MEMBER_S_EVENT_STMT =
			"FROM Event_MemberVO WHERE memVO.mem_no=:mNo";
	private static final String SELECT_STATUS_GROUP_BY_STATUS = 
			"SELECT EMS.S_EXP , COUNT(EMS.S_EXP) FROM E_MEMBER_STATUS EMS INNER JOIN EVENT_MEMBER EMEM "
			+ "ON EMS.M_STATUS = EMEM.M_STATUS WHERE EMEM.E_NO=:eNo GROUP BY EMS.S_EXP";
	
	@Override
	public int saveOrUpdate(Event_MemberVO event_memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int rows = 0;
	    try{
	        Transaction tx = session.beginTransaction();
	        SQLQuery query = session.createSQLQuery("SELECT JOIN_EVENT(?,?,?) as result FROM DUAL");
	        query.setParameter(0, event_memberVO.getEvent_InfoVO().getE_No());
	        query.setParameter(1, event_memberVO.getMemVO().getMem_no());
	        query.setParameter(2, event_memberVO.getM_Status());
	        query.addScalar("result",IntegerType.INSTANCE);
	        rows = (int)query.list().get(0);
//	        tx.commit();
//	        result = true;
	    } catch (RuntimeException ex) {

	        logger.error(ex.toString());
	        throw ex;
	    }
		return rows;
	}

	@Override
	public Event_MemberVO findByPK(Integer e_No, Integer m_No) {
		Event_MemberVO event_member = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try{
	        session.beginTransaction();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery(SELECT_PK_STMT);
	        query.setParameter("eNo",e_No);
	        query.setParameter("mNo",m_No);
	        List<Event_MemberVO> event = query.list();
	        if(event.size()>0)
	        	event_member = event.get(0);
	    } catch (RuntimeException ex) {
	        session.getTransaction().rollback();
	        throw ex;
	    }
		return event_member;
	}

	@Override
	public List<Event_MemberVO> getMemberList(Integer e_No) {
		List<Event_MemberVO> members = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try{
	        session.beginTransaction();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery(SELECT_EVENT_S_MEMBER_STMT);
	        query.setParameter("eNo",e_No);
	        members = query.list();
	    } catch (RuntimeException ex) {
	        session.getTransaction().rollback();
	        throw ex;
	    }
		return members;
	}

	@Override
	public Map<String, Integer> getEventMemberCount(Integer e_No) {
		// XXX 要改用HQL
		Map<String, Integer> statusCount = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try{
	        session.beginTransaction();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createSQLQuery(SELECT_STATUS_GROUP_BY_STATUS);
	        query.setParameter("eNo",e_No);
	        List<Object[]> list = query.list();
	        for(Object[] attr:list){
	        	statusCount.put(attr[0].toString(), Integer.parseInt(attr[1].toString()));
	        }
	        
	    } catch (RuntimeException ex) {
	        session.getTransaction().rollback();
	        throw ex;
	    }
		return statusCount;
	}

	@Override
	public List<Event_MemberVO> getEventList(Integer m_No) {
		List<Event_MemberVO> events = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try{
	        session.beginTransaction();
	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery(SELECT_MEMBER_S_EVENT_STMT);
	        query.setParameter("mNo",m_No);
	        events = query.list();
	    } catch (RuntimeException ex) {
	        session.getTransaction().rollback();
	        throw ex;
	    }
		return events;
	}
	
	


}
