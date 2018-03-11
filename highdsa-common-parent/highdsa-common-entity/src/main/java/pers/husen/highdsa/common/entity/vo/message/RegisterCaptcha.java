package pers.husen.highdsa.common.entity.vo.message;

/**
 * @Desc 用户注册的参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午3:39:48
 * 
 * @Version 1.0.0
 */
public class RegisterCaptcha {
	/**
	 * 验证码
	 */
	private String code;

	public RegisterCaptcha() {
	}

	/**
	 * @param code
	 */
	public RegisterCaptcha(String code) {
		super();
		this.code = code;
	}

	@Override
	public String toString() {
		return "RegisterCaptchaVo [code=" + code + "]";
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}