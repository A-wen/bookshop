package com.cou.model;

import java.util.List;

public interface CouponDAO_interface {
	public void insert(CouponVO couVO);
	public void update(CouponVO couVO);
	public void delete(Integer cou_no);
	public CouponVO findByPK(Integer cou_no);
	public List<CouponVO> getAll();
}
