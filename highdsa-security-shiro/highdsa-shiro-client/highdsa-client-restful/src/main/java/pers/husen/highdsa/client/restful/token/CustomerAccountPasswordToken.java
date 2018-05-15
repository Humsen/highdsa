package pers.husen.highdsa.client.restful.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import pers.husen.highdsa.common.entity.enums.LoginType;

/**
 * @Desc 自定义token,增加登录类型字段
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月14日 下午5:03:39
 * 
 * @Version 1.0.0
 */
public class CustomerAccountPasswordToken extends UsernamePasswordToken{
	private static final long serialVersionUID = -8129884664300987750L;

	private LoginType loginType;

	public CustomerAccountPasswordToken() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public CustomerAccountPasswordToken(String account, String password, LoginType loginType) {
		super(account, password);
		this.loginType = loginType;
	}

	/**
	 * @return the loginType
	 */
	public LoginType getLoginType() {
		return loginType;
	}

	/**
	 * @param loginType the loginType to set
	 */
	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
}