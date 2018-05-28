package com.order_info.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.cy.DBConFactory;
import util.cy.DBConnect;



public class Order_InfoDAO implements Order_InfoDAO_Interface{

	private static final String GET_ALL_STMT =
		"FROM Order_InfoVO";
	private static final String GET_ORDER_BY_MEM_STMT =
		"FROM Order_InfoVO WHERE memVO.mem_no=:mNo";
	
	@Override
	public boolean insert(Order_InfoVO order_infoVO) {
		boolean result = false;
		Session session = util.HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(order_infoVO);
			tx.commit();
			result = true;
		}catch(RuntimeException ex){
			tx.rollback();
		}
		return result;
	}
		
	@Override
	public Order_InfoVO findByPrimaryKey (Integer o_id){
		Order_InfoVO order_infoVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			order_infoVO = (Order_InfoVO)session.get(Order_InfoVO.class, o_id);
		}catch(RuntimeException ex){
			tx.rollback();
			throw new RuntimeException("DB錯誤：" + ex.getMessage());
		}
		return order_infoVO;	
	}
	
	@Override
	public List<Order_InfoVO> getByMemNo(Integer mem_No){
		List<Order_InfoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			Query query = session.createQuery(GET_ORDER_BY_MEM_STMT);
			query.setParameter("mNo", mem_No);
			list = query.list();
		}catch(RuntimeException ex){
			tx.rollback();
			throw new RuntimeException("DB錯誤：" + ex.getMessage());
		}
		return list;
	}
	
	@Override
	public List<Order_InfoVO> getAll(){
		List<Order_InfoVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
		}catch(RuntimeException ex){
			tx.rollback();
			throw new RuntimeException("DB錯誤：" + ex.getMessage());
		}
		return list;
	}

}
