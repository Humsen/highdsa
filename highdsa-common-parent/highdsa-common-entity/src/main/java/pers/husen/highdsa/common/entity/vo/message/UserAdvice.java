package pers.husen.highdsa.common.entity.vo.message;

/**
 * @Desc 用户通知的参数
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月11日 下午3:39:48
 * 
 * @Version 1.0.0
 */
public class UserAdvice {
	private String name;
	private String code;

	@Override
	public String toString() {
		return "UserAdviceVo [name=" + name + ", code=" + code + "]";
	}

	public UserAdvice() {
	}

	/**
	 * @param name
	 * @param code
	 */
	public UserAdvice(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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