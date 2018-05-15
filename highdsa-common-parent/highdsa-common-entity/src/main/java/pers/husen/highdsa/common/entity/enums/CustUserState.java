package pers.husen.highdsa.common.entity.enums;

/**
 * @Desc 客户状态枚举
 *
 * @Author 何明胜
 *
 * @Created at 2018年4月27日 下午3:23:48
 * 
 * @Version 1.0.1
 */
public enum CustUserState implements BaseEnum<Enum<CustUserState>, String> {
	/** 用户有效状态 */
	VALID("100", "有效状态"),
	/** 用户锁定状态 */
	LOCKED("200", "锁定"),
	/** 用户注销状态 */
	INVALID("300", "注销");

	private String stateCode;
	private String description;

	/**
	 * @param stateCode
	 * @param descrition
	 */
	private CustUserState(String stateCode, String description) {
		this.stateCode = stateCode;
		this.description = description;
	}

	/**
	 * @return the stateCode
	 */
	@Override
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 *            the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescrition() {
		return description;
	}

	/**
	 * @param descrition
	 *            the description to set
	 */
	public void setDescrition(String description) {
		this.description = description;
	}
}