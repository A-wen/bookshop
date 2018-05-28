package com.cou.model;

import java.util.List;



public class CouponAppService {

	private CouponAppDAO_interface dao;
	public CouponAppService(){
		dao = new CouponAppDAO();
	}
	
	public List<CouponVO> getAllCoupons (int mem_no){
		return dao.getAllCoupons(mem_no);
	}
	
}
