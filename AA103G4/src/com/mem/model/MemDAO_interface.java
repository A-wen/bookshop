//取會員姓名/電話方法 by CY

package com.mem.model;

import java.util.List;
import java.util.Set;

import com.cou.model.CouponVO;

/** interface=介面 **/
public interface MemDAO_interface {
	/********************* 以下抽象方法 **********************/
	/**
	 * 新增會員資料，回傳sequence產生的編號
	 * @param memVO 會員物件
	 * @return 由DB自動產生的編號
	 */
	public String insert(MemVO memVO);

	/** 修改會員資料的方法 **/
	public void update(MemVO memVO);

	/** 查詢一筆會員資料的方法 **/
	public MemVO findByPrimaryKey(Integer mem_no);

	/** 查詢所有會員資料的方法 **/
	public List<MemVO> getAll();

	/** 登入 **/
	public MemVO selectLogin(String email, String passwd);

	/** Ajax檢查註冊帳號方法 **/
	public MemVO checkMemMailRepeat(String mem_mail);

	/** Ajax檢查註冊暱稱方法 **/
	public MemVO checkMemNicRepeat(String mem_nic);

	/** Ajax檢查註冊電話方法 **/
	public MemVO checkMemPhoneRepeat(String mem_tel);
	
	/** 忘記密碼，更新密碼的方法**/
	public void ResetPsw(MemVO memVO);
	
	/**
	 * 取會員姓名/電話
	 * @param mem_no
	 * @return 只有包含姓名/電話的MemVO
	 */
	public MemVO getUserInfo(Integer mem_no);
	
	/**
	 * 使用密碼取出物件 (不能用在預設DB，會取出一堆相同的)
	 * @param psw
	 * @return 會員物件
	 */
	public MemVO getByPSW(String psw);
	
	/**查詢自己擁有的消費金**/
	public Set<CouponVO> getCouponsByMemno(Integer mem_no);
}
