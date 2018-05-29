package com.s_gro_cs.model;

import java.util.List;
import java.util.Set;
import com.s_gro_info.model.S_gro_infoVO;

public class S_gro_csService {
	
private S_gro_csDAO_interface dao;
	
	public S_gro_csService() {
		dao = new S_gro_csDAO();
	}
	
	public S_gro_csVO addS_gro_cs(String cs_name) {

		S_gro_csVO s_gro_csVO = new S_gro_csVO();

		s_gro_csVO.setCs_name(cs_name);
		dao.insert(s_gro_csVO);

		return s_gro_csVO;
	}
	
	public S_gro_csVO updateS_gro_cs(Integer cs_no, String cs_name) {

		S_gro_csVO s_gro_csVO = new S_gro_csVO();

		s_gro_csVO.setCs_no(cs_no);
		s_gro_csVO.setCs_name(cs_name);
		dao.update(s_gro_csVO);

		return s_gro_csVO;
	}
	
	public void deleteS_gro_cs(Integer cs_no) {
		dao.delete(cs_no);
	}

	public S_gro_csVO getOneS_gro_cs(Integer cs_no) {
		return dao.findByPrimaryKey(cs_no);
	}

	public List<S_gro_csVO> getAll() {
		return dao.getAll();
	}
	
	public Set<S_gro_infoVO> getS_gro_infosByCs_no(Integer cs_no) {
		return dao.getS_gro_infosByCs_no(cs_no);
	}
}
