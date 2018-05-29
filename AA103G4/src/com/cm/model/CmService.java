package com.cm.model;

import java.sql.Date;
import java.util.List;



public class CmService {
	private CmDAO_interface dao;

	public CmService() {
		dao = new CmDAO();
	}
	public CmVO addCm(Integer cm_th, String cm_name, String cm_inv, String cm_url, Date cm_start,
	       Date cm_end, byte[] cm_pic ){
		//以下是包裝資料	//新增	
	
		
		CmVO cmVO= new CmVO();
		
		cmVO.setCmName(cm_name);
		cmVO.setCmStart(cm_start);
		cmVO.setCmEnd(cm_end);		
		cmVO.setCmPic(cm_pic);
		cmVO.setCmTh(cm_th);
		cmVO.setCminv(cm_inv);
		cmVO.setCmUrl(cm_url);
		dao.insert(cmVO);
		return cmVO;
		
	}
	public CmVO updateCm(Integer cm_no, Integer cm_th, String cm_name, String cm_inv, String cm_url, Date cm_start,
	                   Date cm_end, byte[] cm_pic ){
			//以下是包裝資料	//修改
						
			CmVO cmVO= new CmVO();
			
			cmVO.setCmNo(cm_no);
			cmVO.setCmName(cm_name);
			cmVO.setCmStart(cm_start);
			cmVO.setCmEnd(cm_end);		
			cmVO.setCmPic(cm_pic);
			cmVO.setCmTh(cm_th);
			cmVO.setCminv(cm_inv);
			cmVO.setCmUrl(cm_url);
			dao.update(cmVO);
			return cmVO;	
			}

	public void deleteCm(Integer cm_no) {
		dao.delete(cm_no);
	}

	public CmVO getOneCm(Integer cm_no) {
		return dao.findByPK(cm_no);
	}

	public List<CmVO> getAll() {
		return dao.getAll();
	}
}
