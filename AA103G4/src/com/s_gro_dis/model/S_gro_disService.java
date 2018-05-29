package com.s_gro_dis.model;

import java.util.List;
import java.util.Map;

import com.s_book.model.S_bookVO;

public class S_gro_disService {

	private S_gro_disDAO_interface dao;
	
	public S_gro_disService() {
		dao = new S_gro_disDAO();
	}
	
	public S_gro_disVO addS_gro_dis(Integer s_gro_no, Integer mem_no, String title, 
			String dis_con, java.sql.Date ht_date) {

		S_gro_disVO s_gro_disVO = new S_gro_disVO();

		s_gro_disVO.setS_gro_no(s_gro_no);
		s_gro_disVO.setMem_no(mem_no);
		s_gro_disVO.setTitle(title);
		s_gro_disVO.setDis_con(dis_con);
		s_gro_disVO.setHt_date(ht_date);
		dao.insert(s_gro_disVO);

		return s_gro_disVO;
	}
	
	public S_gro_disVO updateS_gro_dis(Integer dis_ar_no, Integer s_gro_no, Integer mem_no, String title, 
			String dis_con, java.sql.Date ht_date) {

		S_gro_disVO s_gro_disVO = new S_gro_disVO();

		s_gro_disVO.setDis_ar_no(dis_ar_no);
		s_gro_disVO.setS_gro_no(s_gro_no);
		s_gro_disVO.setMem_no(mem_no);
		s_gro_disVO.setTitle(title);
		s_gro_disVO.setDis_con(dis_con);
		s_gro_disVO.setHt_date(ht_date);
		dao.update(s_gro_disVO);

		return s_gro_disVO;
	}
	
	public void deleteS_gro_dis(Integer dis_ar_no) {
		dao.delete(dis_ar_no);
	}

	public S_gro_disVO getOneS_gro_dis(Integer dis_ar_no) {
		return dao.findByPrimaryKey(dis_ar_no);
	}
	
	public List<S_gro_disVO> getClub(Integer s_gro_no) {
		return dao.getClub(s_gro_no);
	}

	public List<S_gro_disVO> getAll() {
		return dao.getAll();
	}
	public List<S_gro_disVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}