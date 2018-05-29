package com.person_msg.model;

import java.util.Set;
import java.util.Stack;

public class TestCode {

	public static void main(String[] args) {
		Person_MsgService serv = new Person_MsgService();
		serv.insert(101, 104, "這是新的內容!");	
		Set<Integer> testSet = serv.getByUser(101);
		for(Integer target:testSet){
			System.out.println(target);
		}
		Stack<Person_MsgVO> dialogs = serv.getByConv(101, 104);
		while(!dialogs.empty()){
			System.out.println(dialogs.pop());
		}
	}

}
