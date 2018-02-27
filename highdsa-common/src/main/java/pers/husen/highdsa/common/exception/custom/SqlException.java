package pers.husen.highdsa.common.exception.custom;

/**
 * @Desc 数据库操作异常
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月27日 上午10:54:14
 * 
 * @Version 1.0.0
 */
public class SqlException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/** 错误编码 */
	private String errorCode;

	public SqlException() {
		super();
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param message
	 *            信息描述
	 */
	public SqlException(String message) {
		super(message);
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param errorCode
	 *            错误编码
	 * @param message
	 *            信息描述
	 */
	public SqlException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}