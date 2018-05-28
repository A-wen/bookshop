package com.fun.model;

import java.util.List;


public class FunService {
	FunDAO_inteface dao;
	
	
	public FunService(){
		dao = new FunDAO();
	}

	/** �d�߫�x�������\�� **/
	public List<FunVO> getAll() {
		return dao.getAll();
	}

	/**�d�ߥ\��s���o�������\�઺�W�ٱԭz����**/
	public FunVO getOneFun(Integer fun_no) {
		return dao.findByPrimaryKey(fun_no);
	}

	/** �s�W�@����x�v���\�઺��k **/
	public FunVO addFun(String fun_name) {
		FunVO funVO = new FunVO();
		funVO.setFunName(fun_name);
		dao.insert(funVO);
		return funVO;
	}

	/** �ק��x�v���\�઺��k **/
	public FunVO updateFun(Integer fun_no, String fun_name) {

		FunVO funVO = new FunVO();
		funVO.setFunNo(fun_no);
		funVO.setFunName(fun_name);
		dao.update(funVO);

		return funVO;
	}

	/**�R����x�v�����\��**/
	public void deleteFun(Integer fun_no) {
		dao.delete(fun_no);
	}
}
