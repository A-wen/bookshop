package com.order_item.model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.cy.DBConFactory;
import util.cy.DBConnect;

import java.sql.*;

public class Order_ItemDAO implements Order_ItemDAO_Interface{
	

	private static final String INSERT_STMT =
		"INSERT INTO order_item(o_id,book_no,o_amount,cou_no,o_discount,ord_subtotal) VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String GET_GET_BY_O_ID_STMT =
		"FROM Order_ItemVO WHERE orderInfo.o_Id=:oId";
	private static final String GET_ONE_STMT =
		"SELECT o_id,book_no,o_amount,cou_no,o_discount,ord_subtotal FROM order_item where o_id and book_no=?";
	private static final String GET_STMT =
			"SELECT o_id,book_no,o_amount,cou_no,o_discount,ord_subtotal FROM order_item where o_id = ?";
	
	private DBConnect dbCon;
	
	public Order_ItemDAO(){
		dbCon = DBConFactory.createCon("JNDI");
	}
	
	public Order_ItemDAO(String conMethod){
		dbCon = DBConFactory.createCon(conMethod);
	}
	
	@Override
	public void insert(Order_ItemVO order_itemVO) {
		Session session = util.HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(order_itemVO);
			tx.commit();
		}catch(RuntimeException ex){
			tx.rollback();
		}

	}
	
	@Override
	public List<Order_ItemVO> getByOId(Integer o_id) {
		List<Order_ItemVO> list = new ArrayList<Order_ItemVO>();
		Order_ItemVO order_itemVO = null;
		Session session = util.HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			Query query = session.createQuery(GET_GET_BY_O_ID_STMT);
			query.setParameter("oId", o_id);
			list = query.list();
		}catch(RuntimeException ex){
			tx.rollback();
		}
		return list;
	}

	@Override
	public Set<Order_ItemVO> createTempOrdItem(Integer mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

//	public Order_ItemVO findByOID(Integer o_id) {
//		Order_ItemVO order_itemVO = null;
//		Session session = util.HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		
//		try(Connection con = dbCon.getCon();
//			ResultSet rs = dbCon.query(con,GET_STMT,o_id)){
//			while (rs.next()){	
//				order_itemVO = new Order_ItemVO();
//				order_itemVO.setO_id(rs.getInt("o_id"));
//				order_itemVO.setBook_no(rs.getInt("book_no"));
//				order_itemVO.setO_amount(rs.getInt("o_amount"));
//				order_itemVO.setCou_no(rs.getString("cou_no"));
//				order_itemVO.setO_discount(rs.getInt("o_discount"));
//				order_itemVO.setOrd_subtotal(rs.getInt("ord_subtotal"));
//			}
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		}
//		return order_itemVO;
//	}
}
