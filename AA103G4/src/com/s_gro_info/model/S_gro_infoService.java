package com.s_gro_info.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.s_gro_mem.model.S_gro_memVO;

public class S_gro_infoService {
	
private S_gro_infoDAO_interface dao;
	
	public S_gro_infoService() {
		dao = new S_gro_infoDAO();
	}
	
	public S_gro_infoVO addS_gro_info(String s_gro_name, String s_con, Integer mem_no,
			Integer cs_no, java.sql.Date cre_date, String s_gro_sta) {
		
		S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();

		s_gro_infoVO.setS_gro_name(s_gro_name);
		s_gro_infoVO.setS_con(s_con);
		s_gro_infoVO.setMem_no(mem_no);
		s_gro_infoVO.setCs_no(cs_no);
		s_gro_infoVO.setCre_date(cre_date);
		s_gro_infoVO.setS_gro_sta(s_gro_sta);
//		System.out.println("222222222");
		dao.insert(s_gro_infoVO, mem_no);
//		System.out.println("333333333");
		return s_gro_infoVO;
	}
	
	public S_gro_infoVO updateS_gro_info(Integer s_gro_no, String s_gro_name, String s_con, Integer mem_no,  
			Integer cs_no, java.sql.Date cre_date, String s_gro_sta) {

		S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();

		s_gro_infoVO.setS_gro_no(s_gro_no);
		s_gro_infoVO.setS_gro_name(s_gro_name);
		s_gro_infoVO.setS_con(s_con);
		s_gro_infoVO.setMem_no(mem_no);
		s_gro_infoVO.setCs_no(cs_no);
		s_gro_infoVO.setCre_date(cre_date);
		s_gro_infoVO.setS_gro_sta(s_gro_sta);
		dao.update(s_gro_infoVO);

		return s_gro_infoVO;
	}
	
	public S_gro_infoVO updateGroup(Integer s_gro_no, String s_gro_name, String s_con, Integer mem_no,  
			Integer cs_no, String s_gro_sta) {

		S_gro_infoVO s_gro_infoVO = new S_gro_infoVO();

		s_gro_infoVO.setS_gro_no(s_gro_no);
		s_gro_infoVO.setS_gro_name(s_gro_name);
		s_gro_infoVO.setS_con(s_con);
		s_gro_infoVO.setMem_no(mem_no);
		s_gro_infoVO.setCs_no(cs_no);
		s_gro_infoVO.setS_gro_sta(s_gro_sta);
		dao.update(s_gro_infoVO);

		return s_gro_infoVO;
	}
	
	public void deleteS_gro_info(Integer s_gro_no) {
		dao.delete(s_gro_no);
	}

	public S_gro_infoVO getOneS_gro_info(Integer s_gro_no) {
		return dao.findByPrimaryKey(s_gro_no);
	}
	
	//讀書會成員檢舉讀書會
//	public S_gro_infoVO findinfo(Integer mem_no) {
//		return dao.findinfo(mem_no);
//	}
			
	public List<S_gro_infoVO> getAll() {
		return dao.getAll();
	}
	public List<S_gro_infoVO> getNoJoinClub(Integer mem_no) {
		return dao.findClub(mem_no);
	}
	public List<S_gro_infoVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public S_gro_infoVO getByNo(Integer s_gro_no){
		return dao.findByID(s_gro_no);
	}
	public Set<S_gro_memVO> getS_gro_memByS_gro_no(Integer s_gro_no) {
		return dao.getS_gro_memByS_gro_no(s_gro_no);
	}
	
	public List<S_gro_infoVO> getByMenager(Integer mem_no){
		return dao.getByMenager(mem_no);
	}
	
}
