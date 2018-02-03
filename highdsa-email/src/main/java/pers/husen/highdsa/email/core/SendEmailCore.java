package pers.husen.highdsa.email.core;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @Desc 发送邮件核心类
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午4:26:54
 * 
 * @Version 1.0.0
 */
public class SendEmailCore {
	/**
	 * 获取邮箱机器人登录邮箱session（会话）
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static Session setupSession() throws GeneralSecurityException {
		// 建立属性对象
		Properties properties = new Properties();
		// 开启SSL
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		// 开启认证
		properties.put("mail.smtp.auth", "true");
		// 开启SSL
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 设置邮件服务器主机名
		properties.setProperty("mail.host", "smtp.qq.com");
		// 设置端口
		properties.setProperty("mail.smtp.port", "465");

		// 根据认证获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yige_robot@foxmail.com", "xjsvsdyrekodfiah");
			}
		});

		return session;
	}
}
