package mobile;

import java.io.Serializable;

public class MemAppVO implements Serializable {
	private Integer mem_no;
	private String mem_name;
	private String mem_nic;
	private String mem_tel;
	private String mem_mail;
	private String mem_psw;
	private byte[] mem_photo;

//	@Override
//	public String toString() {
//		return this.mem_no + "," + this.mem_name + "," + this.mem_tel + "," + this.mem_mail + "," + this.mem_psw
//		+ "," + this.mem_photo + "\n";
//	}

	public Integer getMemNo() {
		return mem_no;
	}

	public void setMemNo(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getMemName() {
		return mem_name;
	}

	public void setMemName(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMemTel() {
		return mem_tel;
	}

	public String getMemNic() {
		return mem_nic;
	}
	
	public void setMemNic(String mem_nic) {
		this.mem_nic = mem_nic;
	}
	public void setMemTel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMemMail() {
		return mem_mail;
	}

	public void setMemMail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public String getMemPsw() {
		return mem_psw;
	}

	public void setMemPsw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public byte[] getMemPhoto() {
		return mem_photo;
	}

	public void setMemPhoto(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
}
