package com.fun.model;

import java.util.List;


public interface FunDAO_inteface {
	
	
	/** �s�W�@����x�v���\�઺��k **/
	public void insert(FunVO funVO);

	/** �ק��x�v���\�઺��k **/
	public void update(FunVO funVO);
	/**�R����x�v�����\��**/
	public void delete(Integer fun_no);

	/**�d�ߥ\��s���o�������\�઺�W�ٱԭz����**/
	public FunVO findByPrimaryKey(Integer fun_no);

	/** �d�ߩҦ��\�઺��k **/
	public List<FunVO> getAll();

}
