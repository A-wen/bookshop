package mobile;

import java.util.List;
import java.util.Map;

import com.s_book.model.S_bookVO;

public class S_gro_disAppService {

	private S_gro_disAppDAO_interface dao;
	
	public S_gro_disAppService() {
		dao = new S_gro_disAppDAO();
	}
	
	public S_gro_disAppVO addS_gro_dis(Integer s_gro_no, Integer mem_no, String title, 
			String dis_con, java.sql.Date ht_date) {

		S_gro_disAppVO s_gro_disAppVO = new S_gro_disAppVO();

		s_gro_disAppVO.setS_gro_no(s_gro_no);
		s_gro_disAppVO.setMem_no(mem_no);
		s_gro_disAppVO.setTitle(title);
		s_gro_disAppVO.setDis_con(dis_con);
		s_gro_disAppVO.setHt_date(ht_date);
		dao.insert(s_gro_disAppVO);

		return s_gro_disAppVO;
	}
	
	public S_gro_disAppVO updateS_gro_dis(Integer dis_ar_no, Integer s_gro_no, Integer mem_no, String title, 
			String dis_con, java.sql.Date ht_date) {

		S_gro_disAppVO s_gro_disAppVO = new S_gro_disAppVO();

		s_gro_disAppVO.setDis_ar_no(dis_ar_no);
		s_gro_disAppVO.setS_gro_no(s_gro_no);
		s_gro_disAppVO.setMem_no(mem_no);
		s_gro_disAppVO.setTitle(title);
		s_gro_disAppVO.setDis_con(dis_con);
		s_gro_disAppVO.setHt_date(ht_date);
		dao.update(s_gro_disAppVO);

		return s_gro_disAppVO;
	}
	

	public S_gro_disAppVO getOneS_gro_dis(Integer dis_ar_no) {
		return dao.findByPrimaryKey(dis_ar_no);
	}
	
//	public List<S_gro_disAppVO> getClub(Integer s_gro_no) {
//		return dao.getClub(s_gro_no);
//	}

	public List<S_gro_disAppVO> getAll() {
		return dao.getAll();
	}
	public List<S_gro_disAppVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}