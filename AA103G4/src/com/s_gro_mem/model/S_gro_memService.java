package com.s_gro_mem.model;

import java.util.List;
import java.util.Map;

public class S_gro_memService {
	private S_gro_memDAO_interface dao;
	
	public S_gro_memService() {
		dao = new S_gro_memDAO();
	}
	
	public S_gro_memVO addS_gro_mem(Integer s_gro_no, Integer mem_no) {

		S_gro_memVO s_gro_memVO = new S_gro_memVO();
		s_gro_memVO.setS_gro_no(s_gro_no);
		s_gro_memVO.setMem_no(mem_no);
		System.out.println("mem_no:"+mem_no);
		System.out.println("s_gro_no:"+s_gro_no);
		
		dao.insert(s_gro_memVO);

		return s_gro_memVO;
	}
	
	public S_gro_memVO updateS_gro_mem(Integer s_gro_no, Integer mem_no, Integer au_no) {

		S_gro_memVO s_gro_memVO = new S_gro_memVO();

		s_gro_memVO.setS_gro_no(s_gro_no);
		s_gro_memVO.setMem_no(mem_no);
		s_gro_memVO.setAu_no(au_no);
		dao.update(s_gro_memVO);

		return s_gro_memVO;
	}
	
	public void delete(Integer s_gro_no, Integer mem_no) {
		dao.delete(s_gro_no, mem_no);
	}

	public S_gro_memVO getOneS_gro_mem(Integer s_gro_no) {
		return dao.findByPrimaryKey(s_gro_no);
	}
	
	public List<S_gro_memVO> findMemJoin(Integer mem_no){
//		System.out.println("aaa"+mem_no);
		return dao.findMemJoin(mem_no);
	}
	
	public List<S_gro_memVO> findMemIn(Integer s_gro_no){
		return dao.findMemIn(s_gro_no);
	}
	
	public S_gro_memVO findaguy( Integer s_gro_no,Integer mem_no){
		return 	dao.findaguy(s_gro_no, mem_no);
	}
	
	public List<S_gro_memVO> findMemYet(Integer s_gro_no){
		return dao.findMemYet(s_gro_no);
	}
	
	public List<S_gro_memVO> getAll() {
		return dao.getAll();
	}
}
