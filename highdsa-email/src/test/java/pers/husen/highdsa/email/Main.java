package pers.husen.highdsa.email;


/**
 * @Desc    TODO
 *
 * @Author  何明胜
 *
 * @Created at 2018年2月3日 下午5:20:50
 * 
 * @Version 1.0.0
 */
public class Main {
	public static void main(String[] args) {
		Main main = new Main();

		main.sendEmail2Register("940706904@qq.com", 123123);
	}
	
	public int sendEmail2RetrivePwd(String email, int randomCode) {
		String subject = "【何明胜的个人网站】找回密码邮箱验证";
		String mode = "您正在使用找回密码功能。";

		return new SendEmail().sendEmail2User(email, randomCode, subject, mode);
	}

	public int sendEmail2Register(String email, int randomCode) {
		String subject = "【何明胜的个人网站】新用户注册邮箱验证";
		String mode = "欢迎在【何明胜的个人网站】注册账号。";

		return new SendEmail().sendEmail2User(email, randomCode, subject, mode);
	}

	/**
	 * 修改邮箱验证原邮箱
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail2ModufyEmailAuth(String email, int randomCode) {
		String subject = "【何明胜的个人网站】用户修改邮箱验证原邮箱";
		String mode = "您正在使用修改邮箱功能。第一步，验证码您的原邮箱。";

		return new SendEmail().sendEmail2User(email, randomCode, subject, mode);
	}

	/**
	 * 修改邮箱验证原邮箱
	 * 
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail2ModufyEmailBind(String email, int randomCode) {
		String subject = "【何明胜的个人网站】用户修改邮箱绑定新邮箱";
		String mode = "您正在使用修改邮箱功能。第二步，绑定您的新邮箱。";

		return new SendEmail().sendEmail2User(email, randomCode, subject, mode);
	}
}
