package com.s_gro_dis.model;

import java.util.*;

public interface S_gro_disDAO_interface {
		public void insert(S_gro_disVO s_gro_disVO);
	    public void update(S_gro_disVO s_gro_disVO);
	    public void delete(Integer dis_ar_no);
	    public S_gro_disVO findByPrimaryKey(Integer dis_ar_no);
	    public List<S_gro_disVO> getClub(Integer s_gro_no);
	    public List<S_gro_disVO> getAll();
        public List<S_gro_disVO> getAll(Map<String, String[]> map);
}
