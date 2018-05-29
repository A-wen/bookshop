package com.cou.model;

import java.sql.Date;
import java.util.List;



public class CouponService {
	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponDAO();
	}
	public CouponVO addCoupon( Date cou_start,Date cou_end, double cou_dis, String cou_exp,
		int mem_no, String cou_name){
		
		CouponVO couVO = new CouponVO();
		couVO.setCoustart(cou_start);
		couVO.setCouend(cou_end);
		couVO.setCoudis(cou_dis);
		couVO.setCouexp(cou_exp);
		couVO.setMemno(mem_no);
		couVO.setCouname(cou_name);
		dao.insert(couVO);
		return couVO;
	}
	public CouponVO updateCoupon(int cou_no, Date cou_start,Date cou_end, double cou_dis, String cou_exp,
			int mem_no, String cou_name){
		
	
			CouponVO couVO = new CouponVO();
			couVO.setCouno(cou_no);
			couVO.setCoustart(cou_start);
			couVO.setCouend(cou_end);
			couVO.setCoudis(cou_dis);
			couVO.setCouexp(cou_exp);
			couVO.setMemno(mem_no);
			couVO.setCouname(cou_name);
			dao.update(couVO);
			return couVO;
		
	}
	public void deleteCoupon(Integer cou_no) {
		dao.delete(cou_no);
	}

	public CouponVO getOneCoupon(Integer cou_no) {
		return dao.findByPK(cou_no);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}

	
	