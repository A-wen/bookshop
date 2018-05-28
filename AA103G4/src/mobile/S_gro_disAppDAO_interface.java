package mobile;

import java.util.*;

public interface S_gro_disAppDAO_interface {
		public void insert(S_gro_disAppVO s_gro_disAppVO);
	    public void update(S_gro_disAppVO s_gro_disAppVO);
	    public S_gro_disAppVO findByPrimaryKey(Integer dis_ar_no);
	    public List<S_gro_disAppVO> getClub(Integer s_gro_no);
	    public List<S_gro_disAppVO> getAll();
        public List<S_gro_disAppVO> getAll(Map<String, String[]> map);
}
