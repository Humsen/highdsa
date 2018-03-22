package pers.husen.highdsa.service.email;

/**
 * @Desc 带附件的邮件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月5日 下午4:28:42
 * 
 * @Version 1.0.1
 */
public interface AttachHtmlEmail {
	/**
	 * 发送邮件给用户
	 * 
	 * @param mailTo
	 *            用户邮箱（收件人）
	 * @param subject
	 *            标题
	 * @param content
	 *            内容
	 * @param attachment
	 *            附件
	 * @return
	 */
	public int sendEmail2User(String mailTo, String subject, String content, String attachment);

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
	 * @param attachment
	 *            附件
	 * @return
	 */
	public int sendEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content, String attachment);
}