package com.info.model;

import java.util.*;

public interface InformationDAO_interface {
	public void insert(InformationVO infoVO);
	public void update(InformationVO infoVO);
	public void delete(Integer info_no);
	public InformationVO findPK(Integer info_no);
	public List<InformationVO> getAll();
}
