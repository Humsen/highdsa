package pers.husen.highdsa.service.email;

import java.io.File;

/**
 * @Desc 带附件的邮件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午4:28:42
 * 
 * @Version 1.0.2
 */
public interface AttachHtmlEmail {
	/**
	 * 发送邮件给用户
	 * 
	 * @param mailTo
	 * @param subject
	 * @param content
	 * @param attachFile
	 *            附件文件
	 * @return
	 */
	public boolean sendEmail2User(String mailTo, String subject, String content, File attachFile);

	/**
	 * /** 发送邮件给用户
	 * 
	 * @param mailTo
	 *            用户邮箱（收件人）
	 * @param subject
	 *            标题
	 * @param content
	 *            内容
	 * @param attachUrl
	 *            附件http url
	 * @param attachName
	 *            附件名称
	 * @return
	 */
	public boolean sendEmail2User(String mailTo, String subject, String content, String attachUrl, String attachName);

	/**
	 * 发送带附件的邮件给管理员
	 * 
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 * @param content
	 * @param attachFile
	 *            附件文件
	 * @return
	 */
	public boolean sendEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content, File attachFile);

	/**
	 * 发送带附件的邮件给管理员
	 * 
	 * @param nameFrom
	 *            发件人姓名
	 * @param mailFrom
	 *            发件人邮箱
	 * @param phoneFrom
	 *            发件人电话
	 * @param content
	 *            内容
	 * @param attachUrl
	 *            附件http url
	 * @param attachName
	 *            附件名称
	 * @return
	 */
	public boolean sendEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content, String attachUrl, String attachName);
}