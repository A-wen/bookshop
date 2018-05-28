package com.mail.tool;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public void sendMail(String to, String subject, String messageText) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			final String myGmail = "java.aa103@gmail.com";
			final String myGmail_password = "jpufchykzftuonyc";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject(subject);
			message.setText(messageText);
			message.setContent("<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<a href=\"http://localhost:8081/AA103G4/Front-End/member/updatePsw.jsp\">\n" +
                    "請點擊此連結，並修改您的密碼</a>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>", "text/html;charset=utf-8");
			Transport.send(message);
			System.out.println("送出成功!");
		} catch (MessagingException e) {
			System.out.println("送出失敗!");
			e.printStackTrace();
		}
	}

//	public static void main(String args[]) {
//
//		String to = "ixlogic@pchome.com.tw";
//
//		String subject = "撖Ⅳ�";
//
//		String ch_name = "peter1";
//		String passRandom = "111";
//		String messageText = "Hello! " + ch_name + " 隢牲閮迨撖Ⅳ: " + passRandom + "\n" + " (撌脩��)";
//
//		MailService mailService = new MailService();
//		mailService.sendMail(to, subject, messageText);
//
//	}

}
