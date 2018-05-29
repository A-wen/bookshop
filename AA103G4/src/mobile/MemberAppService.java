package mobile;


public class MemberAppService {

private MemberAppDAO_interface dao;
	
	public MemberAppService(){
		dao = new MemberAppDAO();
	}
	
	public String loginCheck(MemAppVO memAppVO){
		return dao.loginCheck(memAppVO);
	}
	
	public String findByAcctPwd(String mem_mail, String mem_psw){
		return dao.findByAcctPwd(mem_mail, mem_psw);
	}
	
	public byte[] getMemImage(String mem_no){
		return dao.getMemImage(mem_no);
	}
	
	
	
}
