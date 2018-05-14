package pers.husen.highdsa.common.entity.constants;

/**
 * @Desc 登录方式枚举
 *
 * @Author 何明胜
 *
 * @Created at 2018年5月14日 下午5:06:55
 * 
 * @Version 1.0.0
 */
public enum LoginType implements BaseEnum<Enum<LoginType>, String> {
	USERNAME("100", "用户名登录"), EMAIL("200", "邮箱登录"), PHONE("300", "手机登录");

	private String stateCode;
	private String description;

	@Override
	public String getStateCode() {
		return stateCode;
	}

	@Override
	public String getDescrition() {
		return description;
	}

	/**
	 * @param stateCode
	 * @param description
	 */
	private LoginType(String stateCode, String description) {
		this.stateCode = stateCode;
		this.description = description;
	}
}