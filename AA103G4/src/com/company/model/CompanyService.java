package com.company.model;

import java.util.List;

import com.book.model.BookVO;

public class CompanyService {
	private CompanyDAO_interface dao;

	public CompanyService() {
		dao = new CompanyDAO();
	}

	/**
	 * 新增出版社 接名稱、電話、地址、統編、聯絡人、聯絡E-mail
	 **/
	public CompanyVO addCompany(String comp_name, String comp_tel, String comp_add, String comp_number,
			String comp_contact, String comp_email) {
		CompanyVO companyVO = new CompanyVO();
		companyVO.setComp_name(comp_name);
		companyVO.setComp_tel(comp_tel);
		companyVO.setComp_add(comp_add);
		companyVO.setComp_number(comp_number);
		companyVO.setComp_contact(comp_contact);
		companyVO.setComp_email(comp_email);
		dao.insert(companyVO);
		return companyVO;
	}

	/** 修改出版社 **/
	public CompanyVO updateCompany(Integer comp_no, String comp_name, String comp_tel, String comp_add,
			String comp_number, String comp_contact, String comp_email) {
		CompanyVO companyVO = new CompanyVO();
		companyVO.setComp_no(comp_no);
		companyVO.setComp_name(comp_name);
		companyVO.setComp_tel(comp_tel);
		companyVO.setComp_add(comp_add);
		companyVO.setComp_number(comp_number);
		companyVO.setComp_contact(comp_contact);
		companyVO.setComp_email(comp_email);
		dao.update(companyVO);
		return companyVO;
	}

	/** 刪除出版社 **/
	public void deleteCompany(Integer comp_no) {
		dao.delete(comp_no);
	}

	/** 查單一出版社 **/
	public CompanyVO getOneCompany(Integer comp_no) {
		return dao.findByPrimaryKey(comp_no);
	}

	/** 依照名稱查出版社 **/
	public List<CompanyVO> getCompanyByName(String comp_name) {
		return dao.getByName(comp_name);
	}

	/** 列出所有出版社 **/
	public List<CompanyVO> getAllCompany() {
		return dao.getAll();
	}

	/** 依出版社查書籍 **/
	public List<BookVO> getBookByCompany(Integer comp_no) {
		return dao.getBookByCompany(comp_no);
	}
}
