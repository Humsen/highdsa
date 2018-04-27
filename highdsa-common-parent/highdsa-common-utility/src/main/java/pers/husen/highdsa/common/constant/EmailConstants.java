package pers.husen.highdsa.common.constant;

/**
 * @Desc 邮件常量
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 上午9:17:50
 * 
 * @Version 1.0.0
 */
public class EmailConstants {
	/** 邮箱已发送文件夹名称 */
	public static final String SENT_FOLDER_NAME = "Sent Messages";

	/** 发给用户的配置文件 */
	public static final String CONFIG_FILE_2USER = "email/robot2user-mail.properties";
	/** 发给管理员的配置文件 */
	public static final String CONFIG_FILE_2ADMIN = "email/all2admin-mail.properties";
	// 以下是相关属性的key
	public static final String MAIL_SMTP_PROTOCOL = "mail.smtp.protocol";
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String MAIL_SENDER_USERNAME = "mail.sender.username";
	public static final String MAIL_SENDER_NICKNAME = "mail.sender.nickname";
	public static final String MAIL_SENDER_PASSWORD = "mail.sender.password";
	public static final String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
	public static final String MAIL_RECIPIENTS_USERNAME = "mail.recipients.username";

	public static final String MAIL_SMTP_SSL_SOCKETFACTORY = "mail.smtp.ssl.socketFactory";

	/** 保存邮件配置文件路径 */
	public static final String SAVE_MAIL_CONFIG_FILE = "email/savemail.properties";
	// 以下是相关属性的key
	public static final String MAIL_STORE_PROTOCOL = "mail.store.protocol";
	public static final String MAIL_IMAP_HOST = "mail.imap.host";
	public static final String MAIL_IMAP_PORT = "mail.imap.port";
	public static final String MAIL_IMAP_AUTH = "mail.imap.auth";
	public static final String MAIL_STORE_USERNAME = "mail.store.username";
	public static final String MAIL_STORE_NICKNAME = "mail.store.nickname";
	public static final String MAIL_STORE_PASSWORD = "mail.store.password";
	public static final String MAIL_IMAP_SSL_ENABLE = "mail.imap.ssl.enable";
}