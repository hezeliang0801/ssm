package com.chatRobot.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 电子邮件工具类
 * @author Administrator
 *
 */
public class MailUtil {

	public static void main(String[] args) {
		try {
			sendMail("18855589231@163.com","爸爸来了","哈哈哈，爸爸来了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送电子邮件
	 * @param addr 收件人地址
	 * @param subject 主题
	 * @param text 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String addr,String subject,String text) throws MessagingException, FileNotFoundException {
		Properties props=new Properties();
		props.put("mail.smtp.host","smtp.163.com");
		props.put("mail.smtp.auth","true");
		Session session=Session.getInstance(props);
		File file = new File("C:\\Users\\HeZeLiang\\Desktop\\1111.jpg");
		InputStream inputStream = new FileInputStream(file);
		//构造信息体 
		MimeMessage message =new MimeMessage(session,inputStream);
		 //发件地址 
		Address address = new InternetAddress("hezeliang0801@163.com");
		message.setFrom(address);
		//收件地址 
		Address toAddress = new InternetAddress(addr); 
		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
		//主题 
		message.setSubject(subject);
		//正文 
		message.setText(text);
		message.saveChanges();
		Transport transport = session.getTransport("smtp"); 
		transport.connect("smtp.163.com", "hezeliang0801@163.com", "Pass1234"); //发送 
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
	}
	
}
