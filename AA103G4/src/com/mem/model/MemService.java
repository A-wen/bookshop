//取會員姓名/電話方法 by CY

package com.mem.model;

import java.util.List;
import java.util.Set;

import com.cou.model.CouponVO;

public class MemService {

	MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}

	public MemVO getOneMember(Integer mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public MemVO addMember(String mem_name, String mem_nic, String mem_tel,
			String mem_mail, String mem_psw, byte[] mem_photo) {
		MemVO memVO = new MemVO();
		memVO.setMem_name(mem_name);
		memVO.setMem_nic(mem_nic);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_photo(mem_photo);
		String mem_No = dao.insert(memVO); //修改dao的insert,讓他回傳新增後產生的主鍵值
		memVO.setMem_no(Integer.parseInt(mem_No)); //設定memno
		return memVO;
	}

	public MemVO updateMember(String mem_name, String mem_nic, String mem_mail,
			String mem_tel, String mem_psw, byte[] mem_photo, Integer mem_no) {

		MemVO memVO = new MemVO();
		memVO.setMem_name(mem_name);
		memVO.setMem_nic(mem_nic);
		memVO.setMem_mail(mem_mail);
		memVO.setMem_tel(mem_tel);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_photo(mem_photo);
		memVO.setMem_no(mem_no);
		dao.update(memVO);

		return memVO;
	}

	//重置密碼
	public MemVO ResetPsw(String mem_psw, String mem_mail) {
		MemVO memVO = new MemVO();
		memVO.setMem_psw(mem_psw);
		memVO.setMem_mail(mem_mail);
		dao.ResetPsw(memVO);
		return memVO;
	}

	public MemVO getByPsw(String psw){
		return dao.getByPSW(psw);
	}
	
	public MemVO selectLogin(String mem_mail, String mem_psw) {
		return dao.selectLogin(mem_mail, mem_psw);
	}

	public MemVO checkMemMailRepeat(String mem_mail) {
		return dao.checkMemMailRepeat(mem_mail);
	}


	public MemVO checkMemNicRepeat(String mem_nic) {
		return dao.checkMemNicRepeat(mem_nic);
	}


	public MemVO checkMemPhoneRepeat(String mem_tel) {
		return dao.checkMemPhoneRepeat(mem_tel);
	}
	
	public MemVO getUserInfo(Integer mem_no){
		return dao.getUserInfo(mem_no);
	}
	
	//	查詢一個會員擁有的優惠金
		public Set<CouponVO> getCouponsByMemno(Integer mem_no){
		return dao.getCouponsByMemno(mem_no);
	}
}
