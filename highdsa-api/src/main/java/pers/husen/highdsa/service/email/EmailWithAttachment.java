package pers.husen.highdsa.service.email;

import java.io.File;

/**
 * @Desc 带附件的邮件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午4:28:42
 * 
 * @Version 1.0.0
 */
public interface EmailWithAttachment {
	/**
	 * 发送邮件给用户
	 * 
	 * @param userEmail
	 *            用户邮箱（收件人）
	 * @param subject
	 *            标题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件
	 */
	public void sendEmail2User(String userEmail, String subject, String content, File attachment);

	/**
	 * 发送带附件的邮件给管理员
	 * 
	 * @param name
	 *            发件人姓名
	 * @param senderEmail
	 *            发件人邮箱
	 * @param phone
	 *            发件人电话
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件
	 */
	public void sendEmail2Admin(String name, String senderEmail, String phone, String content, File attachment);
}