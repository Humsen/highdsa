package pers.husen.highdsa.common.exception.custom;

/**
 * @Desc 系统错误
 *
 * @Author 何明胜
 *
 * @Created at 2018年2月26日 下午11:58:54
 * 
 * @Version 1.0.0
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** 错误编码 */
	private String errorCode;

	public SystemException() {
		super();
	}
	
	/**
	 * 构造一个基本异常.
	 *
	 * @param message
	 *            信息描述
	 */
	public SystemException(String message) {
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
	public SystemException(String errorCode, String message) {
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