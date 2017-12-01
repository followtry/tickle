package cn.followtry.incubate.java.log;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/4/18.
 */
public class LoggerTest {

	private static final Logger LOGGER=LoggerFactory.getLogger(LoggerTest.class);

	private static final Logger logger_mail = LoggerFactory.getLogger("MailLogger");

	public static void main(String[] args) throws MessagingException {

		/**
		 * 验证通过日志配置文件方式发送邮件
		 */
		try {
			String str=null;
			System.out.println(str.length());
		} catch(Exception e) {
			String s=e.toString();
			LOGGER.error("异常错误：{}", s.toString());
		}

//		sendMail();
	}

	private static void sendMail() throws Exception {
		Properties props=new Properties(){
			{
				put("mail.smtp.auth","true");
				put("mail.smtp.host","smtp.qq.com");
				put("mail.user","1033544740@qq.com");
				put("mail.password","lxmgpnbdyvdnbdfi");
				put("mail.smtp.rt","587");
				put("mail.transport.protocol","smtp");
			}
		};
		

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {

            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人
		InternetAddress to = new InternetAddress("jingzz@yonyou.com");
		message.setRecipient(Message.RecipientType.TO, to);

		// 设置抄送
//		InternetAddress cc = new InternetAddress("1182696993@qq.com");
//		message.setRecipient(Message.RecipientType.CC, cc);

		// 设置密送，其他的收件人不能看到密送的邮件地址
//		InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//		message.setRecipient(Message.RecipientType.CC, bcc);

		// 设置邮件标题
		message.setSubject("测试邮件");

		// 设置邮件的内容体
		message.setContent("<h2>这是用java程序发出的测试邮件，收到该邮件说明您已经通过代码发邮件成功！！</h2>", "text/html;charset=UTF-8");

		System.out.println("ddddddeeee");
		// 发送邮件
		Transport.send(message);
		System.out.println("dddddddddd");
	}
}
