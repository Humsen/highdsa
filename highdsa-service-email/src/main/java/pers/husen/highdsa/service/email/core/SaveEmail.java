package pers.husen.highdsa.service.email.core;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

import pers.husen.highdsa.common.constant.EmailConstants;
import pers.husen.highdsa.common.exception.StackTrace2Str;
import pers.husen.highdsa.common.utility.ReadConfigFile;

/**
 * @Desc 保存邮件到已发送
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月6日 下午5:36:33
 * 
 * @Version 1.0.3
 */
public class SaveEmail {
	private static final Logger logger = LogManager.getLogger(SaveEmail.class.getName());

	/** 保存连接配置 */
	Properties properties;

	public SaveEmail() {
	}

	public SaveEmail(MimeMessage msg) {
		this.saveIntoSent(msg);
	}

	/**
	 * 保存到已发送
	 * 
	 * @param msg
	 *            发送的邮件对象
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void saveIntoSent(MimeMessage msg) {
		try {
			properties = ReadConfigFile.readByRelativePath(EmailConstants.SAVE_MAIL_CONFIG_FILE);

			// 获取会话
			Session imapSession = Session.getInstance(properties, new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(properties.getProperty(EmailConstants.MAIL_STORE_USERNAME), properties.getProperty(EmailConstants.MAIL_STORE_PASSWORD));
				}
			});
			// 获取存储服务器
			IMAPStore store = (IMAPStore) imapSession.getStore(properties.getProperty(EmailConstants.MAIL_STORE_PROTOCOL));
			// 连接
			logger.info("用户名：{}, 密码:{}", properties.getProperty(EmailConstants.MAIL_STORE_USERNAME), properties.getProperty(EmailConstants.MAIL_STORE_PASSWORD));
			store.connect(properties.getProperty(EmailConstants.MAIL_STORE_USERNAME), properties.getProperty(EmailConstants.MAIL_STORE_PASSWORD));
			// 获取 已发送 文件夹
			IMAPFolder folder = (IMAPFolder) store.getFolder(EmailConstants.SENT_FOLDER_NAME);
			// 如果不存在,则创建
			if (!folder.exists()) {
				logger.trace("已发送文件夹在服务器不存在, 已创建!");
				folder.create(Folder.HOLDS_MESSAGES);
			}
			// 以 都读写模式打开 已发送 文件夹
			folder.open(Folder.READ_WRITE);
			// 设置已读
			// msg.setFlag(Flags.Flag.SEEN, true);
			// 添加到 已发送
			folder.appendMessages(new Message[] { msg });

			folder.expunge();

			logger.trace("保存邮件到已发送 --> 成功");
		} catch (IOException | MessagingException e) {
			logger.warn(StackTrace2Str.exceptionStackTrace2Str("保存邮件到已发送-->出错", e));
		}
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