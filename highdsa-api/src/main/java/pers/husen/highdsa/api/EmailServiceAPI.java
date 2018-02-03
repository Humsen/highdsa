package pers.husen.highdsa.api;

/**
 * @Desc    服务器接口
 *
 * @Author  何明胜
 *
 * @Created at 2018年2月3日 下午7:37:37
 * 
 * @Version 1.0.0
 */
public interface EmailServiceAPI {
	/**
	 * 发送验证码通用函数
	 * 
	 * @param email
	 * @param randomCode
	 * @param subject
	 * @param mode
	 * @return
	 */
	public int sendEmail2User(String email, int randomCode, String subject, String mode);
	
	/**
	 * 发邮件给站长
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param content
	 * @return
	 */
	public int sendEmail2Admin(String name, String email, String phone, String content);
}
