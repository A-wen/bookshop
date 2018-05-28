package com.person_msg.model;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Person_MsgService {

	private Person_MsgDAO_Interface DAO;
	
	public Person_MsgService(){
		DAO = new Person_MsgDAO("JDBC");
	}
	
	public boolean insert(Integer pm_Send, Integer pm_Recv,String pm_Content){
		Person_MsgVO person_MsgVO = new Person_MsgVO();
		person_MsgVO.setPm_Send(pm_Send);
		person_MsgVO.setPm_Recv(pm_Recv);
		person_MsgVO.setPm_Content(pm_Content);
		return DAO.insert(person_MsgVO);
		
	}
	
	public Set<Integer> getByUser(Integer member){
		return DAO.getByUser(member);
	}
	
	public Stack<Person_MsgVO> getByConv(Integer PM_Send, Integer PM_Recv){
		return DAO.getByConv(PM_Send, PM_Recv);
	}
}
