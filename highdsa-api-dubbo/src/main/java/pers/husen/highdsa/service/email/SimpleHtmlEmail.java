package pers.husen.highdsa.service.email;

/**
 * @Desc 简单邮件服务
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午7:37:37
 * 
 * @Version 1.0.3
 */
public interface SimpleHtmlEmail {
	/**
	 * 找回密码邮箱验证
	 * 
	 * @param mailTo
	 * @param randomCode
	 * @return
	 */
	public boolean sendEmail4RetrivePwd(String mailTo, int randomCode);

	/**
	 * 新用户注册邮箱验证
	 * 
	 * @param mailTo
	 * @param randomCode
	 * @return
	 */
	public boolean sendEmail4Register(String mailTo, int randomCode);

	/**
	 * 用户修改邮箱验证原邮箱
	 * 
	 * @param mailTo
	 * @param randomCode
	 * @return
	 */
	public boolean sendEmail4ModifyEmailAuth(String mailTo, int randomCode);

	/**
	 * 用户修改邮箱绑定新邮箱
	 * 
	 * @param mailTo
	 * @param randomCode
	 * @return
	 */
	public boolean sendEmail4ModifyEmailBind(String mailTo, int randomCode);

	/**
	 * 发送邮件给用户（发送验证码通用函数）
	 * 
	 * @param mailTo
	 * @param subject
	 * @param content
	 * @return
	 */
	public boolean sendEmail2User(String mailTo, String subject, String content);

	/** ----------------- 分割线 --------------------- */

	/**
	 * 用户反馈联系管理员
	 * 
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 * @param content
	 * @return
	 */
	public boolean sendEmail4UserFeedback(String nameFrom, String mailFrom, String phoneFrom, String content);

	/**
	 * 发邮件给站长（联系站长）
	 * 
	 * @param nameFrom
	 * @param mailFrom
	 * @param phoneFrom
	 * @param content
	 * @return
	 */
	public boolean sendEmail2Admin(String nameFrom, String mailFrom, String phoneFrom, String content);
}