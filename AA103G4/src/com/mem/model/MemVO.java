package com.mem.model;

import java.io.Serializable;
import java.util.Set;

import com.event_member.model.Event_MemberVO;

public class MemVO implements Serializable {

	private Integer mem_no;
	private String mem_name;
	private String mem_nic;
	private String mem_tel;
	private String mem_mail;
	private String mem_psw;
	private byte[] mem_photo;
	private Set<Event_MemberVO> e_Mems;

//	@Override
//	public String toString() {
//		return this.mem_no + "," + this.mem_name + "," + this.mem_tel + "," + this.mem_mail + "," + this.mem_psw
//		+ "," + this.mem_photo + "\n";
//	}

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public String getMem_nic() {
		return mem_nic;
	}
	
	public void setMem_nic(String mem_nic) {
		this.mem_nic = mem_nic;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public String getMem_psw() {
		return mem_psw;
	}

	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public byte[] getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}

	public Set<Event_MemberVO> getE_Mems() {
		return e_Mems;
	}

	public void setE_Mems(Set<Event_MemberVO> e_Mems) {
		this.e_Mems = e_Mems;
	}
	
	
}
