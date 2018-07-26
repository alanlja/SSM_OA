package common.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class FastestEmail {

	public static void sendEmail(String sendAddress, String title, String content) throws Exception {
		//创建一个Properties对象
		Properties properties = new Properties();
		//设置服务器地址
		properties.setProperty("mail.host", "smtp.163.com");
		//设置邮件传输协议
		properties.setProperty("mail.transport.protocol", "smtp");
		//是否检验服务器的用户名和密码
		properties.setProperty("mail.smtp.auth", "true");
		//创建session创建
		Session session = Session.getInstance(properties);
		//创建一个发送邮件的对象
		Transport transport = session.getTransport();
		//连接服务器
		transport.connect("smtp.163.com", "alanmrliang", "ljalja.13829763");
		//创建一个邮件对象
		Message message = createMessage(session,sendAddress,title,content);
		//发送邮件
		transport.sendMessage(message, message.getAllRecipients());
	}

	private static Message createMessage(Session session, String sendAddress, String title, String content) throws Exception {
		//创建邮件对象
		Message message = new MimeMessage(session);
		//设置邮件发件人
		message.setFrom(new InternetAddress("alanmrliang@163.com"));
		//设置邮件收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(sendAddress));
		//设置邮件标题
		message.setSubject(title);
		//设置邮件内容 
		message.setContent(content, "text/html;charset=utf-8");
		return message;
	}

}
