package util.cy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.SimpleEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.log4j.Logger;

import com.order_info.controller.Order_infoServlet;

public class MailSender {

	private static Logger logger = Logger.getLogger(MailSender.class);
	private HtmlEmail email;
	
	public MailSender(){
		email = new HtmlEmail();
		email.setSmtpPort(587);
		email.setHostName("smtp.gmail.com");
		email.setAuthentication("java.aa103@gmail.com", "jpufchykzftuonyc");
		email.setStartTLSEnabled(true);
		
		
		email.setCharset("UTF-8");
		try {
			email.setFrom("java.aa103@gmail.com", "DEV");
		} catch (EmailException e) {
			logger.error("不合法的Email位置"+e.toString());
		}
		
	}
	
	public int sendMailLoop(Set<String> mailLoop,String title,String mailContent){
		int countSend = 0;
		try {
			//收件人|主旨
			email.addTo("chihyu73@gmail.com");
			email.setSubject("Apache Commons Mail Test");
			//信件本文
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");
			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
			email.setTextMsg("Your email client does not support HTML messages");
			email.send();
			countSend += 1 ;
		} catch (MalformedURLException |EmailException e) {
			logger.error("寄送信件時出現錯誤："+e.toString());
		} 
		return countSend;
	}
	
	public boolean sendMail(String addr,String title,String mailContent){
		boolean result = false;
		try {
			email.addTo(addr);
			email.setSubject(title);
			email.setHtmlMsg(mailContent);
			email.send();
			logger.info(mailContent);
			result = true;
		} catch (EmailException e) {
			logger.error("寄送信件時出現錯誤："+e.toString());
		}
		return result;
	}
}
