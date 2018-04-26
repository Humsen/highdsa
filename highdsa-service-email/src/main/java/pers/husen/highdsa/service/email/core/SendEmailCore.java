package pers.husen.highdsa.service.email.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.util.MailSSLSocketFactory;

import pers.husen.highdsa.common.BaseUtils;
import pers.husen.highdsa.common.constant.Encode;
import pers.husen.highdsa.common.exception.StackTrace2Str;

/**
 * @Desc 发送邮件核心类
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午4:26:54
 * 
 * @Version 1.0.2
 */
public class SendEmailCore {
	private final Logger logger = LogManager.getLogger(SendEmailCore.class.getName());
	/** 不设置静态,可以有任意多的Session */
	public Session session = null;
	/** 存储参数 */
	private Properties properties = null;

	/** 参数对应的变量 */
	private String mailHost = null;
	private String senderEamilAddr = null;
	private String senderNickName = null;
	private String senderEmailPwd = null;
	private String recipients = null;

	public SendEmailCore() {
		properties = new Properties();
	}

	/** 获取会话 */
	public Session getSession(String configFile) throws GeneralSecurityException {
		if (session == null) {
			this.setupSession(configFile);
		}

		return this.session;
	}

	/**
	 * 登录邮箱获取session（会话）
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	public Session setupSession(String configFile) throws GeneralSecurityException {

		try {
			properties.load(new InputStreamReader(SendEmailCore.class.getClassLoader().getResourceAsStream(configFile), Encode.DEFAULT_ENCODE));

			this.mailHost = properties.getProperty("mail.smtp.host");
			this.senderEamilAddr = properties.getProperty("mail.sender.username");
			this.senderEmailPwd = properties.getProperty("mail.sender.password");
			this.senderNickName = properties.getProperty("mail.sender.nickname");
			this.recipients = properties.getProperty("mail.recipients.username");
		} catch (IOException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
			this.setupDefaultSession();
		}

		if (BaseUtils.isEmpty(mailHost) || BaseUtils.isEmpty(senderEmailPwd) || BaseUtils.isEmpty(senderEmailPwd)) {
			logger.info("建立邮件会话：邮箱参数异常");
			return this.setupDefaultSession();
		}

		// QQ邮箱开启SSL, 貌似去掉也可以
		// MailSSLSocketFactory sf = new MailSSLSocketFactory();
		// sf.setTrustAllHosts(true);
		// properties.put("mail.smtp.ssl.socketFactory", sf);

		// 根据认证获取默认session对象
		session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEamilAddr, senderEmailPwd);
			}
		});

		logger.info("建立邮件会话：设置成功!  host:{},fromEamilAddr:{},fromEmailPwd:{}", mailHost, senderEamilAddr, senderEmailPwd);
		return session;
	}

	/**
	 * 设置默认会话（通过QQ邮箱发送）
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	public Session setupDefaultSession() throws GeneralSecurityException {
		// 1、设置邮件服务器主机名
		properties.setProperty("mail.smtp.host", "smtp.qq.com");

		// 2、设置端口
		properties.setProperty("mail.smtp.port", "465");

		// 开启SSL
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		// 3、开启认证
		properties.put("mail.smtp.auth", "true");
		// 4、开启SSL
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);

		// 根据认证获取默认session对象
		session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yige_robot@foxmail.com", "xjsvsdyrekodfiah");
			}
		});

		// 5、设置nickname
		properties.put("mail.sender.nickname", "一格网站机器人");

		logger.info("使用系统默认邮箱会话(yige_robot@foxmail.com)");
		return session;
	}

	/**
	 * @return the mailHost
	 */
	public String getMailHost() {
		return mailHost;
	}

	/**
	 * @param mailHost
	 *            the mailHost to set
	 */
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	/**
	 * @return the senderEamilAddr
	 */
	public String getSenderEamilAddr() {
		return senderEamilAddr;
	}

	/**
	 * @param senderEamilAddr
	 *            the senderEamilAddr to set
	 */
	public void setSenderEamilAddr(String senderEamilAddr) {
		this.senderEamilAddr = senderEamilAddr;
	}

	/**
	 * @return the senderNickName
	 */
	public String getSenderNickName() {
		return senderNickName;
	}

	/**
	 * @param senderNickName
	 *            the senderNickName to set
	 */
	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}

	/**
	 * @return the senderEmailPwd
	 */
	public String getSenderEmailPwd() {
		return senderEmailPwd;
	}

	/**
	 * @param senderEmailPwd
	 *            the senderEmailPwd to set
	 */
	public void setSenderEmailPwd(String senderEmailPwd) {
		this.senderEmailPwd = senderEmailPwd;
	}

	/**
	 * @return the recipients
	 */
	public String getRecipients() {
		return recipients;
	}

	/**
	 * @param recipients
	 *            the recipients to set
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}