package pers.husen.highdsa.service.email;

/**
 * @Desc 服务器接口
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月3日 下午7:37:37
 * 
 * @Version 1.0.1
 */
public interface SimpleHtmlEmail {
	/**
	 * 找回密码邮箱验证
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail4RetrivePwd(String email, int randomCode);

	/**
	 * 新用户注册邮箱验证
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail4Register(String email, int randomCode);

	/**
	 * 用户修改邮箱验证原邮箱
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail4ModufyEmailAuth(String email, int randomCode);

	/**
	 * 用户修改邮箱绑定新邮箱
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail4ModufyEmailBind(String email, int randomCode);

	/**
	 * 发送邮件给用户（发送验证码通用函数）
	 * 
	 * @param email
	 * @param subject
	 * @param content
	 * @return
	 */
	public int sendEmail2User(String email, String subject, String content);

	/** ----------------- 分割线 --------------------- */

	/**
	 * 用户反馈联系管理员
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param content
	 * @return
	 */
	public int sendEmail4UserFeedback(String name, String email, String phone, String content);

	/**
	 * 发邮件给站长（联系站长）
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param content
	 * @return
	 */
	public int sendEmail2Admin(String name, String email, String phone, String content);
}
