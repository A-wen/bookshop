package com.s_gro_au.model;

import java.util.List;

public class S_gro_auService {

	private S_gro_auDAO_interface dao;

	public S_gro_auService() {
		dao = new S_gro_auDAO();
	}

	public S_gro_auVO addS_gro_au(String au_lev, String au_state, Integer au_no) {

		S_gro_auVO s_gro_auVO = new S_gro_auVO();

		s_gro_auVO.setAu_lev(au_lev);
		s_gro_auVO.setAu_state(au_state);
		dao.insert(s_gro_auVO);

		return s_gro_auVO;
	}

	public S_gro_auVO updateS_gro_au(String au_lev, String au_state,
			Integer au_no) {

		S_gro_auVO s_gro_auVO = new S_gro_auVO();

		s_gro_auVO.setAu_lev(au_lev);
		s_gro_auVO.setAu_state(au_state);
		s_gro_auVO.setAu_no(au_no);
		dao.update(s_gro_auVO);

		return s_gro_auVO;
	}

	public void deleteS_gro_au(Integer au_no) {
		dao.delete(au_no);
	}

	public S_gro_auVO getOneS_gro_au(Integer au_no) {
		return dao.findByPrimaryKey(au_no);
	}

	public List<S_gro_auVO> getAll() {
		return dao.getAll();
	}
}
