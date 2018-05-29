package mobile;

public interface MemberAppDAO_interface {

	public String findByAcctPwd(String mem_mail,String mem_psw);

	public byte[] getMemImage(String mem_no);
	
	public String loginCheck(MemAppVO memAppVO);
	
}
