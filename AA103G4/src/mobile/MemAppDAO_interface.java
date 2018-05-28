package mobile;

import java.util.List;

/** interface=隞 **/
public interface MemAppDAO_interface {
	/********************* 隞乩�鞊⊥瘜� **********************/
	/** �憓�蝑�鞈��瘜� **/
	public void insert(MemAppVO memAppVO);

	/** 靽格��鞈��瘜� **/
	public void update(MemAppVO memAppVO);

	/** �閰Ｖ�蝑�鞈��瘜� **/
	public MemAppVO findByPrimaryKey(Integer mem_no);

	/** �閰Ｘ����鞈��瘜� **/
	public List<MemAppVO> getAll();

	/** �� **/
	public MemAppVO selectLogin(String email, String passwd);

	/** Ajax瑼Ｘ閮餃�董��瘜� **/
	public MemAppVO checkMemMailRepeat(String mem_mail);

	/** Ajax瑼Ｘ閮餃�蝔望瘜� **/
	public MemAppVO checkMemNicRepeat(String mem_nic);

	/** Ajax瑼Ｘ閮餃�閰望瘜� **/
	public MemAppVO checkMemPhoneRepeat(String mem_tel);
	
	/** 敹��Ⅳ嚗�撖Ⅳ��瘜�**/
	public void ResetPsw(MemAppVO memAppVO);

//	public String loginCheck(MemAppVO memVO);
	
	public int isAccountDup(MemAppVO memAppVO);//自己加
}



//package com.mem.model;
//public interface MemberAppDAO_interface {
//
//	public String findByAcctPwd(String mem_mail,String mem_psw);
//
//	
//}
